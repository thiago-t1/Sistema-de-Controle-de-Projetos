package br.edu.projetos.repository;

import br.edu.projetos.model.Projeto;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProjetoRepository extends JpaRepository<Projeto, Integer> {

    // a) projeto + funcionarios vinculados
    @Query("select p from Projeto p left join fetch p.funcionarios where p.id = :id")
    Optional<Projeto> buscarComFuncionarios(@Param("id") Integer id);

    // b) projetos cujo inicio esta no intervalo
    @Query("select p from Projeto p where p.dataInicio between :inicio and :fim order by p.dataInicio")
    List<Projeto> buscarPorInicioEntre(@Param("inicio") LocalDate inicio, @Param("fim") LocalDate fim);
}
