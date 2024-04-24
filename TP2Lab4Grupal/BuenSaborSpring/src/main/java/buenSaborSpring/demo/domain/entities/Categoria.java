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
public class Categoria extends Base{

    private String denominacion;


    @OneToMany
    @JoinColumn(name = "categoria_id")
    @Builder.Default
    private Set<Articulo> articulos = new HashSet<>();


    @OneToMany
    @JoinColumn(name = "categoria_id")
    @Builder.Default
    //Relacion One to Many de si misma
    private Set<Categoria> subCategorias = new HashSet<>();

}