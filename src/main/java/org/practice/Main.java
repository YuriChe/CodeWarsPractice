package org.practice;

public class Main {
    public static void main(String[] args) {

        for (int i = 0; i < 9; i++) {
            int boxRow = i - i % 3;
            System.out.println(boxRow);
        }
    }
}