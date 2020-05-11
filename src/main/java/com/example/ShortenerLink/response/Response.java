package com.example.ShortenerLink.response;

import com.example.ShortenerLink.domain.Link;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.util.List;

@Data
public class Response {
    int status_code = HttpStatus.OK.value();
    Link data;
    String status_txt;

    public Response() {
    }

    public Response(int status_code, String status_txt) {
        this.status_code = status_code;
        this.status_txt = status_txt;
    }

    public Response(Link data, String status_txt) {
        this.data = data;
        this.status_txt = status_txt;
    }

    public Response(int status_code, Link data, String status_txt) {
        this.status_code = status_code;
        this.data = data;
        this.status_txt = status_txt;
    }
}
