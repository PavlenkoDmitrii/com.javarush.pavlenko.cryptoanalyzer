package services;

import java.util.List;

public class BruteForce {

    public static String decipherWithBruteForce(String line, int position, List<Character> list) {
        char[] chars = line.toLowerCase().toCharArray();
        StringBuilder stringBuilder = new StringBuilder();
        String res = "";
            for (char aChar : chars) {
                if (list.contains(aChar)) {
                    int indexOfSymbol = list.indexOf(aChar);
                    int shift = ((indexOfSymbol + position) % list.size());
                    res = String.valueOf(stringBuilder.append(list.get(shift)));
                }
            }
        return res;
    }
}
