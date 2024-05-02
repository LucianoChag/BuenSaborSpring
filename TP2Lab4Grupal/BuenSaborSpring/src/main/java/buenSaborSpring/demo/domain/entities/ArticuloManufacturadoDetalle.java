package buenSaborSpring.demo.domain.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
@Entity
public class ArticuloManufacturadoDetalle extends Base{


    private Integer cantidad;

    @ManyToOne
    @JoinColumn(name = "articuloManufacturado_id")
    private ArticuloManufacturado articuloManufacturado;

    @ManyToOne
    private ArticuloInsumo articuloInsumo;

}
