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

    // EXPLICACION
    // vertices                                arreglo
    // vertices[0]                             Vertice
    // vertices[0].getValue()                  String
    // vlAristas                               arreglo
    // vlAristas[0]                            Lista
    // vlAristas[0].get(1)                     Arista
    // vlAristas[0].get(1).getPosVDestino()    int

    public void addVertices(String ...vertices) {
        for (String vertex : vertices) {
            addVertice(vertex);
        }
    }
    public void addVertice(String vertice) {
        vertices[cantidadVertices] = new Vertice(vertice);
        vlAristas[cantidadVertices] = new ArrayList<>();
        cantidadVertices++;
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
        vlAristas[posVOrigen].add(new Arista(posVDestino, null));
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
//            for (int j = 0; j < vlAristas[i].size(); j++) {
//                int posVDestino = vlAristas[i].get(j).getPosVDestino();
                aristas += " -> " + vertices[posVDestino].getValue();
            }
            line = String.format("[%s]%s", vertices[i].getValue(), aristas);
            System.out.println(line);
        }
    }

    public static void main(String[] args) {
        GraphLA g = new GraphLA();
        g.addVertices("0", "1", "2", "3");
        g.addAristas("0", "2", "1");
        g.addAristas("1", "0", "1", "2");
        g.addAristas("3", "0");

        g.print();
    }
}
