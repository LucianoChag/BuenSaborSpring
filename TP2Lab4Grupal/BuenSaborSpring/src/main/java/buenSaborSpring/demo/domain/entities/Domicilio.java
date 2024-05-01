package buenSaborSpring.demo.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Builder
public class Domicilio extends Base{

    private String calle;
    private Integer numero;
    private Integer codigoPostal;
    private Integer piso;
    private Integer nDpto;

    @ManyToOne
    private Localidad localidad;


}