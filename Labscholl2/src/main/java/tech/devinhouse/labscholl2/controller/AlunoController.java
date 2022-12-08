package tech.devinhouse.labscholl2.controller;


import org.modelmapper.ModelMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tech.devinhouse.labscholl2.dto.AlunoRequest;
import tech.devinhouse.labscholl2.dto.AlunoResponse;
import tech.devinhouse.labscholl2.dto.SituacaoRequest;
import tech.devinhouse.labscholl2.model.Aluno;
import tech.devinhouse.labscholl2.model.SituacaoMatricula;
import tech.devinhouse.labscholl2.service.AlunoService;

import javax.validation.Valid;
import javax.validation.constraints.Pattern;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/alunos")
@AllArgsConstructor
public class AlunoController {

    private AlunoService service;

    private ModelMapper mapper;


    @GetMapping("{codigo}")
    public ResponseEntity<AlunoResponse> listarCodigo(@PathVariable("codigo") Integer codigo) {
        Aluno aluno = service.consultar(codigo);
        AlunoResponse resp = mapper.map(aluno, AlunoResponse.class);
        return ResponseEntity.ok(resp);
    }



    @GetMapping
    public ResponseEntity<List<AlunoResponse>> listar(@RequestParam(value = "situacao", required = false)String Situacao){
        List<Aluno> alunos;
        if(Situacao == null){
            alunos = service.consultar();
        }else{
                alunos= service.consultar(Situacao);
        }
        List<AlunoResponse> resp = new ArrayList<>();
        for(Aluno aluno: alunos) {
            AlunoResponse alunoResp = mapper.map(aluno, AlunoResponse.class);
            resp.add(alunoResp);
        }
        return ResponseEntity.ok(resp);
    }


    @PostMapping
    public ResponseEntity<AlunoResponse> criar(@RequestBody @Valid AlunoRequest request) {
        Aluno aluno = mapper.map(request, Aluno.class);
        aluno = service.criar(aluno);
        AlunoResponse resp = mapper.map(aluno, AlunoResponse.class);
        return ResponseEntity.created(URI.create(resp.getCodigo().toString())).body(resp);
    }



    @PutMapping("{codigo}")
    public ResponseEntity<AlunoResponse> atualizarSituacao(@PathVariable("codigo") Integer codigo, @RequestBody @Valid SituacaoRequest request) {
        SituacaoMatricula situacao = SituacaoMatricula.valueOf(request.getSituacaoMatricula()) ;
        Aluno aluno = service.atualizarSituacao(codigo,situacao);
        AlunoResponse resp = mapper.map(aluno,AlunoResponse.class);
        return ResponseEntity.ok(resp);
    }

    @DeleteMapping("{codigo}")
    public ResponseEntity exclusaoAluno(@PathVariable("codigo") Integer codigo) {
        service.excluir(codigo);
        return ResponseEntity.noContent().build();
    }











}
