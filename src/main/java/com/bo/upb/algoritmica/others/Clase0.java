// Nota: al copiar la clase a su proyecto nuevo, actualice la sig linea para que tenga el package correcto
package com.bo.upb.algoritmica.others;

import java.util.Arrays;

/**
 * Clase0
 *
 * @author Marcos Quispe
 * @since 1.0
 */
public class Clase0 {

    public int frequency(int[] values, int valueToSearch) {
        int count = 0;
        for (int value : values) {
            if (value == valueToSearch)
                count++;
        }
        return count;
    }

    public void selectionSort(int[] values) {
        int posMenor;
        for (int i = 0; i < values.length; i++) {
            posMenor = i;
            for (int j = i + 1; j < values.length; j++) {
                if (values[j] < values[posMenor])
                    posMenor = j;
            }
            swap(values, i, posMenor);
        }
    }

    // funcion que permite intercambiar elementos de un array
    private void swap(int[] values, int i, int j) {
        int aux = values[i];
        values[i] = values[j];
        values[j] = aux;
    }

    public boolean brackets(String line) {
        int opens = 0;
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i) == '(') {
                opens++;
            } else if (line.charAt(i) == ')') {
                if (opens == 0)
                    return false;
                opens--;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Clase0 clase0 = new Clase0();
        int[] values;

        values = new int[] {3, 0, 1, 4, 2, 5, 1, 0, 1};
        System.out.println(clase0.frequency(values, 1));

        // ------------------------------------------------------

        values = new int[] {3, 0, 1, 4, 2, 5, 1};
        clase0.selectionSort(values);
        System.out.println(Arrays.toString(values));

        values = new int[] {3, 0, 1, 4, 2, 5, 1};
        System.out.println(Arrays.toString(values));

        // ------------------------------------------------------

        System.out.println();
        System.out.println(clase0.brackets("(())()"));
        System.out.println(clase0.brackets("Tengase que (obviamente) puede haber otros simbolos"));
        System.out.println(clase0.brackets(":)"));
        System.out.println(clase0.brackets("a (( ) ) () () b"));
        System.out.println(clase0.brackets("a (( ) ) () () b ))"));
    }
}
