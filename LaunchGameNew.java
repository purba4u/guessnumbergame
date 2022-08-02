package com.example.demo;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

class GusserNum {
    int guessNum;
    public int guessNumber() {
        Scanner sc = new Scanner(System.in);
        guessNum = sc.nextInt();
        return guessNum;
    }
}
class PlayerNum {
    int playerNum;
    public int playerNumber() {
        Random rand = new Random();
        int playerNum = rand.nextInt(100);
        return playerNum;
    }
}
class UmpireAction {
    public static int guessNum;
    ArrayList<Integer> players = new ArrayList<Integer>();
    public void collectGuessedNum() {
        System.out.println("Enter the number by guesser within 0 to 99");
        int maxTries = 3;
        for (int count = 0; count < maxTries; count++) {
            GusserNum gs = new GusserNum();
            guessNum = gs.guessNumber();
            if (0 <= guessNum && guessNum < 100) {
                count = maxTries;//don't retry
            } else if (count +1 < maxTries){
                System.out.println("You haven't entered a number between 0 - 99. You have " + (maxTries - count - 1)  + " attempt(s) left! Please try again:");
            } else {
                System.out.println("3 failed attempts. Please restart the game.");
                System.exit(1);
            }
        }
    }

    public void collectPlayerNumAndCompare() {
        System.out.println("Enter how many random players will play the game:");
        Scanner sc = new Scanner(System.in);
        int playerCount = sc.nextInt();
        int closeResult = 100;
        int closePlayer = 0;
        for (int i = 0; i < playerCount; i++) {
            PlayerNum p = new PlayerNum();
            int num = p.playerNumber();
            System.out.println("player " + (i+1) + ": " + num);
            if (num == guessNum) {
                players.add(i+1);
            }
            else {
                int diff = guessNum - num;
                diff = Math.abs(diff);
                if (closeResult > diff) {
                    closeResult = diff;
                    closePlayer = i+1;
                }
            }
        }
        if (players.size() != 0) {
            System.out.println("Below player(s) are the winners:");
            for (int i = 0; i < players.size(); i++) {
                System.out.print("player " + players.get(i) + "\n");
            }
        } else {
            System.out.println("Game lost! But we want to announce the first closest match player from the list: " + " player " + closePlayer + ". The difference from the guessed number is: " + closeResult);
        }
    }
}


public class LaunchGameNew {
    public static void main(String[] args) throws IOException {
        UmpireAction um = new UmpireAction();
        um.collectGuessedNum();
        um.collectPlayerNumAndCompare();
    }
}