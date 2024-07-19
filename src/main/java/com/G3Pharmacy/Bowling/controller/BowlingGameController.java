package com.G3Pharmacy.Bowling.controller;

import com.G3Pharmacy.Bowling.entities.BowlingGame;
import com.G3Pharmacy.Bowling.service.BowlingGameRollService;
import com.G3Pharmacy.Bowling.service.BowlingGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/bowling")
public class BowlingGameController {

    @Autowired
    private BowlingGameService service;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public String createGame(@RequestParam String name,
                             @RequestParam int frame1roll1, @RequestParam(required = false) Integer frame1roll2,
                             @RequestParam int frame2roll1, @RequestParam(required = false) Integer frame2roll2,
                             @RequestParam int frame3roll1, @RequestParam(required = false) Integer frame3roll2,
                             @RequestParam int frame4roll1, @RequestParam(required = false) Integer frame4roll2,
                             @RequestParam int frame5roll1, @RequestParam(required = false) Integer frame5roll2,
                             @RequestParam int frame6roll1, @RequestParam(required = false) Integer frame6roll2,
                             @RequestParam int frame7roll1, @RequestParam(required = false) Integer frame7roll2,
                             @RequestParam int frame8roll1, @RequestParam(required = false) Integer frame8roll2,
                             @RequestParam int frame9roll1, @RequestParam(required = false) Integer frame9roll2,
                             @RequestParam int frame10roll1, @RequestParam(required = false) Integer frame10roll2,
                             @RequestParam(required = false) Integer frame10roll3,
                             Model model) {
        BowlingGame game = new BowlingGame();
        game.setName(name);

        List<Integer> rolls = new ArrayList<>();
        rolls.add(frame1roll1); rolls.add(frame1roll2 != null ? frame1roll2 : 0);
        rolls.add(frame2roll1); rolls.add(frame2roll2 != null ? frame2roll2 : 0);
        rolls.add(frame3roll1); rolls.add(frame3roll2 != null ? frame3roll2 : 0);
        rolls.add(frame4roll1); rolls.add(frame4roll2 != null ? frame4roll2 : 0);
        rolls.add(frame5roll1); rolls.add(frame5roll2 != null ? frame5roll2 : 0);
        rolls.add(frame6roll1); rolls.add(frame6roll2 != null ? frame6roll2 : 0);
        rolls.add(frame7roll1); rolls.add(frame7roll2 != null ? frame7roll2 : 0);
        rolls.add(frame8roll1); rolls.add(frame8roll2 != null ? frame8roll2 : 0);
        rolls.add(frame9roll1); rolls.add(frame9roll2 != null ? frame9roll2 : 0);
        rolls.add(frame10roll1); rolls.add(frame10roll2 != null ? frame10roll2 : 0);
        if (frame10roll3 != null) {
            rolls.add(frame10roll3);
        }

        game.setRolls(rolls.stream().mapToInt(i -> i).toArray());
        service.save(game);

        List<BowlingGame> games = service.findAll();
        List<BowlingGameDto> gameDtos = games.stream()
                .map(g -> new BowlingGameDto(g.getId(), g.getName(), formatRolls(g.getRolls()), getScore(g)))
                .collect(Collectors.toList());
        model.addAttribute("games", gameDtos);
        return "view";
    }

    private List<int[]> formatRolls(int[] rolls) {
        List<int[]> formattedRolls = new ArrayList<>();
        for (int i = 0; i < rolls.length; ) {
            if (rolls[i] == 10) { // strike
                if (formattedRolls.size() == 9) {
                    // Last frame special handling
                    formattedRolls.add(new int[]{rolls[i], rolls[i + 1], rolls[i + 2]});
                    break;
                } else {
                    formattedRolls.add(new int[]{rolls[i]});
                    i++;
                }
            } else {
                if (formattedRolls.size() == 9) {
                    // Last frame special handling
                    formattedRolls.add(new int[]{rolls[i], rolls[i + 1], rolls[i + 2]});
                    break;
                } else {
                    formattedRolls.add(new int[]{rolls[i], rolls[i + 1]});
                    i += 2;
                }
            }
        }
        return formattedRolls;
    }

    private int getScore(BowlingGame game) {
        BowlingGameRollService rollService = new BowlingGameRollService(game);
        return rollService.score();
    }

    @GetMapping("/all")
    public String findAllGames(Model model){
        List<BowlingGame> games = service.findAll();
        List<BowlingGameDto> gameDtos = games.stream()
                .map(game -> new BowlingGameDto(game.getId(), game.getName(), formatRolls(game.getRolls()), getScore(game)))
                .collect(Collectors.toList());
        model.addAttribute("games", gameDtos);
        return "view";
    }

    @GetMapping("/search/{id}")
    public ResponseEntity<BowlingGame> findById(@PathVariable Long id) {
        BowlingGame game = service.findById(id);
        if (game != null) {
            return ResponseEntity.ok(game);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }

    @GetMapping("/create")
    public String showCreateForm() {
        return "create";
    }

    public static class BowlingGameDto {
        private Long id;
        private String name;
        private List<int[]> rolls;
        private int score;

        public BowlingGameDto(Long id, String name, List<int[]> rolls, int score) {
            this.id = id;
            this.name = name;
            this.rolls = rolls;
            this.score = score;
        }

        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public List<int[]> getRolls() {
            return rolls;
        }

        public int getScore() {
            return score;
        }
    }
}
