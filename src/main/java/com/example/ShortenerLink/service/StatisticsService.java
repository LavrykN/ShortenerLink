package com.example.ShortenerLink.service;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public interface StatisticsService {
    String findByCodeAddStatistics(String code, HttpServletRequest request) throws IOException;
    boolean convertingAndDelete();
}
