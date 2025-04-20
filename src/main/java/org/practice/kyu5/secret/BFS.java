package org.practice.kyu5.secret;

import java.util.*;

public class BFS<T> extends Graph {

// находит расстояние от вершины до всех
    public HashMap<T, Integer> BFSdistance(T begin) {
        HashMap<T, Integer> dist = new HashMap<>();
        Queue<T> queue = new ArrayDeque<>();
        Set<T> visited = new HashSet<>();

        queue.offer(begin);
        visited.add(begin);
        dist.put(begin, 0);  // Начальная вершина на расстоянии 0

        while (!queue.isEmpty()) {
            T current = queue.poll();
            int currentDistance = dist.get(current);
            List<T> neighbours = (List<T>) getGraphHash().getOrDefault(current, new ArrayList<>());
            // Обходим всех соседей текущей вершины
            for (T neighbor : neighbours) {
                if (!visited.contains(neighbor)) {
                    queue.offer(neighbor);
                    visited.add(neighbor);
                    dist.put(neighbor, currentDistance + 1);  // Увеличиваем расстояние от начальной вершины
                }
            }
        }
        return dist;
    }

    //находит кратчайший путь. Пустой = нет пути
    public ArrayList<T> BFSroute(T from, T to) {
        ArrayList<T> route = new ArrayList<>();
        HashMap<T, T> parent = new HashMap<>();
        HashMap<T, Integer> dist = new HashMap<>();
        Queue<T> queue = new ArrayDeque<>();
        Set<T> visited = new HashSet<>();

        queue.offer(from);
        visited.add(from);
        dist.put(from, 0);  // Начальная вершина на расстоянии 0

        while (!queue.isEmpty()) {
            T current = queue.poll();
            int currentDistance = dist.get(current);

            List<T> neighbours = (List<T>) getGraphHash().getOrDefault(current, new ArrayList<>());
            // Обходим всех соседей текущей вершины
            for (T neighbor : neighbours) {
                if (!visited.contains(neighbor)) {
                    queue.offer(neighbor);
                    visited.add(neighbor);
                    parent.put(neighbor, current);
                    dist.put(neighbor, currentDistance + 1);  // Увеличиваем расстояние от начальной вершины
                }
                // Если достигли целевой вершины, можно сразу завершить поиск
                if (neighbor.equals(to)) {
                    queue.clear();
                    break;
                }
            }
        }
        if (visited.contains(to)) {
            //если есть путь
            T current = to;
            while (current != null) {
                route.addFirst(current);
                current = parent.get(current);
            }
        }
        return route;
    }
}
