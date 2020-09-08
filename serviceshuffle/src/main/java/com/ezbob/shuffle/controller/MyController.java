package com.ezbob.shuffle.controller;

import com.ezbob.shuffle.service.ShuffleService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value="ezbob")
public class MyController {

    private static Logger logger = LogManager.getLogger(MyController.class);

    @Autowired
    private ShuffleService service;

    @GetMapping("/{input}")
    public ResponseEntity<List<Integer>> getResult(@PathVariable int input) {
        try {
            return ResponseEntity.ok(this.service.createList(input));
        } catch (Exception e) {
            logger.warn("Caught exception in getResult: " + e.toString());
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
