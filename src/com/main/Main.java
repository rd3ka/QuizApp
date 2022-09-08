package com.main;

import com.api.Quizz;
import java.util.Scanner;
public class Main {

    public static void play(Quizz q) {
      q.init();
      while(q.get()) {
          q.showQuestions();
          q.showAnswers();
          System.out.println(q.checkAnswer(new Scanner(System.in).nextLine().charAt(0)));
      }
    }

    public static void main(String[] args)  {
        Quizz q = new Quizz("multiple");
        play(q);
    }
}
