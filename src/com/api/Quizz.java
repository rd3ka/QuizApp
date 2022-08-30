package com.api;

import com.google.gson.*;

import java.io.InputStream;
import java.net.URL;
import java.util.*;

public class Quizz {
    private String url = "https://opentdb.com/api.php?";
    private final int NumOfQuestions = 10;
    final private Stack <qData> qstack = new Stack<>();

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
            for (int i = 0; i < this.NumOfQuestions; i++)
                this.qstack.add(new qData((JsonObject) contents.get("results")
                        .getAsJsonArray()
                        .get(0)));
        }
    }

    public final synchronized qData get() {
        if (qstack.isEmpty())
            this.init();
        return qstack.pop();
    }

   private void showInfo() {
       System.out.println(this.url);
       System.out.println(this.qstack.size());
       qData samp = qstack.peek();
       System.out.println(samp.getQuestion());
       System.out.println(samp.getAnswers());
   }
}
