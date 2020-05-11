package com.example.ShortenerLink.service.serviceImpl;

import com.example.ShortenerLink.domain.Link;
import com.example.ShortenerLink.exceptions.NotFoundException;
import com.example.ShortenerLink.repository.LinkRepository;
import com.example.ShortenerLink.service.LinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class LinkServiceImpl implements LinkService {

    private final LinkRepository linkRepository;

    @Override
    public Link create(Link link) {
        String newCode = generateRandomCode(7);
        while (linkRepository.findByShortLink(newCode) != null) {
            newCode = generateRandomCode(7);
        }
        if (!link.getUrl().startsWith("http://") && !link.getUrl().startsWith("https://")) {
            link.setUrl("http://" + link.getUrl());
        }
        link.setShortLink(newCode);
        link.setCreatDay(LocalDateTime.now());
        return linkRepository.save(link);
    }

    @Override
    public List<Link> findAll() {
        return linkRepository.findAll();
    }

    @Override
    public Link findById(Long id) {
       return linkRepository.findById(id).get();
    }

    @Override
    public Link showByShortLink(String shortLink) {
        return linkRepository.findByShortLink(shortLink);
    }

    @Override
    public void delete(Link link) {
        linkRepository.delete(link);
    }

    @Override
    public Link findByShortLink(String code) {
        return linkRepository.findByShortLink(code);
    }

    private static final String CHAR_LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String CHAR_UPPER = CHAR_LOWER.toUpperCase();
    private static final String NUMBER = "0123456789";
    private static final String DATA_FOR_RANDOM_STRING = CHAR_LOWER + CHAR_UPPER + NUMBER;
    private static SecureRandom random = new SecureRandom();

    private static String generateRandomCode(int length) {
        StringBuilder sb = new StringBuilder(length);
        for (int i = 0; i < length; i++) {
            int rndCharAt = random.nextInt(DATA_FOR_RANDOM_STRING.length());
            char rndChar = DATA_FOR_RANDOM_STRING.charAt(rndCharAt);
            sb.append(rndChar);
        }
        return sb.toString();
    }
}
