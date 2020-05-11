package com.example.ShortenerLink.controller;

import com.example.ShortenerLink.domain.Link;
import com.example.ShortenerLink.response.Response;
import com.example.ShortenerLink.response.StatusResponse;
import com.example.ShortenerLink.service.LinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/link")
@RequiredArgsConstructor
public class LinkController {

    private final LinkService linkService;

    @GetMapping
    public List<Link> list() {
        return linkService.findAll();
    }

    @GetMapping("/{id}")
    public Response getOne(@PathVariable("id") Link link) {
        if(link == null){
            return new Response(HttpStatus.NOT_FOUND.value(), StatusResponse.NOT_FOUND);
        }
        return new Response(link, StatusResponse.OK);
    }

    @PostMapping
    public Response create(@Valid @RequestBody Link link, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return new Response(HttpStatus.NOT_FOUND.value(), StatusResponse.INVALID_URL);
        }
        return new Response(linkService.create(link), StatusResponse.OK);
    }

    @DeleteMapping("/{id}")
    private void delete(@PathVariable("id") Link link){
        linkService.delete(link);
    }



}
