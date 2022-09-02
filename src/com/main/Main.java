package com.main;

import com.api.Quizz;

public class Main {

    public static void play(Quizz q) {
      q.init();
      while(q.get()) {
          q.showQuestions();
          q.showAnswers();
      }
    }

    public static void main(String[] args)  {
        Quizz q = new Quizz("multiple");
        play(q);
    }
}
