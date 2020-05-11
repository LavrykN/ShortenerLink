package com.example.ShortenerLink.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
public class AllStatistics {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(updatable = false)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime time;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "link_id")
    private Link link;

    private String platform;
    private String country;

    public AllStatistics() {
    }

    public AllStatistics(LocalDateTime time, Link link, String platform, String country) {
        this.time = time;
        this.link = link;
        this.platform = platform;
        this.country = country;
    }
}
