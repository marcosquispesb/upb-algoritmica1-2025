package com.bo.upb.algoritmica.order;

import java.util.Arrays;

/**
 * QuickSort
 *
 * @author Marcos Quispe
 * @since 1.0
 */
public class QuickSort {

    public static void swap(int[] values, int i, int j) {
        int aux = values[i];
        values[i] = values[j];
        values[j] = aux;
    }

    public static void quickSort(int[] values, int pi, int pf) {
        if (pi == pf)
            return;

        boolean goRight = true;
        int r = pf;
        int l = pi;
        while (l < r) {
            if (goRight) {
                if (values[l] > values[r]) {
                    swap(values, l, r);
                    goRight = false;
                } else {
                    r--;
                }
            } else {
                if (values[r] < values[l]) {
                    swap(values, l, r);
                    goRight = true;
                } else {
                    l++;
                }
            }
        }

        //System.out.println(l);
//        System.out.println(Arrays.toString(Arrays.copyOfRange(values, pi, l)));
//        System.out.println(Arrays.toString(Arrays.copyOfRange(values, l + 1, pf + 1)));

        if (l > pi && l < pf) {
            quickSort(values, pi, l - 1);
            quickSort(values, l + 1, pf);
        } else if (l > pi) {
            quickSort(values, pi, l - 1);
        } else {
            quickSort(values, l + 1, pf);
        }
        //System.out.println(Arrays.toString(values));
    }

    public static boolean busBin(int[] values, int valueToSearch) {
        int pi = 0;
        int pf = values.length - 1;

        while (pi <= pf) {
            int pm =  pi + ((pf - pi) / 2);

            if (values[pm] == valueToSearch)
                return true;

            if (valueToSearch > values[pm]) {
                pi = pm + 1;
            } else {
                pf = pm - 1;
            }
            System.out.println(pi + " - " + pf);
        }
        return false;
    }
    public static void main(String[] args) {
        //int[] values = {7, 6, 1, 5, 4, 3};
        //int[] values = {4, 3, 0, 1, 5, 7, 2, 9, 6};
        //int[] values = {1, 0}; // 0, 1
        //quickSort(values, 0, values.length - 1);
        //System.out.println(Arrays.toString(values));

        int[] values = {0, 2 ,3, 5, 7 ,8 ,9};
        System.out.println(busBin(values, 7));
        //System.out.println(Arrays.toString(values));
    }
}
