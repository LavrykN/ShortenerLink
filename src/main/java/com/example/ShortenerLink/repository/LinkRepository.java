package com.example.ShortenerLink.repository;

import com.example.ShortenerLink.domain.Link;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LinkRepository extends JpaRepository<Link, Long> {
    Link findByShortLink(String shortLink);
}
