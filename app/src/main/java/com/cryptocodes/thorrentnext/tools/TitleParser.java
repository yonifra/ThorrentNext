package com.cryptocodes.thorrentnext.tools;

public class TitleParser {
    public static String parse(String unparsedTitle) {
        String[] words = unparsedTitle.split(" ");

        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < words.length; i++) {
            String word = words[i];

            if (!word.matches("[(][\\d]+[)]")) {
                if (i > 0) {
                    sb.append(" ");
                }
                sb.append(word);
            } else {
                break;
            }


        }

        return sb.toString();
    }
}
