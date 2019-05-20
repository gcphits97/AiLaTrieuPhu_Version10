package com.example.ailatrieuphu_version10.Player.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class QuestionModel {
    private int id;
    private String question;
    private String answera;
    private String answerb;
    private String answerc;
    private String answerd;
    private String rightanswer;
    private String questiontype;
    private String questiongroup;
}
