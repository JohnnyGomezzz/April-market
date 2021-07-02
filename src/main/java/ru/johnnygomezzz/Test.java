package ru.johnnygomezzz;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Test {

    public BigDecimal a;
    public BigDecimal b;
    public static BigDecimal c;

    public static BigDecimal divide(BigDecimal a, BigDecimal b) {
        c = a.divide(b, 2, RoundingMode.HALF_UP);
        return c;
    }

    public static void main(String[] args) {

        System.out.println(divide(new BigDecimal(1000), new BigDecimal(3)));
    }
}
