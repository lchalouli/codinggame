package com.leila.codinggame;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;

public class GreatestNumberTest {

    @Test
    public void test() {
        final Random random = new Random(2);
        final Stream<String> stringStream = random.ints().limit(100)
                .mapToObj(value -> {
                    final String str = String.valueOf(Math.abs(value));
                    final int offsetTiret = Math.abs(random.nextInt()) % str.length();
                    final int offsetPoint = Math.abs(random.nextInt()) % str.length();
                    final StringBuilder stringBuilder = new StringBuilder(str);
                    if (random.nextBoolean()) {
                        stringBuilder.insert(offsetTiret, '-');
                    }
                    if (random.nextBoolean()) {
                        stringBuilder.insert(offsetPoint, '.');
                    }
                    final String result = stringBuilder.toString();
                    return result;
                });

        /*stringStream.forEach(s1 -> {
            System.out.print(s1 + " : ");
            final String great = great(s1);
            System.out.println(great);
        });*/
        final String great = great(" - 0 0 0 0 0 0 0 .");
        System.out.println(great);

    }

    private String great(String input) {
        final Stream<Integer> numbers = getNumbers(input);
        StringBuilder result;
        if (input.contains("-")) {
            result = new StringBuilder("-")
                    .append(numbers
                            .sorted()
                            .map(integer -> String.valueOf(integer))
                            .collect(Collectors.joining()));
            if (input.contains(".")) {
                result.insert(2, '.');
            }
        } else {
            result = new StringBuilder(numbers
                    .sorted(Comparator.<Integer>reverseOrder())
                    .map(integer -> String.valueOf(integer))
                    .collect(Collectors.joining()));

            if (input.contains(".")) {
                result.insert(result.length()-1, '.');
            }
        }

        String s = result.toString();
        System.out.println("Avant : " + s);
        if (s.matches("^[^.]*\\.?[0]+$")) {
            s = s.split("\\.")[0];
        }
        if (s.matches("^[-]?[0]+$")) {
            s = "0";
        }
        return s;
    }


    private Stream<Integer> getNumbers(String inputString) {
        Pattern decimals = Pattern.compile("(\\d+)");
        final Matcher matcher = decimals.matcher(inputString);
        final StringBuilder bd = new StringBuilder();
        while (matcher.find()) {
            bd.append(Integer.valueOf(matcher.group(1)));
        }

        return Arrays.stream(bd.toString().split("")).map(s -> Integer.valueOf(s));
    }

}
