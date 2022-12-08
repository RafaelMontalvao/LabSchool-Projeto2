package tech.devinhouse.labscholl2.dto;

import lombok.Data;
import tech.devinhouse.labscholl2.model.SituacaoMatricula;
import tech.devinhouse.labscholl2.validator.ValorDeEnum;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Data
public class SituacaoRequest {

    @NotEmpty(message = "{campo.obrigatorio}")
    @ValorDeEnum(enumClass = SituacaoMatricula.class, message = "{campo.invalido}")
    private  String  situacaoMatricula;


}
