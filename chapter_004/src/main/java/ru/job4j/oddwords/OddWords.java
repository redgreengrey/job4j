package ru.job4j.oddwords;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class OddWords {

    private static List<String> swearWords = Arrays.asList("fuck", "fucking");


    private static List<String> splitWords(String in) {
        return new ArrayList<>(Arrays.asList(in.split(" ")));
    }

    static String filter(String string) {
        List<String> list = splitWords(string);

        return list.stream()
                 .filter(x -> !swearWords.contains(x))
                .map(x -> x + " ")
                .collect(Collectors.joining());
    }
}
