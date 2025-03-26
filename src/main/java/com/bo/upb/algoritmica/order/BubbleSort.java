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

    //region [Radix Sort] --------------------------------------------------------------------------
    public static int max(int[] values) throws Exception {
//        int max = Integer.MIN_VALUE;
//        for (int value : values) {
//            if (value > max)
//                max = value;
//        }
//        return max;
        return Arrays.stream(values).max() // map, filter
                .orElseThrow(() -> new Exception("No se encontro ningun valor"));
    }

    public static int countDigits(int nro) {
        int length = 0;
        while (nro > 0) {
            nro = nro / 10;
            length++;
        }
        return length;
    }

    // crear buckets (arreglo de listas)
    // encontrar max nro de digitos de los numeros del arreglo values
    // iteramos values y pasamos los numeros al bucket
    // pasamos del bucket al arreglo
    // limpiar buckets
    public void radixSort(int[] values) throws Exception {
        LinkedList<Integer>[] buckets = new LinkedList[10];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<>();
        }

        int modulo = 1;
        int k = (int) Math.log10(max(values)) + 1;
        for (int i = 0; i < k; i++) { // definir posicion del digito a evaluar
            for (int j = 0; j < values.length; j++) { // iterar elementos del arreglo
                int dig = (values[j] / modulo) % 10;
                buckets[dig].add(values[j]);
            }

            int pos = 0;
            for (int j = 0; j < buckets.length; j++) {
                for (Integer nro : buckets[j]) {
                    values[pos] = nro;
                    pos++;
                }
                buckets[j].clear();
            }

            modulo *= 10;
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
//        bubbleSort.bubbleSortImproved2(values);
//        System.out.println(Arrays.toString(values));

        // -----------------------------------------------

        System.out.println();
        values = new int[] {8920, 56, 5221, 635, 6, 10, 8691, 457, 26};
        //values = new int[] {1234, 65, 63, 1245};
        //values = new int[] {352, 62, 56, 3, 16, 353};
        bubbleSort.radixSort(values);
        System.out.println(Arrays.toString(values));
    }
}
