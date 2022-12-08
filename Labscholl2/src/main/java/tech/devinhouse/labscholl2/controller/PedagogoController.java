package tech.devinhouse.labscholl2.controller;


import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tech.devinhouse.labscholl2.dto.PedagogoResponse;
import tech.devinhouse.labscholl2.model.Pedagogo;
import tech.devinhouse.labscholl2.service.PedagogoService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/pedagogos")
@AllArgsConstructor
public class PedagogoController {

    private ModelMapper mapper;
    private PedagogoService service;



    @GetMapping
    public ResponseEntity<List<PedagogoResponse>> listar() {
        List<Pedagogo> pedagogos = service.consultar();
        List<PedagogoResponse> resp = new ArrayList<>();
        for(Pedagogo pedagogo: pedagogos) {
            PedagogoResponse pedagogoResponse = mapper.map(pedagogo, PedagogoResponse.class);
            resp.add(pedagogoResponse);
        }
        return ResponseEntity.ok(resp);
    }




}
