package com.comparador.infrastructure.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdutoResponse {

    private Long id;
    private String nome;
    private String urlImagem;
    private String descricao;
    private BigDecimal preco;
    private BigDecimal classificacao;
    private List<EspecificacaoResponse> especificacoes;
}
