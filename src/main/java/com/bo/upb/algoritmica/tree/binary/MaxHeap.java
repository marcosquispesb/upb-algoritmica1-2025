package com.bo.upb.algoritmica.tree.binary;

import java.util.Arrays;

/**
 * MaxHeap
 *
 * @author Marcos Quispe
 * @since 1.0
 */
public class MaxHeap {
    private int[] values;
    private int lenght;

    public MaxHeap() {
        values = new int[10000];
        lenght = 0;
    }

    public void add(int value) {
        values[lenght] = value;
        lenght++;
        int i = lenght;
        while (i > 1) {
            int p = i / 2;
            if (values[i - 1] > values[p - 1]) {
                swap(values, i - 1, p - 1);
                i = p;
            } else {
                return;
            }
        }
    }

    public void addAll(int ...values) {
        for (int value : values) {
            add(value);
        }
    }

    // funcion que permite intercambiar elementos de un array
    private void swap(int[] values, int i, int j) {
        int aux = values[i];
        values[i] = values[j];
        values[j] = aux;
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOfRange(values, 0, lenght));
    }

    public static void main(String[] args) {
        MaxHeap h = new MaxHeap();
        h.addAll(4, 19, 7, 10, 16, 20, 8, 9, 13, 40);
        System.out.println(h);
    }


}
