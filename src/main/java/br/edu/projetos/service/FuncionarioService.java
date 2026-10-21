package br.edu.projetos.service;

import br.edu.projetos.dto.DadosProjetoDTO;
import br.edu.projetos.dto.FuncionarioDTO;
import br.edu.projetos.exception.RecursoNaoEncontradoException;
import br.edu.projetos.model.Funcionario;
import br.edu.projetos.model.Setor;
import br.edu.projetos.repository.FuncionarioRepository;
import br.edu.projetos.repository.SetorRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FuncionarioService {
    private final FuncionarioRepository repository;
    private final SetorRepository setorRepository;

    public FuncionarioService(FuncionarioRepository repository, SetorRepository setorRepository) {
        this.repository = repository;
        this.setorRepository = setorRepository;
    }

    @Transactional
    public void adicionar(FuncionarioDTO dto) {
        Setor setor = setorRepository.findById(dto.idSetor())
                .orElseThrow(() -> new RecursoNaoEncontradoException("Setor " + dto.idSetor() + " não encontrado"));
        Funcionario f = new Funcionario(dto.nome().trim());
        f.setSetor(setor);
        repository.save(f);
    }

    @Transactional(readOnly = true)
    public List<DadosProjetoDTO> buscarProjetos(Integer idFuncionario) {
        if (!repository.existsById(idFuncionario)) {
            throw new RecursoNaoEncontradoException("Funcionário " + idFuncionario + " não encontrado");
        }
        return repository.buscarProjetos(idFuncionario).stream().map(DadosProjetoDTO::resumo).toList();
    }
}
