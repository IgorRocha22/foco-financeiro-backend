package br.com.foco.financeiro.dto;

import br.com.foco.financeiro.entity.Categoria;

public class CategoriaResponseDTO {
    private Long id;
    private String nome;
    private String descricao;

    private CategoriaResponseDTO() {}

    public Long getId() { return id; }
    public String getNome() { return nome; }
    public String getDescricao() { return descricao; }

    /**
     * Fábrica estática para converter uma entidade Categoria em um CategoriaResponseDTO.
     * @param categoria A entidade a ser convertida.
     * @return O DTO correspondente.
     */
    public static CategoriaResponseDTO fromEntity(Categoria categoria) {
        return new Builder()
                .withId(categoria.getId())
                .withNome(categoria.getNome())
                .withDescricao(categoria.getDescricao())
                .build();
    }

    public static class Builder {
        private Long id;
        private String nome;
        private String descricao;

        public Builder withId(Long id) {
            this.id = id;
            return this;
        }
        public Builder withNome(String nome) {
            this.nome = nome;
            return this;
        }
        public Builder withDescricao(String descricao) {
            this.descricao = descricao;
            return this;
        }
        public CategoriaResponseDTO build() {
            CategoriaResponseDTO dto = new CategoriaResponseDTO();
            dto.id = this.id;
            dto.nome = this.nome;
            dto.descricao = this.descricao;
            return dto;
        }
    }
}