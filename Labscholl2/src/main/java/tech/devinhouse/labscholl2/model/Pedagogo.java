package tech.devinhouse.labscholl2.model;


import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Table(name = "PEDAGOGOS")
@Entity
public class Pedagogo extends Pessoa {

    private int atendimentos = 0;


}
