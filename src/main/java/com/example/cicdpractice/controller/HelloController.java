package com.example.cicdpractice.controller;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;

@RestController
public class HelloController {

//    private static final String FILE_DIR = "D:\\workspace\\yogiyo_team_project\\upload\\";
    private static final String FILE_DIR = "/image-store/";

    @RequestMapping("/hello")
    public String hello() {
        return "hello";
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
