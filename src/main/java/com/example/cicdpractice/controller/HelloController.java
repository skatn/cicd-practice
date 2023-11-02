package com.example.cicdpractice.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@RestController
public class HelloController {

//    private static final String FILE_DIR = "D:\\workspace\\yogiyo_team_project\\upload\\";
    private static final String FILE_DIR = "/image-store/";

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping("/print-header")
    public Map<String, String> printHeader(HttpServletRequest request) {
        Map<String, String> result = new HashMap<>();

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames != null && headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);

            result.put(headerName, headerValue);
        }

        return result;
    }

    @PostMapping("/upload")
    public String upload(MultipartFile file) throws IOException {
        String pathname = FILE_DIR + file.getOriginalFilename();
        file.transferTo(new File(pathname));

        return "Save to " + pathname;
    }


    @GetMapping("/file/{filename}")
    public Resource load(@PathVariable String filename) throws MalformedURLException {
        return new UrlResource("file:" + FILE_DIR + filename);
    }
}
