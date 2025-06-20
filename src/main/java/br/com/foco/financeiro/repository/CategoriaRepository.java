package br.com.foco.financeiro.repository;

import br.com.foco.financeiro.entity.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
    List<Categoria> findByCarteiraId(Long carteiraId);
}
