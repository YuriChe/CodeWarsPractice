package org.practice.kyu5.secret;

import java.util.*;

/*
Шаг 1: Построение графа

Инициализация структур данных:
Создайте словарь (или хеш-таблицу) для хранения списков смежности каждого символа.
Создайте множество для хранения всех уникальных символов.

Обработка триплетов:
Для каждого триплета (a, b, c) добавьте ребра a -> b, a -> c, и b -> c в граф.
Добавьте символы a, b, и c в множество уникальных символов.

Шаг 2: Топологическая сортировка

Вычисление входных степеней:
Создайте словарь для хранения входных степеней каждого символа (количество входящих ребер).

Инициализация очереди:
Добавьте все символы с нулевой входной степенью в очередь.

Основной цикл топологической сортировки:
Пока очередь не пуста:
Извлеките символ из очереди и добавьте его в результат.
Уменьшите входные степени всех соседей этого символа на 1.
Если входная степень соседа становится равной 0, добавьте его в очередь.

Шаг 3: Проверка на циклы

Если в результате топологической сортировки все символы были обработаны, то циклов нет, и результат содержит правильную последовательность символов.
Если остались символы, которые не были добавлены в результат, это означает наличие цикла, и необходимо пересмотреть данные или алгоритм.
*/

public class SecretWordByTriplets {

    public static String recoverSecret(char[][] triplets) {

        Map<Character, Set<Character>> graph = new HashMap<>();

        for (char[] triplet : triplets) {
            graph.compute(triplet[0], (k, v) -> {
                if (v == null) {
                    v = new HashSet<>();
                }
                v.add(triplet[1]);
                v.add(triplet[2]);
                return v;
            });
            graph.compute(triplet[1], (k, v) -> {
                if (v == null) {
                    v = new HashSet<>();
                }
                v.add(triplet[2]);
                return v;
            });
            graph.computeIfAbsent(triplet[2], k -> new HashSet<>());
        }

        System.out.println(graph);

        Map<Character, Integer> inDegree = new HashMap<>();
        for (Character ch : graph.keySet()) {
            inDegree.put(ch, 0);
        }
        for (Map.Entry<Character, Set<Character>> entry : graph.entrySet()) {
            for (Character to : entry.getValue()) {
                inDegree.put(to, inDegree.getOrDefault(to, 0) + 1);
            }
        }

        Deque<Character> deque = new ArrayDeque<>();
        inDegree.entrySet().stream()
                .filter(entry -> entry.getValue() == 0)
                .forEach(entry -> deque.add(entry.getKey()));

        StringBuilder result = new StringBuilder();

        while (!deque.isEmpty()) {
            char node = deque.poll();
            result.append(node);
            for (Character neighbour : graph.get(node)) {
                inDegree.put(neighbour, inDegree.get(neighbour) - 1);
                if (inDegree.get(neighbour) == 0) {
                    deque.add(neighbour);
                }
            }
        }

        if (result.length() != inDegree.size()) {
            throw new IllegalStateException("В триплетах присутствует цикл");
        }

        return result.toString();
    }

    public static void main(String[] args) {
/*        char[][] triplets = new char[][]{  // masteringjavaprogrammingchallengesolutionsdevtechnology
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
        };*/

        char[][] triplets = new char[][]{
                {'m', 'a', 's'},
                {'a', 's', 't'},
                {'s', 't', 'e'},
                {'t', 'e', 'r'},
                {'e', 'r', 'i'},
                {'r', 'i', 'n'},
                {'i', 'n', 'g'},
                {'n', 'g', 'a'},
                {'g', 'a', 'n'},
                {'a', 'n', 'g'}
        };

        String secret = recoverSecret(triplets);
        System.out.println("Recovered secret: " + secret); // Ожидаемый вывод: "masteringgang"
    }
}
