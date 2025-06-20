package br.com.foco.financeiro.repository;

import br.com.foco.financeiro.entity.Lancamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long> {
    List<Lancamento> findByCarteiraId(Long carteiraId);
}
