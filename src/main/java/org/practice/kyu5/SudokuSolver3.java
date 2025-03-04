package org.practice.kyu5;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.IntStream;

public class SudokuSolver3 {

    static final int EMPTY = 0;
    static final int SIZE = 9;

    public int[][] sudoku(int[][] puzzle) {

        int[][] solvingBoard = Arrays.stream(puzzle)
                .map(int[]::clone)
                .toArray(int[][]::new);

        solve(solvingBoard);
        return solvingBoard;
    }

    private List<Integer> possibleValues(int row, int col, int[][] board) {
        final HashSet<Integer> impossibleNumRow = new HashSet<>(9);
        for (int i = 0; i < SIZE; i++) {
            impossibleNumRow.add(board[row][i]);
            impossibleNumRow.add(board[i][col]);
        }

        int boxRow = row - row % 3;
        int boxCol = col - col % 3;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                impossibleNumRow.add(board[i + boxRow][j + boxCol]);
            }
        }

        return IntStream.rangeClosed(1, 9)
                .filter(value -> !impossibleNumRow.contains(value))
                .boxed()
                .toList();
    }

    private boolean solve(int[][] board) {

        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {

                if (board[row][col] == EMPTY) {
                    for (int num : (possibleValues(row, col, board))) {
                        board[row][col] = num;

                        if (solve(board)) {
                            return true;
                        }
                        board[row][col] = EMPTY;
                    }
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        SudokuSolver3 sudokuSolver = new SudokuSolver3();

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
        int[][] solved = sudokuSolver.sudoku(puzzle);

        for (int[] row : solved) {
            for (int num : row) {
                System.out.print(STR."\{num} ");
            }
            System.out.println();
        }
    }
}
