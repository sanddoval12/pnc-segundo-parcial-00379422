package com.capas.parcial2_capas.DTO.Response;
import com.capas.parcial2_capas.Entities.Product;

//package com.uca.pncsegundoparcialveterinaria.DTO.Response;
//import com.uca.pncsegundoparcialveterinaria.Entities.Product;
//ya
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductResponse {

    private Long id;
    private String name;
    private String description;
    private Product.Category category;
    private BigDecimal price;
    private Integer stock;
    private Boolean available;
    private Boolean requiresPrescription;
    private LocalDate expirationDate;
    private String supplier;
}
