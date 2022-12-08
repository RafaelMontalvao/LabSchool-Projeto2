package tech.devinhouse.labscholl2.dto;


import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import tech.devinhouse.labscholl2.model.SituacaoMatricula;
import tech.devinhouse.labscholl2.validator.ValorDeEnum;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Data
public class AlunoRequest {


    @NotEmpty(message = "{campo.obrigatorio}")
    private String nome;

    @NotEmpty(message = "{campo.obrigatorio}")
    private String telefone;

    @NotNull(message = "{campo.obrigatorio")
    @DateTimeFormat(pattern="yyyy/MM/dd")
    private LocalDate dataNascimento;


    @NotNull(message = "{campo.obrigatorio}")
    private Long cpf;

    @NotNull(message = "{campo.obrigatorio}")
    @ValorDeEnum(enumClass = SituacaoMatricula.class, message = "{campo.invalido}")
    private String situacao;

    @NotNull(message = "{campo.obrigatorio}")
    @Min(value = 0)
    @Max(value = 10)
    private Double nota;


}
