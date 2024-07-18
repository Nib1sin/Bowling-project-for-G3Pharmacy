package com.G3Pharmacy.Bowling.controller;

import com.G3Pharmacy.Bowling.entities.BowlingGame;
import com.G3Pharmacy.Bowling.service.BowlingGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bowling")
public class BowlingGameController {

    @Autowired
    private BowlingGameService service;

    @PostMapping
    public void createGame(@RequestBody BowlingGame game) {
        service.save(game);
    }

    @GetMapping
    public List<BowlingGame> getAllGames() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public BowlingGame getGameById(@PathVariable Long id) {
        return service.findById(id);
    }


}
