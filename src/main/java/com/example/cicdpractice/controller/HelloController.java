package com.example.cicdpractice.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

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
}
