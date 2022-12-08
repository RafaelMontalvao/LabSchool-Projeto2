package tech.devinhouse.labscholl2.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.devinhouse.labscholl2.model.Aluno;
import tech.devinhouse.labscholl2.model.Professor;
import tech.devinhouse.labscholl2.repository.ProfessorRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ProfessorService {

    private ProfessorRepository repo;
    public List<Professor> consultar() {

        return repo.findAll();
    }
}
