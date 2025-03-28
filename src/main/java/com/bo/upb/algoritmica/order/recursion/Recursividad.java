package com.bo.upb.algoritmica.order.recursion;

/**
 * Recursividad
 *
 * @author Marcos Quispe
 * @since 1.0
 */
public class Recursividad {

    void ejemploRecursivo(int valor) {
        if (valor == 0) // caso base
            return;

        ejemploRecursivo(valor - 1);
    }

    int funcionRecursiva(int valor) {
        if (valor == 1) // caso base
            return 1;

        return valor + funcionRecursiva(valor - 1);
    }

    long factorialRec(long n) {
        return 0;
    }

    long fibonacciRec(int n) {
        return 0;
    }

    public static void main(String[] args) {
        Recursividad rec = new Recursividad();
        rec.funcionRecursiva(10);
        rec.ejemploRecursivo(10);

        System.out.println(rec.factorialRec(6));

        //System.out.println(rec.fibonacciRec(7)); // 40 92
    }

}
