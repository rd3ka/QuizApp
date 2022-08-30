package com.api;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {


    static String showQuestions(qData q) {
        return q.getQuestion();
    }

    static ArrayList<String> showAnswers(qData q) {
        return q.getAnswers();
    }

    static boolean checkAnswer(qData q, String myAnswer) {
        return q.checkCorrectAnswer(myAnswer);
    }

    static String correctAnswer(qData q) { return q.getCorrectAnswer(); }


    static void play(Quizz q) {
        q.init();
        System.out.println(showQuestions(q.get()));
        System.out.println(showAnswers(q.get()));
        String myAnswer = new Scanner(System.in).nextLine();
        System.out.println(checkAnswer(q.get(), myAnswer));
        System.out.println(correctAnswer(q.get()));
    }

    public static void main(String[] args)  {
        Quizz q = new Quizz("multiple");
        play(q);
    }
}
