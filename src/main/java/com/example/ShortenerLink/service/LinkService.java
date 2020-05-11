package com.example.ShortenerLink.service;

import com.example.ShortenerLink.domain.Link;

import java.util.List;

public interface LinkService {
    Link create(Link link);
    List<Link> findAll();
    Link findById(Long id);
    Link showByShortLink(String shortLink);
    void delete(Link link);
    Link findByShortLink(String code);
}
