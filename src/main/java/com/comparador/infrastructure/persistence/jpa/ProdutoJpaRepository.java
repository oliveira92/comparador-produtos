package com.comparador.infrastructure.persistence.jpa;

import com.comparador.infrastructure.persistence.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProdutoJpaRepository extends JpaRepository<ProdutoEntity, Long> {

    List<ProdutoEntity> findByIdIn(List<Long> ids);

    @Query("""
            SELECT p FROM ProdutoEntity p
            WHERE (:nome IS NULL OR LOWER(p.nome) LIKE LOWER(CONCAT('%', :nome, '%')))
              AND (:descricao IS NULL OR LOWER(p.descricao) LIKE LOWER(CONCAT('%', :descricao, '%')))
              AND (:precoMaximo IS NULL OR p.preco <= :precoMaximo)
              AND (:classificacaoMinima IS NULL OR p.classificacao >= :classificacaoMinima)
            """)
    List<ProdutoEntity> filtrar(@Param("nome") String nome,
                                @Param("descricao") String descricao,
                                @Param("precoMaximo") BigDecimal precoMaximo,
                                @Param("classificacaoMinima") BigDecimal classificacaoMinima);
}
