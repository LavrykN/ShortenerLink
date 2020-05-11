package com.example.ShortenerLink.config;

import com.example.ShortenerLink.domain.DayStatistics;
import com.example.ShortenerLink.domain.Link;
import com.example.ShortenerLink.repository.AllStatisticsRepository;
import com.example.ShortenerLink.repository.DayStatisticsRepository;
import com.example.ShortenerLink.repository.LinkRepository;
import com.example.ShortenerLink.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
@EnableScheduling
@RequiredArgsConstructor
public class DBwriter {

    private final StatisticsService statisticsService;
    private final LinkRepository linkRepository;
    private final DayStatisticsRepository dayStatisticsRepository;

//    @Scheduled(fixedDelay = 300000)
//    public void convertingAllStatsInDayStats() {
//
////        Link link = linkRepository.findById(Long.valueOf("119359")).get();
////        System.out.println(link.getId());
////        DayStatistics dayStatistics = new DayStatistics(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), link);
////        dayStatisticsRepository.save(dayStatistics);
//
//        statisticsService.convertingAndDelete();
//    }
}



