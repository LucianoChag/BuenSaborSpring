package buenSaborSpring.demo.domain.entities;

import buenSaborSpring.demo.domain.entities.enums.Estado;
import buenSaborSpring.demo.domain.entities.enums.FormaPago;
import buenSaborSpring.demo.domain.entities.enums.TipoEnvio;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Builder
public class Pedido extends Base{

    private LocalTime horaEstimadaFinalizacion;
    private Double total;
    private Double totalCosto;
    private Estado estado;
    private TipoEnvio tipoEnvio;
    private FormaPago formaPago;
    private LocalDate fechaPedido;

    @ManyToOne
    private Domicilio domicilio;

    @ManyToOne
    private Sucursal sucursal;

    @OneToOne
    private Factura factura;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @OneToMany(cascade = CascadeType.ALL,
                fetch = FetchType.LAZY,
                mappedBy = "pedido")
    @Builder.Default
    private Set<DetallePedido> detallePedidos = new HashSet<>();
}
