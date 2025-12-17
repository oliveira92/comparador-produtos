package com.comparador.infrastructure.persistence;

import com.comparador.domain.model.ProdutoDomain;
import com.comparador.domain.repository.ProdutoRepository;
import com.comparador.infrastructure.persistence.jpa.ProdutoJpaRepository;
import com.comparador.infrastructure.persistence.mapper.ProdutoMapper;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProdutoRepositoryImpl implements ProdutoRepository {

    private final ProdutoJpaRepository produtoJpaRepository;

    public ProdutoRepositoryImpl(ProdutoJpaRepository produtoJpaRepository) {
        this.produtoJpaRepository = produtoJpaRepository;
    }

    @Override
    public List<ProdutoDomain> buscarPorIds(List<Long> ids) {
        return produtoJpaRepository.findByIdIn(ids)
                .stream()
                .map(ProdutoMapper::mapearParaDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProdutoDomain> buscarTodos() {
        return produtoJpaRepository.findAll()
                .stream()
                .map(ProdutoMapper::mapearParaDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<ProdutoDomain> filtrar(String nome, String descricao, BigDecimal precoMaximo, BigDecimal classificacaoMinima) {
        return produtoJpaRepository.filtrar(nome, descricao, precoMaximo, classificacaoMinima)
                .stream()
                .map(ProdutoMapper::mapearParaDomain)
                .collect(Collectors.toList());
    }
}
