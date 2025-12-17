package com.comparador.infrastructure.persistence.mapper;

import com.comparador.domain.model.EspecificacaoDomain;
import com.comparador.domain.model.ProdutoDomain;
import com.comparador.infrastructure.persistence.entity.EspecificacaoEmbeddable;
import com.comparador.infrastructure.persistence.entity.ProdutoEntity;

import java.util.List;
import java.util.stream.Collectors;

public class ProdutoMapper {

    private ProdutoMapper() {
    }

    public static ProdutoDomain mapearParaDomain(ProdutoEntity entity) {
        List<EspecificacaoDomain> especificacoes = entity.getEspecificacoes()
                .stream()
                .map(e -> EspecificacaoDomain.builder()
                        .nome(e.getNome())
                        .valor(e.getValor())
                        .build())
                .collect(Collectors.toList());
        return ProdutoDomain.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .urlImagem(entity.getUrlImagem())
                .descricao(entity.getDescricao())
                .preco(entity.getPreco())
                .classificacao(entity.getClassificacao())
                .especificacoes(especificacoes)
                .build();
    }

    public static ProdutoEntity mapearParaEntity(ProdutoDomain domain) {
        List<EspecificacaoEmbeddable> especificacoes = domain.getEspecificacoes()
                .stream()
                .map(e -> new EspecificacaoEmbeddable(e.getNome(), e.getValor()))
                .collect(Collectors.toList());
        return new ProdutoEntity(
                domain.getId(),
                domain.getNome(),
                domain.getUrlImagem(),
                domain.getDescricao(),
                domain.getPreco(),
                domain.getClassificacao(),
                especificacoes
        );
    }
}
