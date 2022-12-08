package tech.devinhouse.labscholl2.controller;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.devinhouse.labscholl2.dto.AlunoResponse;
import tech.devinhouse.labscholl2.dto.ProfessorResponse;
import tech.devinhouse.labscholl2.model.Aluno;
import tech.devinhouse.labscholl2.model.Professor;
import tech.devinhouse.labscholl2.service.ProfessorService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/professores")
@AllArgsConstructor
public class ProfessorController {

    private ModelMapper mapper;
    private ProfessorService service;



    @GetMapping
    public ResponseEntity<List<ProfessorResponse>> listar() {
        List<Professor> professores = service.consultar();
        List<ProfessorResponse> resp = new ArrayList<>();
        for(Professor professor: professores) {
            ProfessorResponse professorResp = mapper.map(professor, ProfessorResponse.class);
            resp.add(professorResp);
        }
        return ResponseEntity.ok(resp);
}
}
