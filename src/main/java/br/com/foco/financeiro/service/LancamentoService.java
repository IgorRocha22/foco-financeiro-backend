package br.com.foco.financeiro.service;

import br.com.foco.financeiro.dto.LancamentoResponseDTO;
import br.com.foco.financeiro.entity.Lancamento;
import br.com.foco.financeiro.repository.LancamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LancamentoService {
    @Autowired
    private LancamentoRepository repository;

    public List<LancamentoResponseDTO> listarTodos(Long usuarioId) {
        return repository.findByUsuarioId(usuarioId).stream()
                .map(LancamentoResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public LancamentoResponseDTO criarLancamento(Lancamento lancamento) {
        Lancamento lancamentoSalvo = repository.save(lancamento);
        return LancamentoResponseDTO.fromEntity(lancamentoSalvo);
    }
}