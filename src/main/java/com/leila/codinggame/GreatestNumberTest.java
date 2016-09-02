package com.leila.codinggame;

import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class GreatestNumberTest {

    @Test
    public void test() {
        final Stream<String> stringStream = new Random(2).ints().limit(100)
                .map(operand -> Math.abs(operand))
                .mapToObj(value -> String.valueOf(value));

        stringStream.map(s -> s.chars().mapToObj(operand -> getRandomChar(operand)).collect(Collectors.joining())).forEach(s1 -> System.out.println(s1));
    }

    private String getRandomChar(int random) {
        String[] tab = new String[]{".","-","1","2","3","4","5","6","7","8","9"};
        final double index = random % tab.length;
        final int rounded = Long.valueOf(Math.round(index)).intValue();
        System.out.println(index);
        return tab[rounded];
    }

}
