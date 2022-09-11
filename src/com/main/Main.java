package com.main;

import com.api.Quizz;
import java.util.Scanner;
public class Main {

    public static void play(Quizz q) {
      q.init();
      while(q.get()) {
          q.showQuestions();
          q.showAnswers();
          char toSend;
          try {
              toSend = new Scanner(System.in).nextLine().charAt(0);
              if (toSend < 'a' || toSend > 'd') continue;
              System.out.println(q.checkAnswer(toSend));
          }
          catch (NumberFormatException exception) {
              System.out.println("Wrong Format Provided");
          }
      }
    }

    public static void main(String[] args)  {
        Quizz q = new Quizz("multiple");
        play(q);
    }
}
