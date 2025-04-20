package org.practice.kyu5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.practice.kyu5.secret.BFS;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BFSTest {

    private BFS graph;

    @BeforeEach
    void setUp() {
        graph = new BFS<>();
    }

    @Test
    void testBFSdistance() {
        graph.addEdge('A', 'B');
        graph.addEdge('B', 'C');
        HashMap<Character, Integer> distances = graph.BFSdistance('A');
        assertEquals(2, distances.get('C'));
    }

    @Test
    public void testBFSroute() {
        graph.addEdge('A', 'B');
        graph.addEdge('B', 'C');
        graph.addEdge('C', 'D');
        graph.addEdge('D', 'E');
        graph.addEdge('B', 'E');
        graph.addEdge('C', 'A');
        // Ожидаемый путь от 'A' до 'E'
        List<Character> expectedRoute = List.of('A', 'B', 'E');

        // Получаем путь с помощью BFS
        List<Character> route = graph.BFSroute('A', 'E');

        // Проверяем, совпадает ли полученный путь с ожидаемым
        assertEquals(expectedRoute, route);
    }

    @Test
    public void testBFSrouteNoPath() {

        graph.addEdge('A', 'B');
        graph.addEdge('B', 'C');
        graph.addEdge('C', 'D');
        graph.addEdge('D', 'E');
        graph.addEdge('B', 'E');
        graph.addEdge('C', 'A');
        // Ожидаемый путь от 'A' до 'F' (пути нет)
        List<Character> expectedRoute = new ArrayList<>();

        // Получаем путь с помощью BFS
        List<Character> route = graph.BFSroute('A', 'F');

        // Проверяем, что путь пустой
        assertEquals(expectedRoute, route);
    }

    @Test
    public void testBFSrouteOne() {
        graph.addEdge('A', 'B');
        graph.addEdge('B', 'C');
        graph.addEdge('C', 'D');
        graph.addEdge('D', 'E');
        graph.addEdge('B', 'E');
        graph.addEdge('C', 'A');
        // Ожидаемый путь от 'A' до 'E'
        List<Character> expectedRoute = List.of('D');

        // Получаем путь с помощью BFS
        List<Character> route = graph.BFSroute('D', 'D');

        // Проверяем, совпадает ли полученный путь с ожидаемым
        assertEquals(expectedRoute, route);
    }
}
