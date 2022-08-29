package com.api;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;
import java.util.Collections;

public class qData {
    protected String category, type, difficulty;
    private final String correctAnswer;
    private final String question;
    ArrayList <String> incorrectAnswers = new ArrayList<>();

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

    public ArrayList <String> getAnswers() {
        ArrayList <String> ans = new ArrayList<>(this.incorrectAnswers);
        ans.add(correctAnswer);
        Collections.shuffle(ans);
        return ans;
    }

    public boolean checkCorrectAnswer(String ans) {
        return ans.equals(this.correctAnswer);
    }

    private String transform(String s) {
        return s.replaceAll("&quot;","\"")
                .replaceAll("&#039;","'")
                .trim();
    }

    @Override
    public String toString() {
        return "qData{" +
                "category=" + category +
                ", type=" + type +
                ", difficulty=" + difficulty +
                ", correctAnswer=" + correctAnswer +
                ", question=" + question +
                ", incorrectAnswers=" + incorrectAnswers +
                '}';
    }
}