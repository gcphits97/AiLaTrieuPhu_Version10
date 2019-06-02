package com.example.ailatrieuphu_version10.highScore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HighScoreModel {
    private String username;
    private int bestPlay;
    private String bestReward;
    private String time;
    private double tyle;
}
