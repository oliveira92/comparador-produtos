package com.comparador.domain.model;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class ProdutoDomain {

    private final Long id;
    private final String nome;
    private final String urlImagem;
    private final String descricao;
    private final BigDecimal preco;
    private final BigDecimal classificacao;
    private final List<EspecificacaoDomain> especificacoes;
}
