package tech.devinhouse.labscholl2.model;



import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Data
@Table(name = "PROFESSORES")
@Entity
public class Professor extends Pessoa {

    @Enumerated(EnumType.STRING)
    private Estado estado;


    @Enumerated(EnumType.STRING)
    private ExperienciaDesenvolvimento experienciaDesenvolvimento;

    @Enumerated(EnumType.STRING)
    private FormacaoAcademica formacaoAcademica;







}
