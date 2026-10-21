package br.edu.projetos.repository;

import br.edu.projetos.model.Setor;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface SetorRepository extends JpaRepository<Setor, Integer> {

    // d) todos os setores com seus funcionarios
    @Query("select distinct s from Setor s left join fetch s.funcionarios")
    List<Setor> listarComFuncionarios();

    @Query("select s from Setor s left join fetch s.funcionarios where s.id = :id")
    Optional<Setor> buscarComFuncionarios(@Param("id") Integer id);

    boolean existsByNomeIgnoreCase(String nome);
}
