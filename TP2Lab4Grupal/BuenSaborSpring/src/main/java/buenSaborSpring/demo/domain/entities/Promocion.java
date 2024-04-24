package buenSaborSpring.demo.domain.entities;

import buenSaborSpring.demo.domain.entities.enums.TipoPromocion;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString
@Builder
public class Promocion extends Base {

    private String denominacion;
    private LocalDate fechaDesde;
    private LocalDate fechaHasta;
    private LocalTime horaDesde;
    private LocalTime horaHasta;
    private String descripcionDescuento;
    private Double precioPromocional;
    private TipoPromocion tipoPromocion;


    @ManyToMany
    //JoinTable sirve para crear la tabla intermedia en las relaciones Many to Many
    @JoinTable(name = "promocion_articulo",
            joinColumns = @JoinColumn(name = "promocion_id"),
            inverseJoinColumns = @JoinColumn(name = "articulo_id"))
    @Builder.Default
    private Set<Articulo> articulos = new HashSet<>();

    @OneToMany
    @JoinColumn(name = "promocion_id")
    @Builder.Default
    private Set<Imagen> imagenes = new HashSet<>();

}
