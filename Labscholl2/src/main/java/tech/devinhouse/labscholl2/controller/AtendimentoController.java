package tech.devinhouse.labscholl2.controller;


import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.devinhouse.labscholl2.dto.*;
import tech.devinhouse.labscholl2.model.Aluno;
import tech.devinhouse.labscholl2.model.Pedagogo;
import tech.devinhouse.labscholl2.service.AlunoService;
import tech.devinhouse.labscholl2.service.AtendimentoService;
import tech.devinhouse.labscholl2.service.PedagogoService;

import javax.validation.Valid;

@RestController
@RequestMapping("api/atendimentos")
@AllArgsConstructor
public class AtendimentoController {

    private AtendimentoService service;
    private AlunoService alunoService;
    private PedagogoService pedagogoService;

    private ModelMapper mapper;

    @PutMapping
    public ResponseEntity<AtendimentoResponse> atualizarAtendimentos(@RequestBody @Valid AtendimentoRequest aRequest){
        service.atendimentoPedagogico(aRequest.getCodigoAluno(), aRequest.getCodigoPedagogo());
        Aluno alunoAtualizado = alunoService.consultar(aRequest.getCodigoAluno());
        Pedagogo pedagogoAtualizado  = pedagogoService.consultar(aRequest.getCodigoPedagogo());
        AlunoResponse aR = mapper.map(alunoAtualizado, AlunoResponse.class);
        PedagogoResponse pR = mapper.map(pedagogoAtualizado, PedagogoResponse.class);
        AtendimentoResponse aResponse = new AtendimentoResponse(aR, pR);
        return ResponseEntity.ok(aResponse);
    }




}
