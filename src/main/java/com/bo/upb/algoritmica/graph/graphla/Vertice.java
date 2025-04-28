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

    public Vertice(String value) {
        this.value = value;
    }
}
