package com.bo.upb.algoritmica.graph.graphla;

import java.util.ArrayList;
import java.util.List;

/**
 * GraphLA
 *
 * @author Marcos Quispe
 * @since 1.0
 */
public class GraphLA {

    private static final int MAX_VERTICES = 1000;

    private int cantidadVertices;

    private Vertice[] vertices;

    private List<Arista>[] vlAristas;

    public GraphLA() {
        cantidadVertices = 0;
        vertices = new Vertice[MAX_VERTICES];
        vlAristas = new ArrayList[MAX_VERTICES];
    }

    private void explicacionEstructura() {
        // EXPLICACION ESTRUCTURA GRAFO
        // vertices                                arreglo
        // vertices[0]                             Vertice
        // vertices[0].getValue()                  String
        // vlAristas                               arreglo
        // vlAristas[0]                            Lista
        // vlAristas[0].get(1)                     Arista
        // vlAristas[0].get(1).getPosVDestino()    int

        // iterando los vertices
        // cantidadVertices define la cantidad de vertices en el grafo
        for (int i = 0; i < cantidadVertices; i++) {
            Vertice v = vertices[i];
            String value = vertices[i].getValue();
            System.out.println(value);
        }

        // iterando las aristas del vertice en la posicion 0
        for (Arista arista : vlAristas[0]) {
            int posVDestino = arista.getPosVDestino();
            System.out.println(vertices[posVDestino].getValue());
        }

        // iterando los vertices
        for (int i = 0; i < cantidadVertices; i++) {
            // iterando las aristas para cada vertice
            for (Arista arista : vlAristas[i]) {

            }
        }

    }

    public boolean existVertice(String vertice) {
        for (int i = 0; i < cantidadVertices; i++) {
            if (vertices[i].getValue().equalsIgnoreCase(vertice))
                return true;
        }
        return false;
    }

    public void addVertices(String ...vertices) {
        for (String vertex : vertices) {
            addVertice(vertex);
        }
    }
    public void addVertice(String vertice) {
        if (existVertice(vertice))
            return;

        vertices[cantidadVertices] = new Vertice(vertice);
        vlAristas[cantidadVertices] = new ArrayList<>();
        cantidadVertices++;
    }

    public boolean existArista(String vOrigen, String vDestino) {
        int posVOrigen = getPosVertice(vOrigen);
        int posVDestino = getPosVertice(vDestino);
        if (posVOrigen == -1 || posVDestino == -1) {
            System.err.println("ERROR: alguno de los vertices no fue encontrado vOrigen: " + vOrigen + " vDestino: " + vDestino);
            return false;
        }

        return vlAristas[posVOrigen].stream().map(Arista::getPosVDestino).anyMatch(x -> x == posVDestino);
    }

    public void addAristas(String vOrigen, String ...vDestinos) {
        for (String vDestino : vDestinos) {
            addArista(vOrigen, vDestino);
        }
    }
    public void addArista(String vOrigen, String vDestino) {
        int posVOrigen = getPosVertice(vOrigen);
        int posVDestino = getPosVertice(vDestino);
        if (posVOrigen == -1 || posVDestino == -1) {
            System.err.println("ERROR: alguno de los vertices no fue encontrado vOrigen: " + vOrigen + " vDestino: " + vDestino);
            return;
        }

        boolean existArista = vlAristas[posVOrigen].stream().map(Arista::getPosVDestino).anyMatch(x -> x == posVDestino);
        if (existArista)
            return;

        vlAristas[posVOrigen].add(new Arista(posVDestino, null));
    }

    public void addAristasBI(String vOrigen, String ...vDestinos) {
        int posVOrigen = getPosVertice(vOrigen);
        if (posVOrigen == -1) {
            System.err.println("ERROR: el vertice: " + vOrigen + " no fue encontrado");
            return;
        }
        for (String vDestino : vDestinos) {
            int posVDestino = getPosVertice(vDestino);
            if (posVDestino == -1) {
                System.err.println("ERROR: el vertice: " + vDestino + " no fue encontrado");
                continue;
            }

            boolean existArista = vlAristas[posVOrigen].stream().map(Arista::getPosVDestino).anyMatch(x -> x == posVDestino);
            if (!existArista)
                vlAristas[posVOrigen].add(new Arista(posVDestino, null));

            existArista = vlAristas[posVDestino].stream().map(Arista::getPosVDestino).anyMatch(x -> x == posVOrigen);
            if (!existArista)
                vlAristas[posVDestino].add(new Arista(posVOrigen, null));
        }
    }

    public int getPosVertice(String vertice) {
        for (int i = 0; i < cantidadVertices; i++) {
            if (vertices[i].getValue().equalsIgnoreCase(vertice)) {
                return i;
            }
        }
        return -1;
    }

    public void print() {
        String line;
        for (int i = 0; i < cantidadVertices; i++) {
            String aristas = "";
            for (Arista arista : vlAristas[i]) {
                int posVDestino = arista.getPosVDestino();
                aristas += " -> " + vertices[posVDestino].getValue();
            }
            line = String.format("[%s]%s", vertices[i].getValue(), aristas);
            System.out.println(line);
        }
    }

    public static void main(String[] args) {
        GraphLA g;

        g = new GraphLA();
        g.addVertices("A", "B", "C", "D");
        g.addAristas("A", "B", "C");
        g.addAristas("B", "A");
        g.addAristas("D", "A", "C", "D");
        g.print();
    }
}
