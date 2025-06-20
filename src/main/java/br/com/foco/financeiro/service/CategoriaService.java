package br.com.foco.financeiro.service;

import br.com.foco.financeiro.dto.CategoriaResponseDTO;
import br.com.foco.financeiro.entity.Carteira;
import br.com.foco.financeiro.entity.Categoria;
import br.com.foco.financeiro.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoriaService {
    @Autowired
    private CategoriaRepository categoriaRepository;
    @Autowired
    private CarteiraService carteiraService;

    public List<CategoriaResponseDTO> listarTodas() {
        Carteira carteira = carteiraService.getCarteira();
        return categoriaRepository.findByCarteiraId(carteira.getId()).stream()
                .map(CategoriaResponseDTO::fromEntity)
                .collect(Collectors.toList());
    }

    public CategoriaResponseDTO criarCategoria(Categoria categoria) {
        Categoria categoriaSalva = categoriaRepository.save(categoria);
        return CategoriaResponseDTO.fromEntity(categoriaSalva);
    }
}

