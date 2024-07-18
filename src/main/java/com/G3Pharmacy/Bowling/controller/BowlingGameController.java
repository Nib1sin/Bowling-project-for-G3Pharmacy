package com.G3Pharmacy.Bowling.controller;

import com.G3Pharmacy.Bowling.entities.BowlingGame;
import com.G3Pharmacy.Bowling.service.BowlingGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String createGame(@RequestParam String name, @RequestParam String rolls) {
        BowlingGame game = new BowlingGame();
        game.setName(name);
        game.setRolls(convertRollsStringToArray(rolls));
        service.save(game);
        return "redirect:/api/bowling/all";
    }

    private int[] convertRollsStringToArray(String rolls) {
        String[] rollStrings = rolls.split(",");
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
                .map(game -> new BowlingGameDto(game.getId(), game.getName(), arrayToString(game.getRolls())))
                .collect(Collectors.toList());
        model.addAttribute("games", gameDtos);
        return "view";
    }

    private String arrayToString(int[] rolls) {
        return Arrays.toString(rolls);
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
        private String rolls;

        public BowlingGameDto(Long id, String name, String rolls) {
            this.id = id;
            this.name = name;
            this.rolls = rolls;
        }

        public Long getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getRolls() {
            return rolls;
        }
    }
}
