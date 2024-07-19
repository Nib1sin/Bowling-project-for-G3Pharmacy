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
    public String createGame(@RequestParam String name, @RequestParam String rolls, Model model) {
        BowlingGame game = new BowlingGame();
        game.setName(name);
        game.setRolls(convertRollsStringToArray(rolls));
        service.save(game);

        List<BowlingGame> games = service.findAll();
        List<BowlingGameDto> gameDtos = games.stream()
                //.map(g -> new BowlingGameDto(g.getId(), g.getName(), arrayToString(g.getRolls()), getScore(g)))
                .map(g -> new BowlingGameDto(g.getId(), g.getName(), formatRolls(g.getRolls()), getScore(g)))
                .collect(Collectors.toList());
        model.addAttribute("games", gameDtos);
        return "view";
    }

    private int[] convertRollsStringToArray(String rolls) {
        String[] rollStrings = rolls.replaceAll("[\\[\\]]", "").split(",");
        int[] rollArray = new int[rollStrings.length];
        for (int i = 0; i < rollStrings.length; i++) {
            rollArray[i] = Integer.parseInt(rollStrings[i].trim());
        }
        return rollArray;
    }

    @GetMapping("/all")
    public String findAllGames(Model model){
        List<BowlingGame> games = service.findAll();
        List<BowlingGameDto> gameDtos = games.stream()
                //.map(game -> new BowlingGameDto(game.getId(), game.getName(), arrayToString(game.getRolls()), getScore(game)))
                .map(game -> new BowlingGameDto(game.getId(), game.getName(), formatRolls(game.getRolls()), getScore(game)))
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

    private String arrayToString(int[] rolls) {
        return Arrays.toString(rolls);
    }

    private int getScore(BowlingGame game) {
        BowlingGameRollService rollService = new BowlingGameRollService(game);
        return rollService.score();
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
        //private String rolls;
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

        /*
        public String getRolls() {
            return rolls;
        }
        */

        public List<int[]> getRolls() {
            return rolls;
        }

        public int getScore() {
            return score;
        }
    }
}
