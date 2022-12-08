package tech.devinhouse.labscholl2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AtendimentoRequest {

    @NotNull(message = "{campo.obrigatorio}")
    private Integer codigoAluno;

    @NotNull(message = "{campo.obrigatorio}")
    private Integer codigoPedagogo;


}
