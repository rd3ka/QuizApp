package com.main;

import com.api.Quizz;

import java.util.InputMismatchException;
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
          catch (InputMismatchException exception) {
              System.out.println(exception.toString());
          }
          char play = 0;
          if (q.getNumOfQuestions() == 0) {
              System.out.print("Do you want to continue to play? y/n : ");
              play = new Scanner(System.in).nextLine().charAt(0);
              System.out.println();
          }
          if (play == 'y')
              q.init();
      }
    }

    public static void main(String[] args)  {
        Quizz q = new Quizz("multiple");
        play(q);
        System.out.println("GAME OVER!");
    }
}