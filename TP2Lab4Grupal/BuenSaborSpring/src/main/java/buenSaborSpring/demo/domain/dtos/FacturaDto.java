package buenSaborSpring.demo.domain.dtos;


import buenSaborSpring.demo.domain.entities.enums.FormaPago;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FacturaDto extends BaseDto{

    private LocalDate fechaFacturacion;
    private Integer mpPaymentId;
    private Integer mpMerchantOrderId;
    private String mpPreferenceId;
    private String mpPaymentType;
    private FormaPago formaPago;
    private Double totalVenta;

}
