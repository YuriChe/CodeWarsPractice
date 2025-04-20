package org.practice.kyu5;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.practice.kyu5.secret.Graph;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {

    private Graph<Character> graph;

    @BeforeEach
    void setUp() {
        graph = new Graph<>();
    }

    @Test
    void testAddVertex() {
        graph.addVertex('A');
        assertTrue(graph.getGraphHash().containsKey('A'));
    }

    @Test
    void testAddEdge() {
        graph.addEdge('A', 'B');
        assertTrue(graph.getGraphHash().get('A').contains('B'));
    }

    @Test
    void testFindFirst() {
        graph.addEdge('A', 'B');
        graph.addEdge('C', 'B');
        Set<Character> firstVertices = graph.findFirst();
        assertEquals(Set.of('A', 'C'), firstVertices);
    }

    @Test
    void testToString() {
        graph.addEdge('A', 'B');
        graph.addEdge('C', 'D');
        String expected = "A -> [B]\nB -> []\nC -> [D]\nD -> []";
        String actual = graph.toString();
        assertEquals(expected, actual);
    }

    @Test
    void testFindFirstWithEmptyGraph(){
        graph.addEdge('A', 'B');
        graph.addEdge('B', 'C');
        graph.addEdge('D', 'C');
        Set<Character> firstVertices = graph.findFirst();
        assertEquals(Set.of('A', 'D'), firstVertices);
    }


}
