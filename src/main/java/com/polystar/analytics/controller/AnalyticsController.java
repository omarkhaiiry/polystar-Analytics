package com.polystar.analytics.controller;


import com.polystar.analytics.exception.CustomException;
import com.polystar.analytics.exception.CustomExceptionDto;
import com.polystar.analytics.model.service.AnalyticsService;

import com.polystar.analytics.template.ClientTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/analytics")
@CrossOrigin(origins = "*")
public class AnalyticsController {

    private AnalyticsService analyticsService;
    private ClientTemplate clientTemplate;

    public AnalyticsController(AnalyticsService analyticsService,ClientTemplate clientTemplate) {
        this.analyticsService = analyticsService;
        this.clientTemplate = clientTemplate;
    }

    @PostMapping("most-repeated-words")
    public ResponseEntity mostRepeatedWords(@RequestParam int topWordsCount, @RequestParam List<String> filesPaths) {

        try {
            return new ResponseEntity(analyticsService.topRepeatedCountWords(topWordsCount, filesPaths), HttpStatus.OK);
        } catch (CustomException | InterruptedException e) {
            return new ResponseEntity(new CustomExceptionDto(e), HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("read-count")
    public ResponseEntity readCount(@RequestParam List<String> filesPaths) {
        analyticsService.setRepeatedWordsOneTimeConcat(new HashMap<>());
        clientTemplate.readAndCountTopWords(filesPaths.toString().replace("[","").replace("]",""));
        return new ResponseEntity(analyticsService.repeatedWordsOneTimeConcat(), HttpStatus.OK);
    }




    @GetMapping("top-repeated-concat")
    public ResponseEntity mostRepeatedWordsConcatCurrent(@RequestParam List<String> filesPaths) {

        return new ResponseEntity(analyticsService.getRepeatedWordsConcat(), HttpStatus.OK);
    }
    @PostMapping("top-repeated-concat")
    public ResponseEntity mostRepeatedWordsConcat(@RequestBody String line) {

        return new ResponseEntity(analyticsService.topRepeatedCountWordsConcat(line), HttpStatus.OK);
    }
    @PostMapping("top-onetime-repeated-concat")
    public ResponseEntity mostRepeatedWordsOneTimeConcat(@RequestBody String line) {

        return new ResponseEntity(analyticsService.repeatedWordsOneTimeConcat(line), HttpStatus.OK);
    }
}
