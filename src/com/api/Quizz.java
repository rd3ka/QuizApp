package com.api;

import com.google.gson.*;

import java.io.InputStream;
import java.net.URL;
import java.util.*;

public class Quizz {
    private String url = "https://opentdb.com/api.php?";
    private final int NumOfQuestions = 10;
    final private Stack <qData> qstack = new Stack<>();
    private qData qstackFrame;

    Quizz(String category, String difficulty, String type) {
        this.url = this.url.concat("amount=" + this.NumOfQuestions
                + "&" +
                "category=" + category +
                "difficulty=" + difficulty
                + "&" +
                "type=" + type);
    }

    Quizz(String difficulty, String type) {
        this.url = this.url.concat("amount=" + this.NumOfQuestions
                + "&" +
                "category=31" +
                "difficulty=" + difficulty
                + "&" +
                "type=" + type);
    }

    public Quizz(String type) {
        this.url = this.url.concat("amount=" + this.NumOfQuestions
                + "&" +
                "category=31"
                + "&" +
                "type=" + type);
    }


    final protected String checkUrl() {
        return this.url;
    }

    public final synchronized void init()  {
        JsonObject contents = null;
        try (InputStream is = new URL(this.url).openStream()) {
            contents = (JsonObject) JsonParser.parseString(new String(is.readAllBytes()));

        } catch (Exception ignored) {
        }
        if (contents != null) {
            for (int i = 0; i < this.NumOfQuestions; i++) {
                this.qstack.add(new qData((JsonObject) contents.get("results")
                        .getAsJsonArray()
                        .get(i)));
            }
        }
    }
    public synchronized boolean get() {
        if (this.qstack.isEmpty())
            return false;
        this.qstackFrame = this.qstack.pop();
        return true;
    }

    public synchronized void showQuestions() {
        System.out.println(this.qstackFrame.getQuestion());
    }

    public void showAnswers() {
        ArrayList <String> qans = this.qstackFrame.getIncorrectAnswers();
        qans.add(this.qstackFrame.getCorrectAnswer());
        Collections.shuffle(qans);
        Map <Character, String> aq = new HashMap<>();
        for(int i = 0; i < qans.size(); i++)
            aq.put((char) ('a' + i), this.qstackFrame.transform(qans.get(i)));
        System.out.println(aq);
    }
}
