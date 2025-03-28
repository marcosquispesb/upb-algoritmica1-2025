package com.bo.upb.algoritmica.order;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;

/**
 * RadixSort
 *
 * @author Marcos Quispe
 * @since 1.0
 */
public class RadixSort {

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
    public void radixSortPositive(int[] values) throws Exception {
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

    public void radixSort(int[] values) throws Exception {
        int[] positives = Arrays.stream(values).filter(x -> x > 0).toArray();
        int[] negatives = Arrays.stream(values).filter(x -> x < 0).map(x -> x*(-1)).toArray();
        System.out.println("negatives: " + Arrays.toString(negatives));

        radixSortPositive(positives);
        radixSortPositive(negatives);

        int c = 0;
        for (int i = negatives.length - 1; i > -1 ; i--) {
            values[c] = negatives[i] * (-1);
            c++;
        }

        for (int i = 0; i < positives.length; i++) {
            values[c] = positives[i];
            c++;
        }
    }
    //endregion

    public int getCode(char c) {
        int code = (int) c;
        if (code >= 97)
            return code - 96;
        return code - 64;
    }
    public static int maxLength(String[] values) throws Exception {
        return Arrays.stream(values)
                .mapToInt(String::length)
                .max().orElse(0);
    }
    public void radixSortString(String[] values) throws Exception {
        LinkedList<String>[] buckets = new LinkedList[27];
        for (int i = 0; i < buckets.length; i++) {
            buckets[i] = new LinkedList<>();
        }

        int k = maxLength(values);
        System.out.println(k);
//        for (int i = 0; i < k; i++) { // definir posicion del digito a evaluar
        for (int i = k - 1; i > -1; i--) { // definir posicion del digito a evaluar
            for (int j = 0; j < values.length; j++) { // iterar elementos del arreglo
                //int posAux = i; //values[j].length() - i - 1;
                int dig = 0;
                if (i < values[j].length())
                    dig = getCode(values[j].charAt(i));

                buckets[dig].add(values[j]);
            }

            int pos = 0;
            for (int j = 0; j < buckets.length; j++) {
                for (String value : buckets[j]) {
                    values[pos] = value;
                    pos++;
                }
                buckets[j].clear();
            }
            //System.out.println("   " + Arrays.toString(values));
        }
    }

    public static void main(String[] args) throws Exception {
        int[] values;
        RadixSort radixSort = new RadixSort();

//        System.out.println();
//        values = new int[] {8920, 56, 5221, 635, 6, 10, 8691, 457, 26};
//        //values = new int[] {1234, 65, 63, 1245};
//        //values = new int[] {352, 62, 56, 3, 16, 353};
//        bubbleSort.radixSortPositive(values);
//        System.out.println(Arrays.toString(values));

//        values = new int[] {8920, 56, 5221, -635, 6, 10, -8691, 457, -26};
//        radixSort.radixSort(values);
//        System.out.println(Arrays.toString(values));

        String[] valuesStr;
        //valuesStr = new String[] {"ab", "c", "cba", "a", "ba", "z"}; // a, c, z, ab, ba, cba
        //valuesStr = new String[] {"Ab", "c", "cba", "a", "BA", "Z"}; // a, c, Z, Ab, BA, cba
        valuesStr = new String[] {"ab", "a", "b", "ba", "abc"}; // a, b, ab, ba, abc
        //Arrays.sort(valuesStr);
        radixSort.radixSortString(valuesStr);
        System.out.println(Arrays.toString(valuesStr));

//        System.out.println((int)'a' + " - " + (int)'z'); // 97 - 122
//        System.out.println((int)'A' + " - " + (int)'Z'); // 65 - 90
//        System.out.println(radixSort.getCode('a') + " - " + radixSort.getCode('z'));
//        System.out.println(radixSort.getCode('A') + " - " + radixSort.getCode('Z'));
    }
}
