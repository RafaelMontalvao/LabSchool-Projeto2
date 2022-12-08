package tech.devinhouse.labscholl2.service;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.devinhouse.labscholl2.exception.RegistroNaoEncontradoException;
import tech.devinhouse.labscholl2.model.Aluno;
import tech.devinhouse.labscholl2.model.Pedagogo;
import tech.devinhouse.labscholl2.model.Professor;
import tech.devinhouse.labscholl2.repository.PedagogoRepository;
import tech.devinhouse.labscholl2.repository.ProfessorRepository;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PedagogoService {

    private PedagogoRepository repo;
    public List<Pedagogo> consultar() {

        return repo.findAll();
}

    public Pedagogo consultar(Integer codigo) {
            Optional<Pedagogo> PedagogoOpt = repo.findById(codigo);
            if (PedagogoOpt.isEmpty())
                throw new RegistroNaoEncontradoException("Aluno", codigo);
            Pedagogo pedagogo = PedagogoOpt.get();
            return pedagogo;
        }


    }

