package com.comparador.infrastructure.api.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class ProdutoControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void deveRetornarProdutosParaComparacao() throws Exception {
        mockMvc.perform(get("/api/produtos/comparacao")
                        .param("ids", "1", "2")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Smartphone X"))
                .andExpect(jsonPath("$[0].preco").value(3599.90))
                .andExpect(jsonPath("$[0].especificacoes[0].nome").value("Tela"))
                .andExpect(jsonPath("$[1].nome").value("Notebook Pro"));
    }

    @Test
    void deveFiltrarProdutosPorNomeEPreco() throws Exception {
        mockMvc.perform(get("/api/produtos/filtro")
                        .param("nome", "Smart")
                        .param("precoMaximo", "1300")
                        .param("classificacaoMinima", "4.2")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Smartwatch Active"))
                .andExpect(jsonPath("$[0].preco").value(1299.50));
    }
}
