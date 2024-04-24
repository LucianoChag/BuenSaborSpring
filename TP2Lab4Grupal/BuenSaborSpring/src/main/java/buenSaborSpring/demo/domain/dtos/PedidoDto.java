package buenSaborSpring.demo.domain.dtos;


import buenSaborSpring.demo.domain.entities.enums.Estado;
import buenSaborSpring.demo.domain.entities.enums.FormaPago;
import buenSaborSpring.demo.domain.entities.enums.TipoEnvio;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PedidoDto extends BaseDto{
    private LocalTime horaEstimadaFinalizacion;
    private Double total;
    private Double totalCosto;
    private Estado estado;
    private TipoEnvio tipoEnvio;
    private FormaPago formaPago;
    private LocalDate fechaPedido;
    private DomicilioDto domicilio;
    private SucursalDto sucursal;
    private FacturaDto factura;
    private Set<DetallePedidoDto> detallePedidos = new HashSet<>();
    private Long domicilioId; //Usado en el create
    private Long sucursalId; //Usado en el create
}
