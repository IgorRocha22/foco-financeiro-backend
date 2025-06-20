package br.com.foco.financeiro.controller;

import br.com.foco.financeiro.dto.LancamentoResponseDTO;
import br.com.foco.financeiro.entity.Lancamento;
import br.com.foco.financeiro.service.LancamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/lancamento")
public class LancamentoController {
    @Autowired
    private LancamentoService lancamentoService;

    @GetMapping
    public List<LancamentoResponseDTO> listarTodos() {
        return lancamentoService.listarTodos();
    }

    @PostMapping
    public ResponseEntity<LancamentoResponseDTO> criarLancamento(@RequestBody Lancamento lancamento) {
        LancamentoResponseDTO novoLancamentoDTO = lancamentoService.criarLancamento(lancamento);
        return new ResponseEntity<>(novoLancamentoDTO, HttpStatus.CREATED);
    }
}