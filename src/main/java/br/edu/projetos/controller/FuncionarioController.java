package br.edu.projetos.controller;

import br.edu.projetos.dto.DadosProjetoDTO;
import br.edu.projetos.dto.FuncionarioDTO;
import br.edu.projetos.service.FuncionarioService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/funcionarios")
public class FuncionarioController {
    private final FuncionarioService service;

    public FuncionarioController(FuncionarioService service) { this.service = service; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionar(@Valid @RequestBody FuncionarioDTO funcionario) { service.adicionar(funcionario); }

    @GetMapping("/{idFuncionario}/projetos")
    public List<DadosProjetoDTO> buscarProjetos(@PathVariable Integer idFuncionario) {
        return service.buscarProjetos(idFuncionario);
    }
}
