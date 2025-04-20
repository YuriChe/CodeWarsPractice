package org.practice.kyu5.secret;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class SecretDecv3 {

    ArrayList<Character> nodes = new ArrayList<>();

    public Graph<Character> createGraph(char[][] triplets) {  //создание матрицы смежности из char

        Graph<Character> graph = new Graph();

        for (char[] triplet : triplets) {
            graph.addEdge(triplet[0], triplet[1]);
            graph.addEdge(triplet[1], triplet[2]);
        }
        return graph;
    }

    public Graph<Integer> createGraph(int[][] triplets) {  //создание матрицы смежности из INT

        Graph<Integer> graph = new Graph();

        for (int[] triplet : triplets) {
//            graph.addEdge(triplet[0], triplet[1]);
//            graph.addEdge(triplet[1], triplet[2]);
        }
        return graph;
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

    /*public ArrayList<String> startNodes(int[][] matrixGraph) {  // поиск стартового нода
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
    }*/

    public static void main(String[] args) {
        SecretDecv3 secretDecv3 = new SecretDecv3();
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
        BFS<Character> bfs = new BFS<>();
        bfs.setGraphHash(secretDecv3.createGraph(triplets).getGraphHash());

        System.out.println(STR."Расстояния от g: \{bfs.BFSdistance('g')}");
        System.out.print(bfs);

    }
}
