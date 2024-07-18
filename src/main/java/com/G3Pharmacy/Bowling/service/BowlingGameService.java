package com.G3Pharmacy.Bowling.service;
import com.G3Pharmacy.Bowling.entities.BowlingGame;
import com.G3Pharmacy.Bowling.persistence.BowlingGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BowlingGameService implements IBowlingGameService {

    @Autowired
    private BowlingGameRepository bowlingGameRepository;

    @Override
    public List<BowlingGame> findAll() {
        return (List<BowlingGame>) bowlingGameRepository.findAll();
    }

    @Override
    public BowlingGame findById(Long id) {
        return bowlingGameRepository.findById(id).orElseThrow();
    }

    @Override
    public void save(BowlingGame bowlingGame) {
        bowlingGameRepository.save(bowlingGame);
    }


}
