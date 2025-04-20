package org.practice.kyu5.secret;

import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Graph <T> {

    protected HashMap<T, List<T>> graphHash;

    public Graph() {
        graphHash = new HashMap<>();
    }

    public HashMap<T, List<T>> getGraphHash() {
        return graphHash;
    }

    public void setGraphHash(HashMap<T, List<T>> graphHash) {
        this.graphHash = graphHash;
    }

    public void addVertex(T ch) {
        graphHash.putIfAbsent(ch, new ArrayList<>());
    }

    public void addEdge(T from, T to) {
        graphHash.computeIfAbsent(from, k -> new ArrayList<T>()).add(to);
        graphHash.putIfAbsent(to, new ArrayList<>());
    }

    public Set<T> findFirst() { // поиск начальной вершины, не имеет входных ребер
        Set<T> haveIn = new HashSet<>();
        Set<T> vertexesNoIn = new HashSet<>(graphHash.keySet());
        for (T ch : graphHash.keySet()) {
            haveIn.addAll(graphHash.get(ch));
        }
        vertexesNoIn.removeAll(haveIn);
        return vertexesNoIn;
    }

    @Override
    public String toString() {
        return graphHash.entrySet().stream()
                .map(entry -> entry.getKey() + " -> " + entry.getValue())
                .collect(Collectors.joining("\n"));
    }

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
//        Graph<Character> graph = secretDecv3.createGraph(triplets);
        BFS<Character> bfs = new BFS<>();
        bfs.setGraphHash(secretDecv3.createGraph(triplets).getGraphHash());
        bfs.findFirst().stream().map(ch -> STR."Расстояния от \{ch}: \{bfs.BFSdistance((Character) ch)}").forEach(System.out::println);
        System.out.println(bfs);
    }
}
