package com.example.ShortenerLink.repository;

import com.example.ShortenerLink.domain.DayStatistics;
import com.example.ShortenerLink.domain.Link;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Optional;

public interface DayStatisticsRepository extends JpaRepository<DayStatistics, Long> {
    Optional<DayStatistics> findById(Link link);
    DayStatistics findByLink(Link link);
    DayStatistics findByLinkAndDayTime(Link link, String time);
}
