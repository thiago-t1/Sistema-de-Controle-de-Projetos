package br.edu.projetos.controller;

import br.edu.projetos.dto.DadosSetorDTO;
import br.edu.projetos.dto.SetorDTO;
import br.edu.projetos.service.SetorService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/setores")
public class SetorController {
    private final SetorService service;

    public SetorController(SetorService service) { this.service = service; }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void adicionar(@Valid @RequestBody SetorDTO setor) { service.adicionar(setor); }

    @GetMapping
    public List<DadosSetorDTO> listar() { return service.listar(); }

    @GetMapping("/{idSetor}")
    public DadosSetorDTO buscarSetorPorId(@PathVariable Integer idSetor) { return service.buscarPorId(idSetor); }
}
