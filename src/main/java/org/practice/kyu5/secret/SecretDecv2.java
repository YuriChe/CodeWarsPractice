package org.practice.kyu5.secret;
//работа с графом через матрицу
import java.util.*;

public class SecretDecv2  {
    ArrayList<Character> nodes = new ArrayList<>();

    int[][] createMatrix(char[][] triplets) {  //создание матрицы смежности

        nodes = new ArrayList<>(uniqueNodes(triplets));
        int nodesSize = nodes.size();
        int[][] nodeMatrix = new int[nodesSize][nodesSize];

        for (char[] triplet : triplets) {
            int from, to;
            from = nodes.indexOf(triplet[0]);
            for (int i = 1; i < 3; i++) {
                to = nodes.indexOf(triplet[i]);
                nodeMatrix[from][to] = 1;
                from = nodes.indexOf(triplet[i]);
            }
        }
        return nodeMatrix;
    }

    public Set<Character> uniqueNodes(char[][] triplets) { // уникальный набор символов Set
        Set<Character> nodes = new HashSet<>();
        for (char[] triplet : triplets) {
            for (char ch : triplet) {
                nodes.add(ch);
            }
        }
        return nodes;
    }

    public ArrayList<String> startNodes (int[][] matrixGraph) {  // поиск стартового нода
        ArrayList<String> res = new ArrayList<>();
        for (int col = 0; col < matrixGraph.length; col++) {
            boolean isStart = true;
            for (int row = 0; row < matrixGraph.length; row++) {
                if (matrixGraph[row][col] == 1) {
                    isStart = false;
                    break;
                }
            }
            if (isStart) {
                String start = nodes.get(col).toString();
                res.add(start);
            }
        }
        return res;
    }

//   public ArrayList<Point> bfs(int[][] matrixGraph, Point start) {  //обход графа в глубину BFS
//
//        ArrayList<Point> dist = new ArrayList<>();
//
//        Queue<Point> vertexs = new ArrayDeque<>();
//
//        vertexs.add(start);
//
//                while vertexs
//
//
//        return dist;
//    }

    public static void main(String[] args) {
        SecretDecv2 secretDecv2 = new SecretDecv2();
        char[][] triplets = new char[][]{  //masteringjavaprogrammingchallengesolutionsdevtechnology
                {'t', 's', 'f'},
                {'a', 's', 'u'},
                {'m', 'a', 'f'},
                {'a', 'i', 's'},
                {'s', 'u', 'p'},
                {'c', 'o', 'd'},
                {'p', 'r', 'o'},
                {'g', 'r', 'a'},
                {'r', 'a', 'm'},
                {'i', 'n', 'g'},
                {'j', 'a', 'v'},
                {'v', 'a', 's'},
                {'d', 'e', 'v'},
                {'e', 'l', 'o'},
                {'l', 'o', 'p'},
                {'p', 'e', 'r'},
                {'t', 'e', 'c'},
                {'h', 'n', 'o'},
                {'n', 'o', 'l'},
                {'o', 'g', 'y'},
                {'s', 'o', 'l'},
                {'u', 't', 'i'},
                {'o', 'n', 's'},
                {'i', 's', 'c'},
                {'c', 'h', 'a'},
                {'a', 'l', 'l'},
                {'l', 'e', 'n'},
                {'e', 'g', 'e'},
                {'g', 'n', 'd'},
                {'n', 'd', 'z'}
        };
        int[][] matrix = secretDecv2.createMatrix(triplets);
        System.out.print(" ");
        for (char ch : secretDecv2.nodes) {
            System.out.printf(" %c,", ch);
        }
        System.out.println();
        for (int i = 0; i < matrix.length; i++) {
            System.out.print(STR."\{secretDecv2.nodes.get(i)} ");
            for (int j = 0; j < matrix.length; j++) {
                System.out.printf("%d, ", matrix[i][j]);
            }
            System.out.println("]");
        }
        ArrayList<String> listStartNodes = new ArrayList<>(secretDecv2.startNodes(matrix));
        for (String p : listStartNodes) {
            System.out.printf("Start node: %s", p);
        }
    }
}
