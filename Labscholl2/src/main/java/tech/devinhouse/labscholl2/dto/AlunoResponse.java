package tech.devinhouse.labscholl2.dto;

import lombok.Data;
import tech.devinhouse.labscholl2.model.SituacaoMatricula;

import java.time.LocalDate;

@Data
public class AlunoResponse {

    private Integer codigo;

    private String nome;

    private String telefone;

    private LocalDate dataNascimento;

    private Long cpf;

    private String situacao;

    private Double nota;

    private Integer atendimentos;
}
