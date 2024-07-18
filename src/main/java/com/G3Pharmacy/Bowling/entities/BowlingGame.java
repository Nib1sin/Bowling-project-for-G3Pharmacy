package com.G3Pharmacy.Bowling.entities;

import com.G3Pharmacy.Bowling.service.RollsConverterService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Arrays;

@Data
@Entity
@Builder
@Table(name = "bowling_games")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class BowlingGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Convert(converter = RollsConverterService.class)
    private int[] rolls = new int[21];

}
