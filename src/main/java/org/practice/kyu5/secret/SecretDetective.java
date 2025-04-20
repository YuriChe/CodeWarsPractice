package org.practice.kyu5.secret;
// неверный пробный алгоритм
@Deprecated
public class SecretDetective {

    public String recoverSecret(char[][] triplets) {

        StringBuilder result = new StringBuilder();

        for (char[] triplet : triplets) {
            String toAdd = "";
            boolean prevMatter;
            String str1 = String.valueOf(triplet[0]);
            String str2 = String.valueOf(triplet[1]);
            String str3 = String.valueOf(triplet[2]);
            int idx1 = result.indexOf(str1);
            int idx2 = result.indexOf(str2);
            int idx3 = result.indexOf(str3);

            if (idx1 == -1) { // если 1 нет в рез
                toAdd += str1;
                prevMatter = false;
            } else {                            // если 1 есть
                prevMatter = true;
            }

            if (idx2 == -1) {                   //если 2 нет в рез
                if (prevMatter) {
                    result.insert(idx1 + 1, toAdd);
                    toAdd = "";
                } else {
                    toAdd += str2;
                }
                prevMatter = false;
            } else {                        // если 2 есть в рез
                if (prevMatter) {
                    if (idx1 < idx2) {
                        result.setCharAt(idx1, triplet[1]);
                        result.deleteCharAt(idx2);
                        result.insert(idx1, str1);
                        prevMatter = true;
                    }
                } else {
                    result.insert(idx2, toAdd);
                    toAdd = "";
                }
            }

            if (idx3 == -1) {    //  если 3 нет в рез
                if (prevMatter) {
                    if (idx2 < idx3) {
                        result.setCharAt(idx2, triplet[2]);
                        result.deleteCharAt(idx3);
                        result.insert(idx2, str2);
                        prevMatter = true;
                    }
                }
            } else {
                result.insert(idx3, toAdd);
                toAdd = "";
            }
        }  // конец цикла
        return result.toString();
    }

    public static void main(String[] args) {
        char[][] triplets = new char[][]{
                {'t', 's', 'f'},
                {'a', 's', 'u'},
                {'m', 'a', 'f'},
                {'a', 'i', 's'},
                {'s', 'u', 'p'}
        };

        SecretDetective secretDetective = new SecretDetective();

        System.out.print(secretDetective.recoverSecret(triplets));
    }
}