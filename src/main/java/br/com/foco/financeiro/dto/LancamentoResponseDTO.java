package br.com.foco.financeiro.dto;


import br.com.foco.financeiro.contant.TipoLancamento;
import br.com.foco.financeiro.entity.Lancamento;

import java.math.BigDecimal;
import java.time.LocalDate;

public class LancamentoResponseDTO {
    private Long id;
    private String descricao;
    private BigDecimal valor;
    private TipoLancamento tipo;
    private LocalDate data;
    private CategoriaResponseDTO categoria; // DTO aninhado

    private LancamentoResponseDTO() {}

    public Long getId() { return id; }
    public String getDescricao() { return descricao; }
    public BigDecimal getValor() { return valor; }
    public TipoLancamento getTipo() { return tipo; }
    public LocalDate getData() { return data; }
    public CategoriaResponseDTO getCategoria() { return categoria; }

    /**
     * Fábrica estática para converter uma entidade Lancamento em um LancamentoResponseDTO.
     * @param lancamento A entidade a ser convertida.
     * @return O DTO correspondente.
     */
    public static LancamentoResponseDTO fromEntity(Lancamento lancamento) {
        return new Builder()
                .withId(lancamento.getId())
                .withDescricao(lancamento.getDescricao())
                .withValor(lancamento.getValor())
                .withTipo(lancamento.getTipo())
                .withData(lancamento.getData())
                .withCategoria(CategoriaResponseDTO.fromEntity(lancamento.getCategoria()))
                .build();
    }

    // Builder Pattern
    public static class Builder {
        private Long id;
        private String descricao;
        private BigDecimal valor;
        private TipoLancamento tipo;
        private LocalDate data;
        private CategoriaResponseDTO categoria;

        public Builder withId(Long id) { this.id = id; return this; }
        public Builder withDescricao(String d) { this.descricao = d; return this; }
        public Builder withValor(BigDecimal v) { this.valor = v; return this; }
        public Builder withTipo(TipoLancamento t) { this.tipo = t; return this; }
        public Builder withData(LocalDate d) { this.data = d; return this; }
        public Builder withCategoria(CategoriaResponseDTO c) { this.categoria = c; return this; }

        public LancamentoResponseDTO build() {
            LancamentoResponseDTO dto = new LancamentoResponseDTO();
            dto.id = this.id;
            dto.descricao = this.descricao;
            dto.valor = this.valor;
            dto.tipo = this.tipo;
            dto.data = this.data;
            dto.categoria = this.categoria;
            return dto;
        }
    }
}