package tech.devinhouse.labscholl2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.devinhouse.labscholl2.model.Aluno;


import java.util.Optional;

public interface AlunoRepository extends JpaRepository<Aluno,Integer> {

    boolean existsAlunoByCpf(Long cpf);


}



