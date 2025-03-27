package com.bo.upb.algoritmica.order;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * BubbleSort
 *
 * @author Marcos Quispe
 * @since 1.0
 */
public class BubbleSort {

    //region [Bubble Sort] --------------------------------------------------------------------------
    // funcion que permite intercambiar elementos de un array
    private void swap(int[] values, int i, int j) {
        int aux = values[i];
        values[i] = values[j];
        values[j] = aux;
    }

    public void bubbleSort(int[] values) {
        for (int limit = values.length - 1; limit > 0 ; limit--) { // define limite
            for (int i = 0; i < limit; i++) { // burbujeando
                System.out.println("bs normal");
                if (values[i] > values[i + 1])
                    swap(values, i, i + 1);
            }
        }
    }

    public void bubbleSortImproved1(int[] values) {
        boolean flag = false;
        for (int limit = values.length - 1; limit > 0 ; limit--) { // define limite
            flag = false;
            for (int i = 0; i < limit; i++) { // burbujeando
                //System.out.println("bs normal");
                if (values[i] > values[i + 1]) {
                    swap(values, i, i + 1);
                    flag = true;
                }
            }
            if (!flag)
                break;
        }
    }

    public void bubbleSortImproved2(int[] values) {
        int limit = values.length - 1;
        int limitAux;
        while (limit > 0) { // define limite
            limitAux = 0;
            for (int i = 0; i < limit; i++) { // burbujeando
                System.out.println("bs improved2");
                if (values[i] > values[i + 1]) {
                    swap(values, i, i + 1);
                    limitAux = i;
                }
            }
            if (limitAux == 0)
                break;

            limit = limitAux;
        }
    }
    //endregion

    public static void main(String[] args) throws Exception {
        int[] values;
        BubbleSort bubbleSort = new BubbleSort();

        values = new int[] {4, 3, 8, 1, 5, 7, 2, 9, 6};
        //values = new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9};
        values = new int[] {1, 3, 4, 2, 5, 6, 7, 8, 9};
        //bubbleSort.bubbleSort(values);
        //bubbleSort.bubbleSortImproved1(values);
        bubbleSort.bubbleSortImproved2(values);
        System.out.println(Arrays.toString(values));
    }
}
