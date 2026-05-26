package com.capas.parcial2_capas.Entities;
//package com.uca.pncsegundoparcialveterinaria.Entities;
//ya
import jakarta.persistence.*;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "products")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @Column(nullable = false)
    private BigDecimal price;

    @Column(nullable = false)
    private Integer stock;

    @Column(nullable = false)
    private Boolean available;

    @Column(nullable = false)
    private Boolean requiresPrescription;

    @Column(nullable = false)
    private LocalDate expirationDate;

    @Column(nullable = false)
    private String supplier;

    public enum Category {
        MEDICINE,
        VACCINE,
        SUPPLEMENT,
        SURGICAL_SUPPLY,
        FOOD
    }
}