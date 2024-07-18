package com.G3Pharmacy.Bowling.controller;

import com.G3Pharmacy.Bowling.entities.BowlingGame;
import com.G3Pharmacy.Bowling.service.BowlingGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bowling")
public class BowlingGameController {

    @Autowired
    private BowlingGameService service;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createGame(@RequestBody BowlingGame game) {
        service.save(game);
    }

    @GetMapping("/all")
    public ResponseEntity<?> findAllStudent(){
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }


}
