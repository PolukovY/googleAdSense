package com.levik.googleadsense.advert.suggestion.utils;

import lombok.experimental.UtilityClass;

import java.util.Random;


@UtilityClass
public class IntRandomNumberGeneratorUtils {

    public int generate(int max) {
        return new Random().ints(0, max).iterator().nextInt();
    }
}
