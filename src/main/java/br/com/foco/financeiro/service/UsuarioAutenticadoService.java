package br.com.foco.financeiro.service;


import br.com.foco.financeiro.entity.Usuario;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UsuarioAutenticadoService {

    /**
     * Obtém a entidade Usuario que corresponde ao principal autenticado na requisição atual.
     *
     * @return A entidade Usuario do usuário logado.
     * @throws IllegalStateException se não houver um usuário autenticado ou o principal não for do tipo esperado.
     */
    public Usuario getUsuario() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()) {
            throw new IllegalStateException("Não há usuário autenticado na sessão.");
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof Usuario) {
            return (Usuario) principal;
        }

        throw new IllegalStateException("O principal da autenticação não é uma instância de Usuario.");
    }
}
