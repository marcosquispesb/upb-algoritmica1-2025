package com.bo.upb.algoritmica.order;

import java.util.Arrays;

/**
 * MergeSort
 *
 * @author Marcos Quispe
 * @since 1.0
 */
public class MergeSort {

    public static void mergeSort(int[] values) {
        //System.out.println(Arrays.toString(values));
        if (values.length == 1)
            return;

        int k = values.length / 2;
        int[] left = Arrays.copyOfRange(values, 0, k);
        int[] right = Arrays.copyOfRange(values, k, values.length);

        mergeSort(left);
        mergeSort(right);

        int l = 0;
        int r = 0;
        int i = 0;
        while (l < left.length || r < right.length) {
            if (l < left.length && r < right.length) {
                if (left[l] < right[r]) {
                    values[i] = left[l];
                    l++;
                } else {
                    values[i] = right[r];
                    r++;
                }
            } else if (l < left.length) {
                values[i] = left[l];
                l++;
            } else {
                values[i] = right[r];
                r++;
            }
            i++;
        }
    }

    public static void main(String[] args) {
        int[] values = {7, 6, 1, 5, 4, 3};
        mergeSort(values);
        System.out.println(Arrays.toString(values));
    }
}
