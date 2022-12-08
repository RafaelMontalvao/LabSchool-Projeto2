package tech.devinhouse.labscholl2.model;


import lombok.Data;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;


@Data
@Table(name = "ALUNOS")
@Entity
public class Aluno extends Pessoa{


    @Enumerated(EnumType.STRING)
    private SituacaoMatricula situacao;

    private Double nota;

    private int atendimentos = 0  ;


}
