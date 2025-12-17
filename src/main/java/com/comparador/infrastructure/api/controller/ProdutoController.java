package com.comparador.infrastructure.api.controller;

import com.comparador.application.usecase.ConsultarProdutosUseCase;
import com.comparador.domain.model.ProdutoDomain;
import com.comparador.infrastructure.api.dto.EspecificacaoResponse;
import com.comparador.infrastructure.api.dto.ProdutoResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private final ConsultarProdutosUseCase consultarProdutosUseCase;

    public ProdutoController(ConsultarProdutosUseCase consultarProdutosUseCase) {
        this.consultarProdutosUseCase = consultarProdutosUseCase;
    }

    @GetMapping(value = "/comparacao", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProdutoResponse>> buscarParaComparacao(@RequestParam(name = "ids", required = false) List<Long> ids) {
        List<ProdutoDomain> produtos = consultarProdutosUseCase.executarPorIds(ids);
        List<ProdutoResponse> resposta = produtos.stream()
                .map(this::mapearResposta)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resposta);
    }

    @GetMapping(value = "/filtro", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<ProdutoResponse>> filtrarProdutos(@RequestParam(name = "nome", required = false) String nome,
                                                                 @RequestParam(name = "descricao", required = false) String descricao,
                                                                 @RequestParam(name = "precoMaximo", required = false) BigDecimal precoMaximo,
                                                                 @RequestParam(name = "classificacaoMinima", required = false) BigDecimal classificacaoMinima) {
        List<ProdutoDomain> produtos = consultarProdutosUseCase.filtrar(nome, descricao, precoMaximo, classificacaoMinima);
        List<ProdutoResponse> resposta = produtos.stream()
                .map(this::mapearResposta)
                .collect(Collectors.toList());
        return ResponseEntity.ok(resposta);
    }

    private ProdutoResponse mapearResposta(ProdutoDomain domain) {
        List<EspecificacaoResponse> especificacoes = domain.getEspecificacoes()
                .stream()
                .map(e -> new EspecificacaoResponse(e.getNome(), e.getValor()))
                .collect(Collectors.toList());

        return new ProdutoResponse(
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
