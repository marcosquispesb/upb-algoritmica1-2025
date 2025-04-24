package com.bo.upb.algoritmica.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * GraphMA
 *
 * @author Marcos Quispe
 * @since 1.0
 */
public class GraphMA {

    private int[] vertices;
    private int[][] mAdyacentes;

    public GraphMA() {
        vertices = null;
        mAdyacentes = null;
    }

    public GraphMA(int ...vertices) {
        this.vertices = vertices;
        this.mAdyacentes = new int[vertices.length][vertices.length];
//        this.vertices = new int[vertices.length];
//        for (int i = 0; i < vertices.length; i++) {
//            this.vertices[i] = vertices[i];
//        }
    }

    public int getPosVertice(int vertice) {
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i] == vertice)
                return i;
        }
        return -1;
    }

    public void addAristas(int verticeOrigen, int ...adyacentes) {
        int posVOrigen = getPosVertice(verticeOrigen);
        if (posVOrigen == -1)
            return;

        for (int adyacente : adyacentes) {
            int posAdyacente = getPosVertice(adyacente);
            if (posAdyacente == -1)
                continue;
            mAdyacentes[posVOrigen][posAdyacente] = 1;
        }
    }

    public int countAristas() {
        int count = 0;
        for (int f = 0; f < vertices.length; f++) {
            for (int c = 0; c < vertices.length; c++) {
                if (mAdyacentes[f][c] == 1)
                    count++;
            }
        }
        return count;
    }

    public List<Integer> getAdyacentes(int vertice) {
        int f = getPosVertice(vertice);
        List<Integer> adyacentes = new ArrayList<>();
        for (int c = 0; c < vertices.length; c++) {
            if (mAdyacentes[f][c] == 1)
                adyacentes.add(vertices[c]);
        }
        return adyacentes;
    }

    public List<Integer> aqsa(int vertice) {
        return null;
    }

    public void print() {
        for (int f = 0; f < vertices.length; f++) {
            System.out.println(Arrays.toString(mAdyacentes[f]));
        }
    }

    public static void main(String[] args) {
        GraphMA g = new GraphMA(10, 11, 2, 3);
        g.addAristas(10, 11, 2);
        g.addAristas(11, 10, 2);
        g.addAristas(3, 10, 3);
        g.print();
        System.out.println("cantAristas: " + g.countAristas());
        System.out.println("adyacentes: " + g.getAdyacentes(10));
    }
}
