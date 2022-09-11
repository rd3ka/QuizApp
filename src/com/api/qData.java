package com.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;

public class qData {
    protected String category, type, difficulty;
    private final String correctAnswer;
    private final String question;
    private final ArrayList <String> incorrectAnswers = new ArrayList<>();

    qData(JsonObject q) {
        this.category = q.get("category").toString();
        this.type = q.get("type").toString();
        this.difficulty = q.get("difficulty").toString();

        this.question = q.get("question").toString();
        this.correctAnswer = q.get("correct_answer").toString();

        JsonArray jq = q.getAsJsonArray("incorrect_answers");
        if (jq != null) {
            for(int i = 0; i < jq.size(); i++)
                this.incorrectAnswers.add(jq.get(i).toString());
        }
    }

    public String getQuestion() {
        return transform(this.question);
    }

    public ArrayList <String> getIncorrectAnswers() {
       return this.incorrectAnswers;
    }

    public boolean checkCorrectAnswer(String ans) {
        return ans.equals(transform(this.correctAnswer));
    }

    public String getCorrectAnswer() {
        return transform(this.correctAnswer);
    }

    protected String transform(String s) {
        return s.replaceAll("&quot;","\"")
                .replaceAll("&#039;","'")
                .replaceAll("\"","")
                .trim();
    }

    @Override
    public String toString() {
        return "qData{" +
                "category = " + category +
                ", type = " + type +
                ", difficulty = " + difficulty +
                ", correctAnswer = " + correctAnswer +
                ", question = " + question +
                ", incorrectAnswers = " + incorrectAnswers +
                '}';
    }
}
