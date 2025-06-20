package br.com.foco.financeiro.service;

import br.com.foco.financeiro.entity.Carteira;
import br.com.foco.financeiro.entity.Usuario;
import br.com.foco.financeiro.exception.handler.RecursoNaoEncontradoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CarteiraService {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    /**
     * Busca a carteira do usuário autenticado na sessão atual.
     * Este método abstrai completamente a necessidade de saber sobre IDs de usuário.
     *
     * @return A entidade Carteira do usuário logado.
     * @throws RecursoNaoEncontradoException se o usuário não possuir uma carteira (o que seria uma inconsistência de dados).
     */
    public Carteira getCarteira() {
        Usuario usuarioLogado = usuarioAutenticadoService.getUsuario();
        Carteira carteira = usuarioLogado.getCarteira();

        if (carteira == null) {
            throw new RecursoNaoEncontradoException("Carteira não encontrada para o usuário " + usuarioLogado.getUsername());
        }
        return carteira;
    }
}