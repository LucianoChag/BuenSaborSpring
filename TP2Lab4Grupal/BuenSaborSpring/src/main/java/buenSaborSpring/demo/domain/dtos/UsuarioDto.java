package buenSaborSpring.demo.domain.dtos;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UsuarioDto extends BaseDto{
    private String auth0Id;
    private String username;
}