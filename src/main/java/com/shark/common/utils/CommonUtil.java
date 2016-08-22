package com.shark.common.utils;

import java.util.Random;

public class CommonUtil {
    public static int getRandom() {
        Random random = new Random();
        return random.nextInt(10) + 1;
    }
}
