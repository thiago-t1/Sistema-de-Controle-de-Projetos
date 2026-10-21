package br.edu.projetos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record FuncionarioDTO(
        @NotBlank(message = "O nome é obrigatório") String nome,
        @NotNull(message = "O setor é obrigatório") Integer idSetor) {}
