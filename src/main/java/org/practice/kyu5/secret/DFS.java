package org.practice.kyu5.secret;

import java.util.*;

public class DFS<T> extends Graph<T> {
    enum Color {WHITE, GRAY, BLACK}

    public ArrayList<T> DFS(T start, T stop) { //поиск пути алгоритмом DFS

        HashMap<T, Color> colors = new HashMap<>();
        HashMap<T, T> parent = new HashMap<>();// вершина - предок
        ArrayList<T> path = new ArrayList<>();//путь по вершинам

        for (T vertex : getGraphHash().keySet()) {
            colors.put(vertex, Color.WHITE);
            parent.put(vertex, null);
        }
        boolean way = false;
        if (!start.equals(stop)) {
            colors.put(start, Color.GRAY);
            way = DFSgo(start, stop, colors, parent); //запуск
        } else path.addFirst(start);

        if (way) {
            //если есть путь
            T current = stop;
            while (current != null) {
                path.addFirst(current);
                current = parent.get(current);
            }
        }
        return path;
    }

    private boolean DFSgo(T current, T stop,
                          HashMap<T, Color> colors,
                          HashMap<T, T> parent) {

        List<T> neighbours = getGraphHash().get(current);

        for (T neighbour : neighbours) {
            if (neighbour == null) {
                // Если текущая вершина не имеет соседей, просто возвращаем false
                return false;
            }
            if (neighbour.equals(stop)) {
                parent.put(neighbour, current);
                colors.put(neighbour, Color.BLACK);
                return true;
            }

            switch (colors.get(neighbour)) {
                case WHITE: {
                    parent.put(neighbour, current);
                    colors.put(neighbour, Color.GRAY);
                    if (DFSgo(neighbour, stop, colors, parent)) {
                        return true;
                    }
                    break;
                }
                case GRAY: //если циклы
                case BLACK: {
                    System.out.println(STR."Обнаружен цикл через \{neighbour}");
                    return false;
                }
            }
        }
        //нет пути
        colors.replace(current, Color.BLACK);
        return false;
    }
}
