package com.example.ailatrieuphu_version10.highScore;

import com.example.ailatrieuphu_version10.login.model.UserModel;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HighScoreModel {
    private int user_model_id;
    private UserModel userModel;
    private int bestplay;
    private String bestreward;
    private String playtime;
    private double tyle;

    public HighScoreModel(int user_model_id, int bestplay, String bestreward) {
        this.user_model_id = user_model_id;
        this.bestplay = bestplay;
        this.bestreward = bestreward;
    }
}
