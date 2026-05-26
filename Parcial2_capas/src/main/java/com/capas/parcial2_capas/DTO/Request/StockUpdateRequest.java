package com.capas.parcial2_capas.DTO.Request;
//package com.uca.pncsegundoparcialveterinaria.DTO.Request;
//ya
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockUpdateRequest {

    @NotNull(message = "El valor amount es obligatorio")
    private Integer amount;
}