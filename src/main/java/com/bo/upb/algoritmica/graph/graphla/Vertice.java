package com.bo.upb.algoritmica.graph.graphla;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Vertice
 *
 * @author Marcos Quispe
 * @since 1.0
 */
@Getter
@Setter
@ToString
public class Vertice {

    private String value;

    private boolean marcado;

    // para Dijsktra
    private Double dijPesoAcumulado;
    private int dijPosVPredecesor;
    private int dijLongitudAcumulada;

    public Vertice(String value) {
        this.value = value;
    }

    public void setDijkstraValues(Double pesoAcumulado, int posVPredecesor, int longitudAcumulada) {
        this.dijPesoAcumulado = pesoAcumulado;
        this.dijPosVPredecesor = posVPredecesor;
        this.dijLongitudAcumulada = longitudAcumulada;
    }
}
