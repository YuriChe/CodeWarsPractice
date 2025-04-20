package org.practice.kyu5;

import java.util.ArrayList;
import java.util.Collections;

public class MixUp {
    public static long nextBiggerNumber(long n) {
        ArrayList<Integer> digits = new ArrayList<>();
        long num = n;

        while (num > 0) {
            digits.add((int) (num % 10));
            num /= 10;
        }

        Collections.reverse(digits);

        int i;
        for (i = digits.size() - 2; i >= 0; i--) {
            if (digits.get(i) < digits.get(i + 1)) {

                int minRight = i + 1;
                for (int j = i + 1; j < digits.size(); j++) {
                    if (digits.get(j) > digits.get(i) && digits.get(j) < digits.get(minRight)) {
                        minRight = j;
                    }
                }

                Collections.swap(digits, i, minRight);
                Collections.sort(digits.subList(i + 1, digits.size()));

                long resultNum = 0L;
                for (Integer digit : digits) {
                    resultNum = resultNum * 10 + digit;
                }
                return resultNum;
            }
        }
        return -1;
    }

        /*int i;
        for (i = digits.size() - 2; i >= 0; i--) {
            if (digits.get(i) < digits.get(i + 1)) { // Нашли "критическую" цифру
                int minRight = i + 1;

                // Поиск минимально большего элемента
                for (int j = i + 1; j < digits.size(); j++) {
                    if (digits.get(j) > digits.get(i) && digits.get(j) < digits.get(minRight)) {
                        minRight = j;
                    }
                }

                // Меняем местами
                Collections.swap(digits, i, minRight);

                // Сортируем оставшуюся часть справа
                Collections.sort(digits.subList(i + 1, digits.size()));

                // Формируем итоговое число
                long resultNum = 0L;
                for (int index = 0; index < digits.size(); index++) {
                    resultNum = resultNum * 10 + digits.get(index);
                }
                return resultNum;
            }
        }
        return -1;
    }*/

    public static void main(String[] args) {

        System.out.println(nextBiggerNumber(414)); // Ожидаем 441
        System.out.println(nextBiggerNumber(1234)); // 1243
        System.out.println(nextBiggerNumber(4321)); // -1
        System.out.println(nextBiggerNumber(2017)); // 2071
        System.out.println(nextBiggerNumber(144)); // 414
        System.out.println(nextBiggerNumber(10990)); // 19009
        System.out.println(nextBiggerNumber(98761125)); //
    }
}

/*
import java.util.*;
public class Kata {
    public static long nextBiggerNumber(long n) {
        char[] k = String.valueOf(n).toCharArray();
        for (int i = k.length - 2; i >= 0; i--) {
            for (int j = k.length - 1; j > i; j--) {
                if (k[i] < k[j]) {
                    k[i] ^= k[j]; k[j] ^= k[i]; k[i] ^= k[j];
                    Arrays.sort(k, i + 1, k.length);
                    return Long.valueOf(new String(k));
                }}}
        return -1;
    }
}*/
