package tech.devinhouse.labscholl2.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tech.devinhouse.labscholl2.model.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Integer> {

}
