package org.practice.kyu5;

import org.junit.Before;
import org.junit.Test;
import org.practice.kyu5.secret.TopologicalSort;

import static org.junit.Assert.*;

import java.util.*;

public class TopSortTest {

    private TopologicalSort<Character> topologicalSort;

    @Before
    public void setUp() {
        topologicalSort = new TopologicalSort<Character>();
        topologicalSort.addEdge('t', 's');
        topologicalSort.addEdge('s', 'f');
        topologicalSort.addEdge('a', 's');
        topologicalSort.addEdge('s', 'u');
        topologicalSort.addEdge('m', 'a');
        topologicalSort.addEdge('a', 'f');
        topologicalSort.addEdge('a', 'i');
        topologicalSort.addEdge('i', 's');
        topologicalSort.addEdge('s', 'u');
        topologicalSort.addEdge('p', 's');
        topologicalSort.addEdge('c', 'o');
        topologicalSort.addEdge('o', 'd');
        topologicalSort.addEdge('p', 'r');
        topologicalSort.addEdge('r', 'o');
        topologicalSort.addEdge('g', 'r');
        topologicalSort.addEdge('r', 'a');
//        topologicalSort.addEdge('a', 'm');
//        topologicalSort.addEdge('i', 'n');
//        topologicalSort.addEdge('n', 'g');
//        topologicalSort.addEdge('j', 'a');
//        topologicalSort.addEdge('a', 'v');
//        topologicalSort.addEdge('v', 'a');
//        topologicalSort.addEdge('s', 'a');
//        topologicalSort.addEdge('d', 'e');
//        topologicalSort.addEdge('e', 'v');
//        topologicalSort.addEdge('e', 'l');
//        topologicalSort.addEdge('l', 'o');
//        topologicalSort.addEdge('o', 'p');
//        topologicalSort.addEdge('p', 'e');
//        topologicalSort.addEdge('r', 'e');
//        topologicalSort.addEdge('t', 'e');
//        topologicalSort.addEdge('c', 'h');
//        topologicalSort.addEdge('h', 'n');
//        topologicalSort.addEdge('n', 'o');
//        topologicalSort.addEdge('o', 'l');
//        topologicalSort.addEdge('l', 'o');
//        topologicalSort.addEdge('g', 'y');
//        topologicalSort.addEdge('s', 'o');
//        topologicalSort.addEdge('l', 'u');
//        topologicalSort.addEdge('t', 'i');
//        topologicalSort.addEdge('u', 't');
//        topologicalSort.addEdge('i', 'o');
//        topologicalSort.addEdge('n', 's');
//        topologicalSort.addEdge('i', 's');
//        topologicalSort.addEdge('c', 'c');
//        topologicalSort.addEdge('h', 'a');
//        topologicalSort.addEdge('a', 'l');
//        topologicalSort.addEdge('l', 'l');
//        topologicalSort.addEdge('l', 'e');
//        topologicalSort.addEdge('n', 'e');
//        topologicalSort.addEdge('g', 'e');
//        topologicalSort.addEdge('e', 'g');
//        topologicalSort.addEdge('n', 'd');
//        topologicalSort.addEdge('d', 'z');
    }

    @Test
    public void testTopologicalSort() {
        ArrayList<Character> result = topologicalSort.topologicalSort();
        String stringToInsert = "masteringjavaprogrammingchallengesolutionsdevtechnology";

        // Преобразуем строку в массив символов
        char[] characters = stringToInsert.toCharArray();

        ArrayList<Character> expected = new ArrayList<>();
        for (char c : characters) {
            expected.add(c);
        }
        assertEquals(expected, result);
    }

    /*@Test(expected = IllegalArgumentException.class)
    public void testCycleDetection() {
        TopologicalSort<String> cyclicSort = new TopologicalSort<String>() {
            @Override
            public Map<String, List<String>> getGraphHash() {
                Map<String, List<String>> graph = new HashMap<>();
                graph.put("A", Arrays.asList("B"));
                graph.put("B", Arrays.asList("C"));
                graph.put("C", Arrays.asList("A")); // Цикл A -> B -> C -> A
                return graph;
            }
        };
        cyclicSort.topologicalSort();
    }*/
}
