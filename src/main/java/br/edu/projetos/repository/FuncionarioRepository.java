package br.edu.projetos.repository;

import br.edu.projetos.model.Funcionario;
import br.edu.projetos.model.Projeto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

    // c) projetos aos quais o funcionario esta vinculado
    @Query("select p from Projeto p join p.funcionarios f where f.id = :idFuncionario")
    List<Projeto> buscarProjetos(@Param("idFuncionario") Integer idFuncionario);
}
