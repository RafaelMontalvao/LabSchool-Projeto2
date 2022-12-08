package tech.devinhouse.labscholl2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AtendimentoResponse {


    private AlunoResponse aluno;

    private PedagogoResponse pedagogo;
}
