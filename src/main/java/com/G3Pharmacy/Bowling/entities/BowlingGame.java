package com.G3Pharmacy.Bowling.entities;

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
public class BowlingGame {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ElementCollection
    private int[] rolls = new int[21];

}
