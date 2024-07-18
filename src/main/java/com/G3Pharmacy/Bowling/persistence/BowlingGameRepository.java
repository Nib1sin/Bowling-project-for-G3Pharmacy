package com.G3Pharmacy.Bowling.persistence;

import com.G3Pharmacy.Bowling.entities.BowlingGame;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@Repository
public interface BowlingGameRepository extends CrudRepository<BowlingGame, Long>{

    List<BowlingGame> findAll();

}
