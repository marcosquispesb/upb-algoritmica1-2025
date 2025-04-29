package com.bo.upb.algoritmica.graph.graphma;

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

    private String[] vertices;

    private int[][] mAdyacencia;

    public GraphMA() {
        vertices = null;
        mAdyacencia = null;
    }

    public GraphMA(String ...vertices) {
        this.vertices = vertices;
        this.mAdyacencia = new int[vertices.length][vertices.length];
    }

    public int getPosVertice(String vertice) {
        for (int i = 0; i < vertices.length; i++) {
            if (vertices[i].equals(vertice))
                return i;
        }
        return -1;
    }

    public void addAristas(String verticeOrigen, String ...adyacentes) {
        int posVOrigen = getPosVertice(verticeOrigen);
        if (posVOrigen == -1)
            return;

        for (String adyacente : adyacentes) {
            int posAdyacente = getPosVertice(adyacente);
            if (posAdyacente == -1)
                continue;
            mAdyacencia[posVOrigen][posAdyacente] = 1;
        }
    }

    public int countAristas() {
        int count = 0;
        for (int f = 0; f < vertices.length; f++) {
            for (int c = 0; c < vertices.length; c++) {
                if (mAdyacencia[f][c] == 1)
                    count++;
            }
        }
        return count;
    }

    public List<String> getAdyacentes(String vertice) {
        int f = getPosVertice(vertice);
        List<String> adyacentes = new ArrayList<>();
        for (int c = 0; c < vertices.length; c++) {
            if (mAdyacencia[f][c] == 1)
                adyacentes.add(vertices[c]);
        }
        return adyacentes;
    }

    public List<String> aqsa(String vertice) {
        int c = getPosVertice(vertice);
        List<String> adyacentes = new ArrayList<>();
        for (int f = 0; f < vertices.length; f++) {
            if (mAdyacencia[f][c] == 1)
                adyacentes.add(vertices[f]);
        }
        return adyacentes;
    }

    public void print() {
        for (int f = 0; f < vertices.length; f++) {
            System.out.println(Arrays.toString(mAdyacencia[f]));
        }
    }

    public static void main(String[] args) {
        GraphMA g = new GraphMA("A", "B", "C", "D");
        g.addAristas("A", "B", "C");
        g.addAristas("B", "A");
        g.addAristas("D", "A", "C", "D");
        g.print();

        System.out.println("cantAristas:  " + g.countAristas());
        System.out.println("adyacentes A: " + g.getAdyacentes("A"));
        System.out.println("aqsa A:       " + g.aqsa("A"));
    }
}
