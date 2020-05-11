package com.example.ShortenerLink.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class DayStatistics {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(updatable = false)
    private String dayTime;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "link_id")
    private Link link;


    private String platform;

    @Column
    private String country; // TODO: Max value for country

    private String countViews;

    public DayStatistics() {
    }

    public DayStatistics(String dayTime, Link link) {
        this.dayTime = dayTime;
        this.link = link;
    }
}
