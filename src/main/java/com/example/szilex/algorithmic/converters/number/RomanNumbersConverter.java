package com.example.szilex.algorithmic.converters.number;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Class with static functions for conversion of Roman number literals to integer values and vice versa
 * It is assumed that the values passed to the functions are correctly formatted and their value is in range <1,3999>
 */
public class RomanNumbersConverter {
    private static final LinkedHashMap<String, Integer> literalsToValuesMap = new LinkedHashMap<>();

    static {
        literalsToValuesMap.put("I", 1);
        literalsToValuesMap.put("II", 2);
        literalsToValuesMap.put("III", 3);
        literalsToValuesMap.put("IV", 4);
        literalsToValuesMap.put("V", 5);
        literalsToValuesMap.put("VI", 6);
        literalsToValuesMap.put("VII", 7);
        literalsToValuesMap.put("VIII", 8);
        literalsToValuesMap.put("IX", 9);
        literalsToValuesMap.put("X", 10);
        literalsToValuesMap.put("XX", 20);
        literalsToValuesMap.put("XXX", 30);
        literalsToValuesMap.put("XL", 40);
        literalsToValuesMap.put("L", 50);
        literalsToValuesMap.put("LX", 60);
        literalsToValuesMap.put("LXX", 70);
        literalsToValuesMap.put("LXXX", 80);
        literalsToValuesMap.put("XC", 90);
        literalsToValuesMap.put("C", 100);
        literalsToValuesMap.put("CC", 200);
        literalsToValuesMap.put("CCC", 300);
        literalsToValuesMap.put("CD", 400);
        literalsToValuesMap.put("D", 500);
        literalsToValuesMap.put("DC", 600);
        literalsToValuesMap.put("DCC", 700);
        literalsToValuesMap.put("DCCC", 800);
        literalsToValuesMap.put("CM", 900);
        literalsToValuesMap.put("M", 1000);
        literalsToValuesMap.put("MM", 2000);
        literalsToValuesMap.put("MMM", 3000);
    }

    public static int convertToInteger(String romanLiteral) {
        var entryList = new ArrayList<>(literalsToValuesMap.entrySet());
        Collections.reverse(entryList);
        int result = 0;
        var iterator = entryList.listIterator();
        while(iterator.hasNext()) {
            var entry = iterator.next();
            if (romanLiteral.startsWith(entry.getKey())) {
                result += entry.getValue();
                romanLiteral = romanLiteral.substring(entry.getKey().length());
                if (iterator.hasPrevious()) {
                    iterator.previous();
                }
            }
        }
        return result;
    }

    public static String convertToRoman(int integerValue) {
        StringBuilder result = new StringBuilder();
        int multiplicator = 1000;
        int remainder = integerValue;

        while (remainder != 0) {
            int value = remainder / multiplicator;
            result.append(getRomanLiteral(value * multiplicator));
            remainder %= multiplicator;
            multiplicator /= 10;
        }

        return result.toString();
    }

    private static String getRomanLiteral(int value) {
        return literalsToValuesMap.entrySet().stream()
                .filter(entry -> entry.getValue().equals(value))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse("");
    }
}
