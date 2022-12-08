package tech.devinhouse.labscholl2.model;



import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer codigo;

    private String nome;

    private String telefone;

    @DateTimeFormat(pattern="yyyy/MM/dd")
    private LocalDate dataNascimento;

    private Long cpf;


}