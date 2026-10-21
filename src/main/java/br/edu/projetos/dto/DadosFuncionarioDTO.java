package br.edu.projetos.dto;

import br.edu.projetos.model.Funcionario;

public record DadosFuncionarioDTO(Integer id, String nome) {
    public static DadosFuncionarioDTO de(Funcionario f) {
        return new DadosFuncionarioDTO(f.getId(), f.getNome());
    }
}
