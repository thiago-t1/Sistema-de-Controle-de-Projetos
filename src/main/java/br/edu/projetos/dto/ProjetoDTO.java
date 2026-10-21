package br.edu.projetos.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record ProjetoDTO(
        @NotBlank(message = "A descrição é obrigatória") String descricao,
        @NotNull(message = "A data de início é obrigatória") LocalDate dataInicio,
        LocalDate dataFim) {}
