package org.practice.kyu5.secret;

import java.util.*;

public class TopologicalSort<T> extends DFS<T> {
    Deque<T> dec = new ArrayDeque<>();
    HashMap<T, Color> colors = new HashMap<>();

    public ArrayList<T> topologicalSort() {
        /*for (T vertex : getGraphHash().keySet()) {
            colors.put(vertex, Color.WHITE);
        }*/
        for (T vertex : getGraphHash().keySet()) {
            if (!colors.containsKey(vertex)) {
                topS(vertex);
            }
        }

        return new ArrayList<>(dec);
    }

    public void topS(T vertex) {
        colors.put(vertex, Color.GRAY);
        for (T neighbour : getGraphHash().getOrDefault(vertex, new ArrayList<>())) {
            if (!colors.containsKey(neighbour)) {
                topS(neighbour);
            } else if (colors.get(neighbour) == Color.GRAY) {
                throw new IllegalArgumentException("Граф содержит цикл");
            }
        }
        colors.put(vertex, Color.BLACK);
        dec.push(vertex);
    }
}
