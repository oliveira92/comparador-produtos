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
                .map(e -> new EspecificacaoDomain(e.getNome(), e.getValor()))
                .collect(Collectors.toList());
        return new ProdutoDomain(
                entity.getId(),
                entity.getNome(),
                entity.getUrlImagem(),
                entity.getDescricao(),
                entity.getPreco(),
                entity.getClassificacao(),
                especificacoes
        );
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
