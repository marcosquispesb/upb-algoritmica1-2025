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

//        funcionRecursiva(valor - 1);
//        return valor;
//        return funcionRecursiva(valor - 1);
        return valor + funcionRecursiva(valor - 1);
    }

    long factorialRec(long n) {
        if (n == 1 || n == 0)
            return 1;
        return n * factorialRec(n - 1);
    }

    long fibonacciRec(int n) {
        if (n == 1 || n == 2)
            return 1;

        return fibonacciRec(n - 1) + fibonacciRec(n - 2);
    }

    public static void main(String[] args) {
        Recursividad rec = new Recursividad();
        rec.funcionRecursiva(10);
        rec.ejemploRecursivo(10);

        //System.out.println(rec.factorialRec(4));

        System.out.println(rec.fibonacciRec(92)); // 40 92
    }

}
