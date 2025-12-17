package com.comparador.application.service;

import com.comparador.application.usecase.ConsultarProdutosUseCase;
import com.comparador.domain.model.ProdutoDomain;
import com.comparador.domain.repository.ProdutoRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

@Service
public class ConsultarProdutosService implements ConsultarProdutosUseCase {

    private final ProdutoRepository produtoRepository;

    public ConsultarProdutosService(ProdutoRepository produtoRepository) {
        this.produtoRepository = produtoRepository;
    }

    @Override
    public List<ProdutoDomain> executarPorIds(List<Long> ids) {
        if (ids == null || ids.isEmpty()) {
            return produtoRepository.buscarTodos();
        }
        List<Long> idsFiltrados = ids.stream().filter(Objects::nonNull).toList();
        return produtoRepository.buscarPorIds(idsFiltrados);
    }

    @Override
    public List<ProdutoDomain> filtrar(String nome, String descricao, BigDecimal precoMaximo, BigDecimal classificacaoMinima) {
        return produtoRepository.filtrar(nome, descricao, precoMaximo, classificacaoMinima);
    }
}
