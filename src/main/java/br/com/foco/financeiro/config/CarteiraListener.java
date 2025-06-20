package br.com.foco.financeiro.config;

import br.com.foco.financeiro.entity.Carteira;
import br.com.foco.financeiro.entity.ICarteira;
import br.com.foco.financeiro.service.CarteiraService;
import jakarta.persistence.PrePersist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CarteiraListener {

    private static CarteiraService carteiraService;

    @Autowired
    public void init(CarteiraService carteiraService) {
        CarteiraListener.carteiraService = carteiraService;
    }

    /**
     * Este método é chamado automaticamente pelo JPA antes de qualquer
     * nova entidade ser persistida (INSERT).
     *
     * @param entity A entidade que está sendo salva.
     */
    @PrePersist
    public void setCarteiraAntesDePersistir(Object entity) {
        if (entity instanceof ICarteira) {
            Carteira carteira = carteiraService.getCarteira();
            ((ICarteira) entity).setCarteira(carteira);
        }
    }
}