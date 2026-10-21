package br.edu.projetos.dto;

import br.edu.projetos.model.Projeto;
import java.time.LocalDate;
import java.util.List;

public record DadosProjetoDTO(Integer id, String descricao, LocalDate dataInicio, LocalDate dataFim,
                              List<DadosFuncionarioDTO> funcionarios) {
    public static DadosProjetoDTO de(Projeto p) {
        return new DadosProjetoDTO(p.getId(), p.getDescricao(), p.getDataInicio(), p.getDataFim(),
                p.getFuncionarios().stream().map(DadosFuncionarioDTO::de).toList());
    }

    /** Versão sem a lista de funcionários (evita carregar a coleção lazy). */
    public static DadosProjetoDTO resumo(Projeto p) {
        return new DadosProjetoDTO(p.getId(), p.getDescricao(), p.getDataInicio(), p.getDataFim(), List.of());
    }
}
