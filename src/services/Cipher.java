package services;

import java.util.List;

public class Cipher {

    public static String encipher(String line, int key, List<Character> list) {
        char[] chars = line.toLowerCase().toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        String res = "";
        for (char aChar : chars) {
            if (list.contains(aChar)) {
                int j = list.indexOf(aChar);
                int shift = (Math.abs((j + key) % list.size()));
                res = String.valueOf(stringBuilder.append(list.get(shift)));
            }
        }
        return res;
    }
}
