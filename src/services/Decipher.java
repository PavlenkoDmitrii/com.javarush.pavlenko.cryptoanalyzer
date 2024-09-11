package services;

import java.util.List;

public class Decipher {

    public static String decipher(String line, int key, List<Character> list) {
        char[] chars = line.toLowerCase().toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        String res = "";
        for (char aChar : chars) {
            if (list.contains(aChar)) {
                int j = list.indexOf(aChar);
                if (j == 0) {
                    j = list.size();
                }
                int shift = (Math.abs((j - key) % list.size()));
                res = String.valueOf(stringBuilder.append(list.get(shift)));
            }
        }
        return res;
    }
}
