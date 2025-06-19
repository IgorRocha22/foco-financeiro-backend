package br.com.foco.financeiro.service;

import br.com.foco.financeiro.dto.CategoriaResponseDTO;
import br.com.foco.financeiro.entity.Categoria;
import br.com.foco.financeiro.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository repository;

    public List<CategoriaResponseDTO> listarTodas(Long usuarioId) {
        return repository.findByUsuarioId(usuarioId).stream()
                .map(CategoriaResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public CategoriaResponseDTO criarCategoria(Categoria categoria) {
        Categoria categoriaSalva = repository.save(categoria);
        return CategoriaResponseDTO.fromEntity(categoriaSalva);
    }
}

