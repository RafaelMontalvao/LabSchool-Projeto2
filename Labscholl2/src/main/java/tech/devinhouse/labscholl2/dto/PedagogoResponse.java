package tech.devinhouse.labscholl2.dto;


import lombok.Data;

import java.time.LocalDate;
@Data
public class PedagogoResponse {

    private Integer codigo;

    private String nome;

    private String telefone;

    private LocalDate dataNascimento;

    private Long cpf;

    private Integer atendimentos;

}
