package com.codecool.polishdraughts;

import com.codecool.polishdraughts.screens.IntroScreen;

import java.util.concurrent.TimeUnit;

public class App {

    public static void main(String[] args) {
        IntroScreen intro = new IntroScreen();
        intro.display();
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException ie) {
            System.out.println("sleep is not working");
        }
        intro.menu();
    }
}
