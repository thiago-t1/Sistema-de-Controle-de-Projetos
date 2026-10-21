package br.edu.projetos.service;

import br.edu.projetos.dto.DadosSetorDTO;
import br.edu.projetos.dto.SetorDTO;
import br.edu.projetos.exception.RecursoNaoEncontradoException;
import br.edu.projetos.exception.RegraNegocioException;
import br.edu.projetos.model.Setor;
import br.edu.projetos.repository.SetorRepository;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SetorService {
    private final SetorRepository repository;

    public SetorService(SetorRepository repository) { this.repository = repository; }

    @Transactional
    public void adicionar(SetorDTO dto) {
        String nome = dto.nome().trim();
        if (repository.existsByNomeIgnoreCase(nome)) {
            throw new RegraNegocioException("Já existe um setor com o nome " + nome);
        }
        repository.save(new Setor(nome));
    }

    @Transactional(readOnly = true)
    public DadosSetorDTO buscarPorId(Integer id) {
        return repository.buscarComFuncionarios(id).map(DadosSetorDTO::de)
                .orElseThrow(() -> new RecursoNaoEncontradoException("Setor " + id + " não encontrado"));
    }

    @Transactional(readOnly = true)
    public List<DadosSetorDTO> listar() {
        return repository.listarComFuncionarios().stream().map(DadosSetorDTO::de).toList();
    }
}
