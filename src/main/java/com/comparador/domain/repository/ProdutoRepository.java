package com.comparador.domain.repository;

import com.comparador.domain.model.ProdutoDomain;

import java.math.BigDecimal;
import java.util.List;

public interface ProdutoRepository {

    List<ProdutoDomain> buscarPorIds(List<Long> ids);

    List<ProdutoDomain> buscarTodos();

    List<ProdutoDomain> filtrar(String nome, String descricao, BigDecimal precoMaximo, BigDecimal classificacaoMinima);
}
