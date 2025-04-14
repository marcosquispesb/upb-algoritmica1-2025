package com.bo.upb.algoritmica.order.recursion;

import java.util.Arrays;

/**
 * Iterativos
 *
 * @author Marcos Quispe
 * @since 1.0
 */
public class Iterativos {

    long factorial(long n) {
        if (n == 0)
            return 1;

        long result = 1;
        for (int i = 1; i < n + 1; i++) {
            result = result * i;
        }

        return result;
    }

    long fibo(int n) {
        long f2 = 1;
        long f1 = 0;
        long f = 0;
        for (int i = 0; i < n; i++) {
            f = f1 + f2;
            f2 = f1;
            f1 = f;
        }
        return f;
    }

//    long fibo(long[] values) {
//        long f2 = 1;
//        long f1 = 0;
//        long f = 0;
//        for (int i = 0; i < values.length; i++) {
//            f = f1 + f2;
//
//            values[i] = f;
//            f2 = f1;
//            f1 = f;
//        }
//
//        return f;
//    }

    public static void main(String[] args) {
        Iterativos ite = new Iterativos();
        long timeMillis;

        int number = 25;
        timeMillis = System.currentTimeMillis();
        //System.out.println("" + number + "!: " + ite.factorial(number) + " time: " + (System.currentTimeMillis() - timeMillis) + " ms");

        long[] fiboValues;

        timeMillis = System.currentTimeMillis();
        //fiboValues = new long[92];
        //ite.fibo(fiboValues);
        //System.out.println(String.format("fibo: %s time: %s ms", Arrays.toString(fiboValues), System.currentTimeMillis() - timeMillis));

//        System.out.println(ite.fibo(1));
//        System.out.println(ite.fibo(2));
//        System.out.println(ite.fibo(3));
//        System.out.println(ite.fibo(4));
//        System.out.println(ite.fibo(5));
        System.out.println(ite.fibo(46));

        //int[] values = {7, 6, 1, 5, 4, 3};
        //int[] numbers = new int[] {10, 5};
        //int[] numbers = new int[] {10, 5, 2, 4, 1, 8, 3};
        //int[] numbers = new int[] {100, 5, 2, 4, 1, 8, 3, 0, 10, 20, 30, 25, 80, 90, 15, 56, 70, 98, 99};
    }

}
