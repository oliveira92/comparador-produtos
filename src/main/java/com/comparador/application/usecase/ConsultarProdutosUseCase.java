package com.comparador.application.usecase;

import com.comparador.domain.model.ProdutoDomain;

import java.math.BigDecimal;
import java.util.List;

public interface ConsultarProdutosUseCase {

    List<ProdutoDomain> executarPorIds(List<Long> ids);

    List<ProdutoDomain> filtrar(String nome, String descricao, BigDecimal precoMaximo, BigDecimal classificacaoMinima);
}
