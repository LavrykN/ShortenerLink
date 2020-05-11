package com.example.ShortenerLink.controller;

import com.example.ShortenerLink.service.StatisticsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final StatisticsService statisticsService;

    @GetMapping("/{code}")
    public RedirectView main(@PathVariable("code") String code, HttpServletRequest request) throws IOException {
        return new RedirectView(statisticsService.findByCodeAddStatistics(code, request));
    }

    @GetMapping("/")
    public String main(){
        return "index";
    }
}
