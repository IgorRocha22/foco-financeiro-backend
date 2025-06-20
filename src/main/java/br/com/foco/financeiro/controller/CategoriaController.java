package br.com.foco.financeiro.controller;

import br.com.foco.financeiro.dto.CategoriaResponseDTO;
import br.com.foco.financeiro.entity.Categoria;
import br.com.foco.financeiro.entity.Usuario;
import br.com.foco.financeiro.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categoria")
@CrossOrigin(origins = "*")
public class CategoriaController {
    @Autowired
    private CategoriaService categoriaService;

    @GetMapping
    public List<CategoriaResponseDTO> listarTodas(@AuthenticationPrincipal Usuario usuario) {
        return categoriaService.listarTodas(usuario.getId());
    }

    @PostMapping
    public ResponseEntity<CategoriaResponseDTO> criarCategoria(@RequestBody Categoria categoria, @AuthenticationPrincipal Usuario usuario) {
        categoria.setUsuario(usuario);
        CategoriaResponseDTO novaCategoriaDTO = categoriaService.criarCategoria(categoria);
        return new ResponseEntity<>(novaCategoriaDTO, HttpStatus.CREATED);
    }
}