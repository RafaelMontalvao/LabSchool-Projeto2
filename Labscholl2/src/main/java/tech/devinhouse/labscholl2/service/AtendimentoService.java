package tech.devinhouse.labscholl2.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.devinhouse.labscholl2.exception.RegistroNaoEncontradoException;
import tech.devinhouse.labscholl2.model.Aluno;
import tech.devinhouse.labscholl2.model.Pedagogo;
import tech.devinhouse.labscholl2.model.SituacaoMatricula;
import tech.devinhouse.labscholl2.repository.AlunoRepository;
import tech.devinhouse.labscholl2.repository.PedagogoRepository;

import java.util.Optional;
@Service
@AllArgsConstructor
public class AtendimentoService {

    private AlunoRepository aRepo;
    private PedagogoRepository pRepo;

    public void atendimentoPedagogico(Integer codAluno, Integer codPedagogo) {
        Optional<Aluno> alunoOpt = aRepo.findById(codAluno);
        Optional<Pedagogo> pedagogoOpt = pRepo.findById(codPedagogo);
        if(alunoOpt.isEmpty())
            throw new RegistroNaoEncontradoException("Aluno", codAluno);
        if(pedagogoOpt.isEmpty())
           throw new RegistroNaoEncontradoException("Pedagogo", codPedagogo);
        Aluno alunoBD = alunoOpt.get();
        Pedagogo pedagogoBD = pedagogoOpt.get();
        alunoBD.setSituacao(SituacaoMatricula.ATENDIMENTO_PEDAGOGICO);
        alunoBD.setAtendimentos(alunoBD.getAtendimentos()+1);
        pedagogoBD.setAtendimentos(pedagogoBD.getAtendimentos()+1);
        Aluno aAtualizado = aRepo.save(alunoBD);
        Pedagogo pAtualizado = pRepo.save(pedagogoBD);
    }
}
