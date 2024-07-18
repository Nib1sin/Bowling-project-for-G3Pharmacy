package com.G3Pharmacy.Bowling.service;

import com.G3Pharmacy.Bowling.entities.BowlingGame;

import java.util.List;

public interface IBowlingGameService {

    List<BowlingGame> findAll();

    BowlingGame findById(Long id);

    void save(BowlingGame bowlingGame);

}
