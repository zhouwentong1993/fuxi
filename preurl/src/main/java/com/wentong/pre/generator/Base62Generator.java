package com.wentong.pre.generator;

import com.google.common.collect.Maps;

import java.util.Map;
import java.util.Random;

public class Base62Generator implements UrlGenerator {

    private static final int LENGTH = 6;

    private static final Map<Integer, Character> CONTAINER1 = Map.of(0, 'A', 1, 'B', 2, 'C', 3, 'D', 4, 'E', 5, 'F', 6, 'G', 7, 'H', 8, 'I', 9, 'J');
    private static final Map<Integer, Character> CONTAINER2 = Map.of(10, 'K', 11, 'L', 12, 'M', 13, 'N', 14, 'O', 15, 'P', 16, 'Q', 17, 'R', 18, 'S', 19, 'T');
    private static final Map<Integer, Character> CONTAINER3 = Map.of(20, 'U', 21, 'V', 22, 'W', 23, 'X', 24, 'Y', 25, 'Z', 26, 'a', 27, 'b', 28, 'c', 29, 'd');
    private static final Map<Integer, Character> CONTAINER4 = Map.of(30, 'e', 31, 'f', 32, 'g', 33, 'h', 34, 'i', 35, 'j', 36, 'k', 37, 'l', 38, 'm', 39, 'n');
    private static final Map<Integer, Character> CONTAINER5 = Map.of(40, 'o', 41, 'p', 42, 'q', 43, 'r', 44, 's', 45, 't', 46, 'u', 47, 'v', 48, 'w', 49, 'x');
    private static final Map<Integer, Character> CONTAINER6 = Map.of(50, 'y', 51, 'z', 52, '0', 53, '1', 54, '2', 55, '3', 56, '4', 57, '5', 58, '6', 59, '7');
    private static final Map<Integer, Character> CONTAINER7 = Map.of(60, '8', 61, '9');
    private static final Map<Integer, Character> CONTAINER = Maps.newHashMapWithExpectedSize(62);

    static {
        CONTAINER.putAll(CONTAINER1);
        CONTAINER.putAll(CONTAINER2);
        CONTAINER.putAll(CONTAINER3);
        CONTAINER.putAll(CONTAINER4);
        CONTAINER.putAll(CONTAINER5);
        CONTAINER.putAll(CONTAINER6);
        CONTAINER.putAll(CONTAINER7);
    }

    @Override
    public String generate() {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < LENGTH; i++) {
            sb.append(CONTAINER.get(random.nextInt(62)));
        }
        return sb.toString();
    }
}
