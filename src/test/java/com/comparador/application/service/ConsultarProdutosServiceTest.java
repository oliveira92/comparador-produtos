package com.comparador.application.service;

import com.comparador.domain.model.EspecificacaoDomain;
import com.comparador.domain.model.ProdutoDomain;
import com.comparador.domain.repository.ProdutoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;

import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class ConsultarProdutosServiceTest {

    private ProdutoRepository produtoRepository;
    private ConsultarProdutosService consultarProdutosService;

    @BeforeEach
    void setUp() {
        produtoRepository = mock(ProdutoRepository.class);
        consultarProdutosService = new ConsultarProdutosService(produtoRepository);
    }

    @Test
    void deveRetornarTodosQuandoListaDeIdsVazia() {
        List<ProdutoDomain> esperados = List.of(criarProduto(1L), criarProduto(2L));
        when(produtoRepository.buscarTodos()).thenReturn(esperados);

        List<ProdutoDomain> resultado = consultarProdutosService.executarPorIds(List.of());

        assertThat(resultado).isEqualTo(esperados);
        verify(produtoRepository).buscarTodos();
    }

    @Test
    void deveConsultarPorIdsValidos() {
        List<ProdutoDomain> esperados = List.of(criarProduto(1L));
        when(produtoRepository.buscarPorIds(List.of(1L))).thenReturn(esperados);

        List<ProdutoDomain> resultado = consultarProdutosService.executarPorIds(List.of(1L, null));

        ArgumentCaptor<List<Long>> captor = ArgumentCaptor.forClass(List.class);
        verify(produtoRepository).buscarPorIds(captor.capture());
        assertThat(captor.getValue()).containsExactly(1L);
        assertThat(resultado).isEqualTo(esperados);
    }

    @Test
    void deveFiltrarComParametrosInformados() {
        List<ProdutoDomain> esperados = List.of(criarProduto(3L));
        when(produtoRepository.filtrar("fone", "bluetooth", BigDecimal.valueOf(900), BigDecimal.valueOf(4))).thenReturn(esperados);

        List<ProdutoDomain> resultado = consultarProdutosService.filtrar("fone", "bluetooth", BigDecimal.valueOf(900), BigDecimal.valueOf(4));

        assertThat(resultado).isEqualTo(esperados);
        verify(produtoRepository).filtrar("fone", "bluetooth", BigDecimal.valueOf(900), BigDecimal.valueOf(4));
    }

    private ProdutoDomain criarProduto(Long id) {
        return ProdutoDomain.builder()
                .id(id)
                .nome("Produto")
                .urlImagem("url")
                .descricao("descricao")
                .preco(BigDecimal.TEN)
                .classificacao(BigDecimal.ONE)
                .especificacoes(List.of(EspecificacaoDomain.builder()
                        .nome("Chave")
                        .valor("Valor")
                        .build()))
                .build();
    }
}
