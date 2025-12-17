package com.comparador.domain.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@AllArgsConstructor
@EqualsAndHashCode
public class EspecificacaoDomain {

    private final String nome;
    private final String valor;
}
