package org.practice.kyu5.secret;

import java.util.*;

public class SecretDevv4 {

    public static String recoverSecret(char[][] triplets) {

        // 1. Собрать все уникальные символы и построить граф + посчитать in-degree
        Map<Character, Set<Character>> adj = new HashMap<>(); // Список смежности (исходящие ребра)
        Map<Character, Integer> inDegree = new HashMap<>(); // Входящие степени
        Set<Character> allChars = new HashSet<>(); // Множество всех уникальных символов

        // Инициализация для всех символов в триплетах
        for (char[] triplet : triplets) {
            char a = triplet[0];
            char b = triplet[1];
            char c = triplet[2];

            // Добавляем символы в множество и инициализируем структуры
            for (char ch : new char[]{a, b, c}) {
                allChars.add(ch);
                adj.putIfAbsent(ch, new HashSet<>()); // Гарантируем, что у каждого символа есть запись в adj
                inDegree.putIfAbsent(ch, 0);        // Гарантируем, что у каждого символа есть запись в inDegree
            }

            // Добавляем ребро a -> b
            // adj.get(a).add(b) возвращает true, если ребро было добавлено (т.е. его не было)
            if (adj.get(a).add(b)) {
                inDegree.put(b, inDegree.get(b) + 1); // Увеличиваем in-degree для b
            }

            // Добавляем ребро b -> c
            if (adj.get(b).add(c)) {
                inDegree.put(c, inDegree.get(c) + 1); // Увеличиваем in-degree для c
            }
        }

        // 2. Инициализация очереди узлами с нулевой входной степенью
        Queue<Character> queue = new LinkedList<>(); // Используем LinkedList как Queue
        for (char ch : allChars) {
            if (inDegree.get(ch) == 0) {
                queue.add(ch);
            }
        }

        // 3. Основной цикл топологической сортировки
        StringBuilder result = new StringBuilder();
        while (!queue.isEmpty()) {
            char u = queue.poll();
            result.append(u);

            // Обрабатываем соседей (символы, которые должны идти после u)
            // Проверка adj.containsKey(u) не обязательна из-за putIfAbsent выше,
            // но может быть полезна для ясности или если бы инициализация была другой.
            // if (adj.containsKey(u)) { // Можно добавить для надежности
            for (char v : adj.get(u)) {
                inDegree.put(v, inDegree.get(v) - 1); // Уменьшаем входную степень соседа
                if (inDegree.get(v) == 0) {
                    queue.add(v); // Добавляем соседа в очередь, если его степень стала 0
                }
            }
            // }
        }

        // 4. Проверка на циклы (и полноту результата)
        if (result.length() != allChars.size()) {
            // Этого не должно произойти согласно условиям задачи, но проверка важна
            throw new RuntimeException("Cycle detected in triplets or insufficient data.");
        }

        return result.toString();
    }

    // --- main метод для тестирования ---
    public static void main(String[] args) {
        char[][] triplets1 = new char[][]{
                {'t', 'u', 'p'}, {'w', 'h', 'i'}, {'t', 's', 'u'},
                {'a', 't', 's'}, {'h', 'a', 'p'}, {'t', 'i', 's'},
                {'w', 'h', 's'}
        };
        System.out.println("Test 1 Input: " + Arrays.deepToString(triplets1));
        System.out.println("Test 1 Recovered secret: " + recoverSecret(triplets1)); // Ожидаемый: whatisup

        System.out.println("---");

        char[][] triplets2 = new char[][]{
                {'m', 'a', 's'}, {'a', 's', 't'}, {'s', 't', 'e'},
                {'t', 'e', 'r'}, {'e', 'r', 'i'}, {'r', 'i', 'n'},
                {'i', 'n', 'g'}
                // Если раскомментировать следующие строки, возникнет цикл n->g->a->n
                // {'n', 'g', 'a'},
                // {'g', 'a', 'n'},
                // {'a', 'n', 'g'}
        };
        System.out.println("Test 2 Input: " + Arrays.deepToString(triplets2));
        System.out.println("Test 2 Recovered secret: " + recoverSecret(triplets2)); // Ожидаемый: mastering

        // Пример с циклом (если раскомментировать в triplets2)
        /*
        try {
             char[][] triplets3 = new char[][]{
                 {'m', 'a', 's'}, {'a', 's', 't'}, {'s', 't', 'e'},
                 {'t', 'e', 'r'}, {'e', 'r', 'i'}, {'r', 'i', 'n'},
                 {'i', 'n', 'g'}, {'n', 'g', 'a'}, {'g', 'a', 'n'},
                 {'a', 'n', 'g'} // Цикл n->g->a->n
             };
             System.out.println("---");
             System.out.println("Test 3 Input (with cycle): " + Arrays.deepToString(triplets3));
             recoverSecret(triplets3);
        } catch (RuntimeException e) {
            System.out.println("Test 3 correctly detected error: " + e.getMessage());
        }
        */
    }
}
