package tech.devinhouse.labscholl2.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tech.devinhouse.labscholl2.exception.RegistroExistenteException;
import tech.devinhouse.labscholl2.exception.RegistroNaoEncontradoException;
import tech.devinhouse.labscholl2.model.Aluno;
import tech.devinhouse.labscholl2.model.SituacaoMatricula;
import tech.devinhouse.labscholl2.repository.AlunoRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class AlunoService {

    private AlunoRepository repo;


    public Aluno criar(Aluno aluno) {
        if (repo.existsAlunoByCpf(aluno.getCpf()))
            throw new RegistroExistenteException("Aluno", aluno.getCpf());
        aluno.setAtendimentos(0);
        aluno = repo.save(aluno);
        return aluno;
    }

    public List<Aluno> consultar() {
        return repo.findAll();
    }

    public List<Aluno> consultar(String situacaoMatricula) {
        List<Aluno> alunos = repo.findAll();
        List<Aluno> filtroSituacao = new ArrayList<>();
        for(Aluno aluno: alunos){
            if (aluno.getSituacao().toString().equals(situacaoMatricula))
                filtroSituacao.add(aluno);
        }return filtroSituacao;
    }

    public Aluno consultar(Integer codigo) {
        Optional<Aluno> alunoOpt = repo.findById(codigo);
        if (alunoOpt.isEmpty())
            throw new RegistroNaoEncontradoException("Aluno", codigo);
        Aluno aluno = alunoOpt.get();
        return aluno;
    }


    public Aluno atualizarSituacao(Integer codigo, SituacaoMatricula situacaoMatricula) {
        Optional<Aluno> alunoOpt = repo.findById(codigo);
        if (alunoOpt.isEmpty())
            throw new RegistroNaoEncontradoException("Aluno", codigo);
        Aluno alunoBD = alunoOpt.get();
        alunoBD.setSituacao(situacaoMatricula);
        repo.save(alunoBD);
        return alunoBD;
    }


    public void excluir(Integer codigo) {
        boolean existe = repo.existsById(codigo);
        if (!existe)
            throw new RegistroNaoEncontradoException("Aluno", codigo);
        repo.deleteById(codigo);
    }
}





