package org.practice.kyu5;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.practice.kyu5.secret.DFS;

import java.util.ArrayList;
import java.util.Arrays;
import static org.junit.jupiter.api.Assertions.*;

public class DFSTest {
    private DFS dfs;

    @BeforeEach
    void setUp() {
        dfs = new DFS();
    }

    @Test
    void testSimplePath() {
        // Создаем простой граф: A -> B -> C -> D
        dfs.addEdge('A', 'B');
        dfs.addEdge('B', 'C');
        dfs.addEdge('C', 'D');

        ArrayList<Character> expectedPath = new ArrayList<>(Arrays.asList('A', 'B', 'C', 'D'));
        ArrayList<Character> actualPath = dfs.DFS('A', 'D');

        assertEquals(expectedPath, actualPath, "Путь должен быть A -> B -> C -> D");
    }

    @Test
    void testMultiplePaths() {
        // Создаем граф с несколькими путями: A -> B -> D, A -> C -> D
        dfs.addEdge('A', 'B');
        dfs.addEdge('A', 'C');
        dfs.addEdge('B', 'D');
        dfs.addEdge('C', 'D');

        ArrayList<Character> actualPath = dfs.DFS('A', 'D');

        // Путь может быть либо A -> B -> D, либо A -> C -> D в зависимости от порядка обхода
        assertTrue(
                actualPath.equals(new ArrayList<>(Arrays.asList('A', 'B', 'D'))) ||
                        actualPath.equals(new ArrayList<>(Arrays.asList('A', 'C', 'D'))),
                "Путь должен быть одним из возможных путей от A до D"
        );
    }

    @Test
    void testNoPath() {
        // Создаем граф без пути между вершинами: A -> B, C -> D
        dfs.addVertex('H');
        dfs.addEdge('A', 'B');
        dfs.addEdge('C', 'D');

        ArrayList<Character> path = dfs.DFS('A', 'D');

        assertTrue(path.isEmpty(), "Путь не должен существовать");
    }

    @Test
    void testPathWithCycle() {
        // Создаем граф с циклом: A -> B -> C -> A -> D
        dfs.addEdge('A', 'B');
        dfs.addEdge('B', 'C');
        dfs.addEdge('C', 'D');
        dfs.addEdge('C', 'E');
        dfs.addEdge('E', 'F');
        dfs.addEdge('D', 'E');
        dfs.addEdge('D', 'A');
        dfs.addEdge('D', 'C');

        ArrayList<Character> expectedPath = new ArrayList<>(Arrays.asList('A', 'B', 'C', 'D', 'E', 'F'));
        ArrayList<Character> actualPath = dfs.DFS('A', 'F');
        System.out.println(actualPath);

        assertEquals(expectedPath, actualPath, "Должен найти прямой путь A -> D, несмотря на цикл");
    }

    @Test
    void testSameStartAndEnd() {
        // Тестируем случай, когда начальная и конечная вершины совпадают
        dfs.addEdge('A', 'B');

        ArrayList<Character> expectedPath = new ArrayList<>(Arrays.asList('A'));
        ArrayList<Character> actualPath = dfs.DFS('A', 'A');

        assertEquals(expectedPath, actualPath, "Путь должен содержать только вершину A");
    }

    @Test
    void testComplexGraph() {
        // Создаем более сложный граф
        dfs.addEdge('A', 'B');
        dfs.addEdge('A', 'C');
        dfs.addEdge('B', 'D');
        dfs.addEdge('C', 'E');
        dfs.addEdge('D', 'F');
        dfs.addEdge('E', 'F');

        ArrayList<Character> actualPath = dfs.DFS('A', 'F');

        // В зависимости от порядка обхода, путь может быть разным
        assertTrue(
                actualPath.equals(new ArrayList<>(Arrays.asList('A', 'B', 'D', 'F'))) ||
                        actualPath.equals(new ArrayList<>(Arrays.asList('A', 'C', 'E', 'F'))),
                "Путь должен быть одним из возможных путей от A до F"
        );
    }

    @Test
    void testInvalidVertex() {
        // Тестируем поиск пути для несуществующей вершины
        dfs.addEdge('A', 'B');

        ArrayList<Character> path = dfs.DFS('A', 'Z');

        assertTrue(path.isEmpty(), "Путь не должен существовать для несуществующей вершины");
    }

    @Test
    void testDirectedGraph() {
        // Тестируем направленный граф: A -> B -> C, но нет пути C -> A
        dfs.addEdge('A', 'B');
        dfs.addEdge('B', 'C');

        ArrayList<Character> pathAC = dfs.DFS('A', 'C');
        ArrayList<Character> pathCA = dfs.DFS('C', 'A');

        assertEquals(new ArrayList<>(Arrays.asList('A', 'B', 'C')), pathAC, "Должен быть путь от A до C");
        assertTrue(pathCA.isEmpty(), "Не должно быть пути от C до A");
    }
}
