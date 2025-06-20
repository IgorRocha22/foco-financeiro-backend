package br.com.foco.financeiro.service;

import br.com.foco.financeiro.dto.LancamentoResponseDTO;
import br.com.foco.financeiro.entity.Carteira;
import br.com.foco.financeiro.entity.Lancamento;
import br.com.foco.financeiro.exception.handler.RecursoNaoEncontradoException;
import br.com.foco.financeiro.repository.CategoriaRepository;
import br.com.foco.financeiro.repository.LancamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LancamentoService {
    @Autowired
    private LancamentoRepository lancamentoRepository;
    @Autowired
    private CarteiraService carteiraService;
    @Autowired
    private CategoriaRepository categoriaRepository;

    public List<LancamentoResponseDTO> listarTodos() {
        Carteira carteira = carteiraService.getCarteira();
        return lancamentoRepository.findByCarteiraId(carteira.getId()).stream()
                .map(LancamentoResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public LancamentoResponseDTO criarLancamento(Lancamento lancamento) {
        Carteira carteira = carteiraService.getCarteira();

        Long categoriaId = lancamento.getCategoria().getId();
        categoriaRepository.findById(categoriaId)
                .filter(cat -> cat.getCarteira().getId().equals(carteira.getId()))
                .orElseThrow(() -> new RecursoNaoEncontradoException("Categoria com ID " + categoriaId + " não encontrada ou não pertence a este usuário."));

        Lancamento lancamentoSalvo = lancamentoRepository.save(lancamento);
        return LancamentoResponseDTO.fromEntity(lancamentoSalvo);
    }
}