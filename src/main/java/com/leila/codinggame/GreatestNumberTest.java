package com.leila.codinggame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class GreatestNumberTest {

    @Test
    public void test() {
        String[] tab = new String[]{".","-","1","2","3","4","5","6","7","8","9"};
        final Stream<String> stringStream = new Random(2).ints().limit(100)
                .map(operand -> Math.abs(operand))
                .mapToObj(value -> String.valueOf(value));

        stringStream.map(s -> {
            final ArrayList<String> tabCopy = new ArrayList<>(Arrays.asList(tab));
            return s.chars().mapToObj(operand -> getRandomChar(operand, tabCopy)).collect(Collectors.joining());
        }).forEach(s1 -> System.out.println(s1));
    }

    private String getRandomChar(int random, List<String> tab) {
        final double index = random % tab.size();
        final int rounded = Long.valueOf(Math.round(index)).intValue();
        final String character = tab.get(rounded);
        if (character.equals(".") || character.equals("-")) {
            tab.remove(character);
        }
        return character;
    }

}
