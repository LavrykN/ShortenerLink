package com.example.ShortenerLink.controller;

import com.example.ShortenerLink.response.Response;
import com.example.ShortenerLink.service.StatisticsService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api/stats")
@RequiredArgsConstructor
public class StatsController {

    private final StatisticsService statisticsService;

    @GetMapping("/{code}")
    public Response getStats(@PathVariable("code") String shortUrl){
        return null; // TODO:
    }
}
