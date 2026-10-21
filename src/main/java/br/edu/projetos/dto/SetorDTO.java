package br.edu.projetos.dto;

import jakarta.validation.constraints.NotBlank;

public record SetorDTO(@NotBlank(message = "O nome é obrigatório") String nome) {}
