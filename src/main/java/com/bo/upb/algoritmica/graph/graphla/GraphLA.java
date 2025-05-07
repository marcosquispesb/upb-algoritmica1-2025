package com.bo.upb.algoritmica.graph.graphla;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
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
        addArista(vOrigen, vDestino, null);
    }
    public void addArista(String vOrigen, String vDestino, Double peso) {
        int posVOrigen = getPosVertice(vOrigen);
        int posVDestino = getPosVertice(vDestino);
        if (posVOrigen == -1 || posVDestino == -1) {
            System.err.println("ERROR: alguno de los vertices no fue encontrado vOrigen: " + vOrigen + " vDestino: " + vDestino);
            return;
        }

        boolean existArista = vlAristas[posVOrigen].stream().map(Arista::getPosVDestino).anyMatch(x -> x == posVDestino);
        if (existArista)
            return;

        vlAristas[posVOrigen].add(new Arista(posVDestino, peso));
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

    public void addAristaBI(String vOrigen, String vDestino, Double peso) {
        int posVOrigen = getPosVertice(vOrigen);
        if (posVOrigen == -1) {
            System.err.println("ERROR: el vertice: " + vOrigen + " no fue encontrado");
            return;
        }

        int posVDestino = getPosVertice(vDestino);
        if (posVDestino == -1) {
            System.err.println("ERROR: el vertice: " + vDestino + " no fue encontrado");
            return;
        }

        boolean existArista = vlAristas[posVOrigen].stream().map(Arista::getPosVDestino).anyMatch(x -> x == posVDestino);
        if (!existArista)
            vlAristas[posVOrigen].add(new Arista(posVDestino, peso));

        existArista = vlAristas[posVDestino].stream().map(Arista::getPosVDestino).anyMatch(x -> x == posVOrigen);
        if (!existArista)
            vlAristas[posVDestino].add(new Arista(posVOrigen, peso));
    }

    public int getPosVertice(String vertice) {
        for (int i = 0; i < cantidadVertices; i++) {
            if (vertices[i].getValue().equalsIgnoreCase(vertice)) {
                return i;
            }
        }
        return -1;
    }

    public void deleteVertice(String vertice) {
        int posVerticeDelete = getPosVertice(vertice);

        // eliminar aristas igual a posVerticeDelete
        for (int i = 0; i < cantidadVertices; i++) {
            for (int j = 0; j < vlAristas[i].size(); j++) {
                Arista arista = vlAristas[i].get(j);
                if (arista.getPosVDestino() == posVerticeDelete) {
                    vlAristas[i].remove(j);
                    break;
                }
            }
        }

        // recorrer hacia atras desde posVerticeDelete + 1
        for (int i = posVerticeDelete + 1; i < cantidadVertices; i++) {
            vertices[i - 1] = vertices[i];
            vlAristas[i - 1] = vlAristas[i];
        }

        // eliminar los ultimos elementos
        vertices[cantidadVertices - 1] = null;
        vlAristas[cantidadVertices - 1] = null;
        cantidadVertices--;

        // decrementar en 1 los posVDestino > que posVerticeDelete
        for (int i = 0; i < cantidadVertices; i++) {
            for (Arista arista : vlAristas[i]) {
                if (arista.getPosVDestino() > posVerticeDelete)
                    arista.setPosVDestino(arista.getPosVDestino() - 1);
            }
        }
    }


    public List<String> aqsa(String vertice) {
        int posVertice = getPosVertice(vertice);
        List<String> result = new ArrayList<>();
        for (int i = 0; i < cantidadVertices; i++) {
            for (Arista arista : vlAristas[i]) {
                if (arista.getPosVDestino() == posVertice) {
                    result.add(vertices[i].getValue());
                    break;
                }
            }
        }
        return result;
    }

    private List<Integer> aqsa(int posVertice) {
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < cantidadVertices; i++) {
            for (Arista arista : vlAristas[i]) {
                if (arista.getPosVDestino() == posVertice) {
                    result.add(i);
                    break;
                }
            }
        }
        return result;
    }

    public List<String> solitarios() {
        return null;
    }

    public boolean esGrafoNoDirigido() {
        // implement
        return false;
    }

    public void deleteBucles() {
        // implement
    }

    public int countAristasBI() {
        // implement
        return 0;
    }

    public void desmarcarTodos() {
        for (int i = 0; i < cantidadVertices; i++) {
            vertices[i].setMarcado(false);
        }
    }
    public void marcar(int i) {
        vertices[i].setMarcado(true);
    }
    public void desmarcar(int i) {
        vertices[i].setMarcado(false);
    }
    public boolean isMarcado(int i) {
        return vertices[i].isMarcado();
    }
    private boolean estanTodosMarcados() {
        for (int i = 0; i < cantidadVertices; i++) {
            if (!isMarcado(i))
                return false;
        }
        return true;
    }

    public void dfs() {
        desmarcarTodos();
        for (int i = 0; i < cantidadVertices; i++) {
            if (!isMarcado(i)) {
                System.out.println("dfs principal: " + i);
                dfs(i);
            }
        }
    }
    public void dfs(int u) {
        marcar(u);
        for (Arista arista : vlAristas[u]) {
            int v = arista.getPosVDestino();
            if (!isMarcado(v))
                dfs(v);
        }
    }

    public boolean hayCamino(int u, int v) {
        desmarcarTodos();
        dfs(u);
        return isMarcado(v);
    }

    public void dfsConectados(int u) {
        marcar(u);
        for (Arista arista : vlAristas[u]) {
            int v = arista.getPosVDestino();
            if (!isMarcado(v))
                dfsConectados(v);
        }

        for (Integer w : aqsa(u)) {
            if (!isMarcado(w))
                dfsConectados(w);
        }
    }

    // estanTodosConectados
    public boolean esConexo() {
        desmarcarTodos();
        dfsConectados(0);
        return estanTodosMarcados();
    }

    public boolean hayCircuito(String vertice) {
        int posV = getPosVertice(vertice);
        if(posV == -1)
            return false;

        desmarcarTodos();
        marcar(posV);
        for (Arista arista : vlAristas[posV]) {
            if (hayCircuito(arista.getPosVDestino(), posV, 0))
                return true;
        }
        return false;
    }

    private boolean hayCircuito(int actual, int origen, int contador) {
        marcar(actual);
        contador = contador + 1;

        for (Arista arista : vlAristas[actual]) {
            int posVAdyacente = arista.getPosVDestino();
            if (posVAdyacente == actual) // bucle
                continue;

            if (posVAdyacente == origen && contador + 1 >= 3) // circuito encontrado con las de 2 aristas
                return true;

            if (!isMarcado(posVAdyacente))
                if (hayCircuito(posVAdyacente, origen, contador)) // recorrer adyacentes no marcados
                    return true;
        }
        return false;
    }

    public int caminoLargo(String origen, String destino) {
        int posOrigen = getPosVertice(origen);
        int posDestino = getPosVertice(destino);
        if (posOrigen == -1 || posDestino == -1)
            return -1;

        desmarcarTodos();
        return dfsCaminoLargo(posOrigen, posDestino, 0);
    }
    public int dfsCaminoLargo(int actual, int destino, int contador) {
        if (actual == destino)
            return contador;

        marcar(actual);
        int result = 0;
        for (Arista arista : vlAristas[actual]) {
            int v = arista.getPosVDestino();
            if (!isMarcado(v)) {
                int caminoAdyacente = dfsCaminoLargo(v, destino, contador + 1);
                result = Math.max(result, caminoAdyacente);
                desmarcar(v);
            }
        }
        return result;
    }

    public Integer getPosVConMenorPesoAcum() {
        return null;
    }
    public Double dijkstra(String vOrigen, String vDestino) {
        int posVOrigen = getPosVertice(vOrigen);
        int posVDestino = getPosVertice(vDestino);
        if (posVOrigen == -1 || posVDestino == -1)
            return null;

        vertices[posVOrigen].setDijkstraValues(0d, -1, 0);

        Integer u = getPosVConMenorPesoAcum();
        while(u != null) {
            for (Arista arista : vlAristas[u]) {
                int v = arista.getPosVDestino();
                if (isMarcado(v))
                    continue;

                Double nuevoPesoAcum = vertices[u].getDijPesoAcumulado() + arista.getPeso();
                if (vertices[v].getDijPesoAcumulado() == null || nuevoPesoAcum < vertices[v].getDijPesoAcumulado()) {
                    vertices[v].setDijkstraValues(nuevoPesoAcum, u, vertices[u].getDijLongitudAcumulada() + 1);
                }
            }
            marcar(u);

            u = getPosVConMenorPesoAcum();
        }

        return vertices[posVDestino].getDijPesoAcumulado();
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
            line = (isMarcado(i) ? "." : " ") + line;
            System.out.println(line);
        }
    }

    public static void main(String[] args) {
        GraphLA g;

//        g = new GraphLA();
//        g.addVertices("A", "B", "C", "D");
//        g.addAristas("A", "B", "C");
//        g.addAristas("B", "A");
//        g.addAristas("D", "A", "C", "D");
//        g.print();
//
//        System.out.println();
//        g.deleteVertice("B");
//        g.print();

//        g = new GraphLA();
//        g.addVertices("A", "B", "C", "D");
//        g.addAristasBI("A", "B", "D");
//        //g.addAristasBI("B", "D");
//        g.addAristasBI("D", "B", "C");
//        g.print();

//        g = new GraphLA();
//        g.addVertices("0", "1", "2", "3", "4", "5", "6");
//        g.addAristasBI("0", "1", "2", "3");
//        g.addAristasBI("2", "3");
//        g.addAristasBI("4", "6");
//        System.out.println();
//        g.dfs();
//        g.print();

//        g = new GraphLA();
//        g.addVertices("0", "1", "2", "3", "4", "5", "6");
//        g.addAristas("0", "1", "3");;
//        g.addAristas("3", "2", "4");
//        g.addAristas("4", "6");
//        g.addAristas("6", "4", "5");
        //System.out.println("hayCamino: " + g.hayCamino(1, 5));
//        g.print();
//        System.out.println();

//        g = new GraphLA();
//        g.addVertices("0", "1", "2", "3", "4", "5", "6");
//        g.addAristas("0", "1", "3");;
//        g.addAristas("3", "2");
//        g.addAristas("4", "3", "6");
//        g.addAristas("6", "4");
//        g.addAristas("5", "6");
//        System.out.println("esConexo: " + g.esConexo());
//        g.print();
//        System.out.println();
        //System.out.println("aqsa: " + g.aqsa("3"));

        g = new GraphLA();
//        g.addVertices("0", "1", "2", "3", "4");
//        g.addAristas("0", "1", "3");;
//        g.addAristas("1", "1");
//        g.addAristas("2", "0", "3");
//        g.addAristas("3", "0", "3", "4");
//        g.addAristas("4", "2");

//        g.addVertices("0", "1", "2", "3", "4");
//        g.addAristas("0", "1", "3");;
//        g.addAristas("1", "1");
//        g.addAristas("2", "0", "3");
//        g.addAristas("3", "0");
//        g.addAristas("4", "2");
//        g.print();
//        System.out.println(g.hayCircuito("0"));
//        System.out.println(g.hayCircuito("3"));
//        System.out.println(g.hayCircuito("1"));

//        g = new GraphLA();
//        g.addVertices("0", "1", "2", "3", "4", "5", "6");
//        g.addAristas("0", "1", "3", "5");
//        g.addAristas("3", "2", "4");
//        g.addAristas("4", "4", "5", "6");
//        g.addAristas("6", "4", "5");
//        g.addVertices("0", "1", "2", "3", "4", "5", "6");
//        g.addAristas("0", "1", "3", "5");
//        g.addAristas("3", "2", "4", "6");
//        g.addAristas("4", "4", "5");
//        g.addAristas("6", "3", "4", "5");
//        g.print();
//        System.out.println("caminoLargo: " + g.caminoLargo("0", "5"));
//
//        g = new GraphLA();
//        g.addVertices("0", "1", "2", "3", "4");
//        g.addAristas("0", "1", "3");
//        g.addAristas("1", "4");
//        g.addAristas("2", "0");
//        g.addAristas("3", "0", "2", "3");
//        g.addAristas("4", "3");

//        List<String> list = new ArrayList<>(Arrays.asList("A", "B", "C"));
//        int i = 0;
//        for (String s : list) {
//            if (i == 0) {
//                list.remove(i);
//            }
//            i++;
//        }
//        System.out.println(list);
//        for (int j = 0; j < list.size(); j++) {
//            System.out.println(list.get(j));
//            if (i == 0) {
//                list.remove(i);
//                j--;
//            }
//            i++;
//        }
//        System.out.println(list);

        g = new GraphLA();
        g.addVertices("A", "B", "C", "D", "E", "F", "G", "H");
        g.addAristaBI("A", "C", 1d);
        g.addAristaBI("A", "B", 3d);
        g.addAristaBI("B", "D", 1d);
        g.addAristaBI("B", "G", 5d);
        g.addAristaBI("C", "D", 2d);
        g.addAristaBI("C", "F", 5d);
        g.addAristaBI("D", "F", 2d);
        g.addAristaBI("D", "E", 4d);
        g.addAristaBI("E", "G", 2d);
        g.addAristaBI("E", "H", 1d);
        g.addAristaBI("F", "H", 3d);
        g.print();
        System.out.println("dij menor distancia: " + g.dijkstra("A", "H"));
    }
}
