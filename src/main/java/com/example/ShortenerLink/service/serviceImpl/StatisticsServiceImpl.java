package com.example.ShortenerLink.service.serviceImpl;

import com.example.ShortenerLink.domain.AllStatistics;
import com.example.ShortenerLink.domain.DayStatistics;
import com.example.ShortenerLink.domain.Link;
import com.example.ShortenerLink.repository.AllStatisticsRepository;
import com.example.ShortenerLink.repository.DayStatisticsRepository;
import com.example.ShortenerLink.repository.LinkRepository;
import com.example.ShortenerLink.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import ua_parser.Client;
import ua_parser.Parser;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
@RequiredArgsConstructor
public class StatisticsServiceImpl implements StatisticsService {

    private final LinkRepository linkRepository;
    private final AllStatisticsRepository allStatisticsRepository;
    private final DayStatisticsRepository dayStatisticsRepository;

    @Override
    public String findByCodeAddStatistics(String code, HttpServletRequest request) throws IOException {
        Link link = linkRepository.findByShortLink(code);
        if(link != null){
            Parser uaParser = new Parser();
            Client c = uaParser.parse(request.getHeader("User-Agent"));
            AllStatistics statisticsDay = new AllStatistics(LocalDateTime.now(), link, c.os.family,null);
            allStatisticsRepository.save(statisticsDay);
            return link.getUrl();
        }
        return "http://localhost:8080/";
    }

    @Override
    public boolean convertingAndDelete() {
        List<AllStatistics> allDayStats = allStatisticsRepository.findAll();

        Map<Link,List<Map<String,Map<String,Integer>>>> statisticsByIdLink = new HashMap<>();
        for (AllStatistics a : allDayStats) {
            if(!statisticsByIdLink.containsKey(a.getLink())){
                statisticsByIdLink.put(a.getLink(), new ArrayList<>());
                statisticsByIdLink.get(a.getLink()).add(new HashMap<>());
            }
            for (Map<String, Map<String,Integer>> s : statisticsByIdLink.get(a.getLink())) {
                if(!s.containsKey("country")){
                    s.put("country", new HashMap<>());
                }
                if(!s.containsKey("platform")){
                    s.put("platform", new HashMap<>());
                }
                if(!s.containsKey("count")){
                    s.put("count", new HashMap<>());
                }
                for ( String key : s.keySet() ) {
                    if(key == "country"){
                        if (!s.get(key).containsKey(a.getCountry())){
                            s.get(key).put(a.getCountry(), 1);
                        }else {
                            s.get(key).put(a.getCountry(), s.get(key).get(a.getCountry()) + 1);
                        }
                    }else if(key == "platform"){
                        if (!s.get(key).containsKey(a.getPlatform())){
                            s.get(key).put(a.getPlatform(), 1);
                        }else {
                            s.get(key).put(a.getPlatform(), s.get(key).get(a.getPlatform()) + 1);
                        }
                    }else if(key == "count"){
                        if (!s.get(key).containsKey("count")){
                            s.get(key).put("count", 1);
                        }else {
                            s.get(key).put("count", s.get(key).get("count") + 1);
                        }
                    }
                }
            }
        }

        for(Map.Entry<Link, List<Map<String, Map<String, Integer>>>> entry : statisticsByIdLink.entrySet()){
            DayStatistics dayStatistics = dayStatisticsRepository.findByLinkAndDayTime(entry.getKey(), LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

            if(dayStatistics == null){
                dayStatistics = new DayStatistics(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), entry.getKey());
                for (int i=0; i < entry.getValue().size(); i++){
                    dayStatistics.setCountry(new JSONObject(entry.getValue().get(i).get("country")).toString());
                    dayStatistics.setCountViews(new JSONObject(entry.getValue().get(i).get("count")).toString());
                    dayStatistics.setPlatform(new JSONObject(entry.getValue().get(i).get("platform")).toString());
                }
            }else {
                JSONObject mapBdCountry = new JSONObject(dayStatistics.getCountry());
                JSONObject mapCountry = new JSONObject(entry.getValue().get(0).get("country"));
                System.out.println(mapBdCountry.length());
                for (int j = 0; j < mapCountry.length(); j++) {
                    if (mapBdCountry.has(mapCountry.names().get(j).toString())){
                        mapBdCountry.put(mapCountry.names().get(j).toString(), mapBdCountry.getInt(mapCountry.names().get(j).toString()) + mapCountry.getInt(mapCountry.names().get(j).toString()));
                    }else {
                        mapBdCountry.put(mapCountry.names().get(j).toString(), 1);
                    }
                }
                dayStatistics.setCountry(mapBdCountry.toString());
            }
            System.out.println(dayStatistics);
        }
        return true;
    }


}
