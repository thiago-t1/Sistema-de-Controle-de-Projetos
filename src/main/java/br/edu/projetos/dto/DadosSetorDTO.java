package br.edu.projetos.dto;

import br.edu.projetos.model.Setor;
import java.util.List;

public record DadosSetorDTO(Integer id, String nome, List<DadosFuncionarioDTO> funcionarios) {
    public static DadosSetorDTO de(Setor s) {
        return new DadosSetorDTO(s.getId(), s.getNome(),
                s.getFuncionarios().stream().map(DadosFuncionarioDTO::de).toList());
    }
}
