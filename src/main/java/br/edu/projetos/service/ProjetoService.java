package br.edu.projetos.service;

import br.edu.projetos.dto.DadosProjetoDTO;
import br.edu.projetos.dto.ProjetoDTO;
import br.edu.projetos.exception.RecursoNaoEncontradoException;
import br.edu.projetos.exception.RegraNegocioException;
import br.edu.projetos.model.Funcionario;
import br.edu.projetos.model.Projeto;
import br.edu.projetos.repository.FuncionarioRepository;
import br.edu.projetos.repository.ProjetoRepository;
import java.time.LocalDate;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProjetoService {
    private final ProjetoRepository repository;
    private final FuncionarioRepository funcionarioRepository;

    public ProjetoService(ProjetoRepository repository, FuncionarioRepository funcionarioRepository) {
        this.repository = repository;
        this.funcionarioRepository = funcionarioRepository;
    }

    @Transactional
    public void adicionar(ProjetoDTO dto) {
        if (dto.dataFim() != null && dto.dataFim().isBefore(dto.dataInicio())) {
            throw new RegraNegocioException("A data de fim não pode ser anterior à data de início");
        }
        repository.save(new Projeto(dto.descricao().trim(), dto.dataInicio(), dto.dataFim()));
    }

    @Transactional(readOnly = true)
    public DadosProjetoDTO buscarPorId(Integer id) {
        return repository.buscarComFuncionarios(id).map(DadosProjetoDTO::de)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Projeto " + id + " não encontrado"));
    }

    @Transactional(readOnly = true)
    public List<DadosProjetoDTO> listar() {
        return repository.findAll().stream().map(DadosProjetoDTO::resumo).toList();
    }

    @Transactional(readOnly = true)
    public List<DadosProjetoDTO> buscarPorPeriodo(LocalDate inicio, LocalDate fim) {
        if (fim.isBefore(inicio)) {
            throw new RegraNegocioException("A data final do intervalo não pode ser anterior à inicial");
        }
        return repository.buscarPorInicioEntre(inicio, fim).stream().map(DadosProjetoDTO::resumo).toList();
    }

    @Transactional
    public void vincularFuncionario(Integer idProjeto, Integer idFuncionario) {
        Projeto projeto = repository.buscarComFuncionarios(idProjeto)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Projeto " + idProjeto + " não encontrado"));
        Funcionario funcionario = funcionarioRepository.findById(idFuncionario)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Funcionário " + idFuncionario + " não encontrado"));
        boolean jaVinculado = projeto.getFuncionarios().stream().anyMatch(f -> f.getId().equals(idFuncionario));
        if (jaVinculado) {
            throw new RegraNegocioException("O funcionário já está vinculado a este projeto");
        }
        projeto.getFuncionarios().add(funcionario);
    }
}
