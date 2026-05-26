package com.capas.parcial2_capas.DTO.Request;
//package com.uca.pncsegundoparcialveterinaria.DTO.Request;

import com.capas.parcial2_capas.Entities.Product;
//package com.uca.pncsegundoparcialveterinaria.Entities.Product;

//ya
import jakarta.validation.constraints.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductRequest {

    @NotBlank(message = "El nombre es  de carácter obligatorio")
    private String name;

    private String description;

    @NotNull(message = "La categoría es obligatoria")
    private Product.Category category;

    @NotNull(message = "El precio es obligatorio")
    @DecimalMin(value = "0.01", message = "El precio debe ser mayor a cero")
    private BigDecimal price;

    @NotNull(message = "El stock es obligatorio")
    @Min(value = 0, message = "El stock no puede ser negativo")
    private Integer stock;

    @NotNull(message = "La disponibilidad es obligatoria")
    private Boolean available;

    @NotNull(message = "La fecha de vencimiento es obligatoria")
    @Future(message = "La fecha de vencimiento debe ser una fecha futura")
    private LocalDate expirationDate;

    @NotBlank(message = "El proveedor es obligatorio")
    private String supplier;
}