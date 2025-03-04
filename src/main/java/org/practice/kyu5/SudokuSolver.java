package org.practice.kyu5;

import java.util.Arrays;

import static java.lang.StringTemplate.STR;

public class SudokuSolver {
    static final int EMPTY = 0;
    static final int SIZE = 9;

    public int[][] sudoku(int[][] puzzle) {

        int[][] solvingBoard = Arrays.stream(puzzle)
                        .map(int[]::clone)
                        .toArray(int[][]::new);

        solve(solvingBoard);
        return solvingBoard;
    }

    private boolean isValid(int[][] board, int num, int row, int col) {
        int boxRow = row - row % 3;
        int boxCol = col - col % 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i + boxRow][j + boxCol] == num) {
                    return false;
                }
            }
        }

        for (int i = 0; i < SIZE; i++) {
            if (board[row][i] == num || board[i][col] == num) {
                return false;
            }
        }
        return true;
    }

    private boolean solve(int[][] board) {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == EMPTY) {
                    for (int num = 1; num <= SIZE; num++) {
                        if (isValid(board, num, row, col)) {
                            board[row][col] = num;

                            if (solve(board)) {
                                return true;
                            }
                            board[row][col] = EMPTY;
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {

        SudokuSolver sudokuSolver = new SudokuSolver();

        int[][] puzzle = {
                {5, 3, 0, 0, 7, 0, 0, 0, 0},
                {6, 0, 0, 1, 9, 5, 0, 0, 0},
                {0, 9, 8, 0, 0, 0, 0, 6, 0},
                {8, 0, 0, 0, 6, 0, 0, 0, 3},
                {4, 0, 0, 8, 0, 3, 0, 0, 1},
                {7, 0, 0, 0, 2, 0, 0, 0, 6},
                {0, 6, 0, 0, 0, 0, 2, 8, 0},
                {0, 0, 0, 4, 1, 9, 0, 0, 5},
                {0, 0, 0, 0, 8, 0, 0, 7, 9}
        };
        long startTime = System.nanoTime(); // Запуск таймера

        int[][] solved = sudokuSolver.sudoku(puzzle);

        long endTime = System.nanoTime(); // Время после выполнения

        long duration = endTime - startTime; // Вычисление длительности в наносекундах
        System.out.println("Time taken: " + duration + " nanoseconds.");

        // Print the solved Sudoku
        for (int[] row : solved) {
            for (int num : row) {
                System.out.print(STR."\{num} ");
            }
            System.out.println();
        }
    }
}
