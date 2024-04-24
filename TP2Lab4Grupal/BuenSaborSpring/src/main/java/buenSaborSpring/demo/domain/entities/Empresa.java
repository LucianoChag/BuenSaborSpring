package buenSaborSpring.demo.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
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
public class Empresa extends Base{
    private String nombre;
    private String razonSocial;
    private Integer cuit;

    @OneToMany
    @JoinColumn(name = "empresa_id")
    //El BUILDER.DEFAULT es para que BUILDER NO sobreescriba la inicializacion de la lista.
    @Builder.Default
    private Set<Sucursal> sucursales = new HashSet<>();
}
