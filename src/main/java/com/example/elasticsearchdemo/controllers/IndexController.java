package com.example.elasticsearchdemo.controllers;

import com.example.elasticsearchdemo.services.IndexService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/index")
@RequiredArgsConstructor
public class IndexController {


    private final IndexService indexSevice;

//    @Autowired
//    public IndexController(IndexService indexService) {
//        this.indexService = indexService;
//    }

    @PostMapping("/recreate")
    public void recreateAllIndices() {
        indexSevice.recreateIndices(true);
    }
}
