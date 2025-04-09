package com.bo.upb.algoritmica.order;

import java.util.Arrays;

/**
 * BubbleSort
 *
 * @author Marcos Quispe
 * @since 1.0
 */
public class SelectionSort {

    // funcion que permite intercambiar elementos de un array
    private void swap(int[] values, int i, int j) {
        int aux = values[i];
        values[i] = values[j];
        values[j] = aux;
    }

    public void selectionSort(int[] v) {
        // implement
    }

    public void insertionSort(int[] v) {
        // implement
    }

    public static void main(String[] args) throws Exception {
        int[] values;
        SelectionSort bubbleSort = new SelectionSort();

        values = new int[] {4, 3, 8, 1, 5, 7, 2, 9, 6};
        //values = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
        values = new int[] {1, 3, 4, 2, 5, 6, 7, 8, 9};
        bubbleSort.selectionSort(values);
        bubbleSort.insertionSort(values);
        System.out.println(Arrays.toString(values));
    }
}
