package com.G3Pharmacy.Bowling.service;

import com.G3Pharmacy.Bowling.entities.BowlingGame;

import java.util.Arrays;

public class BowlingGameRollService {

    private BowlingGame bowlingGame;

    public BowlingGameRollService(BowlingGame bowlingGame) {
        this.bowlingGame = bowlingGame;
    }

    public void roll(int pins) {
        int[] rolls = bowlingGame.getRolls();
        for (int i = 0; i < rolls.length; i++) {
            if (rolls[i] == 0) {
                rolls[i] = pins;
                bowlingGame.setRolls(rolls);
                break;
            }
        }
    }

    public int score() {
        int score = 0;
        int frameIndex = 0;
        int[] rolls = bowlingGame.getRolls();
        for (int frame = 0; frame < 10; frame++) {
            if (isStrike(frameIndex)) {
                score += 10 + strikeBonus(frameIndex);
                frameIndex++;
            } else if (isSpare(frameIndex)) {
                score += 10 + spareBonus(frameIndex);
                frameIndex += 2;
            } else {
                score += sumOfPinsInFrame(frameIndex);
                frameIndex += 2;
            }
        }
        return score;
    }

    private boolean isStrike(int frameIndex) {
        return bowlingGame.getRolls()[frameIndex] == 10;
    }

    private int sumOfPinsInFrame(int frameIndex) {
        return bowlingGame.getRolls()[frameIndex] + bowlingGame.getRolls()[frameIndex + 1];
    }

    private int strikeBonus(int frameIndex) {
        return bowlingGame.getRolls()[frameIndex + 1] + bowlingGame.getRolls()[frameIndex + 2];
    }

    private boolean isSpare(int frameIndex) {
        return bowlingGame.getRolls()[frameIndex] + bowlingGame.getRolls()[frameIndex + 1] == 10;
    }

    private int spareBonus(int frameIndex) {
        return bowlingGame.getRolls()[frameIndex + 2];
    }

}
