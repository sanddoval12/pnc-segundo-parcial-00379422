package com.capas.parcial2_capas.Mapper;
import com.capas.parcial2_capas.DTO.Request.ProductRequest;
import com.capas.parcial2_capas.DTO.Response.ProductResponse;
import com.capas.parcial2_capas.Entities.Product;



//package com.uca.pncsegundoparcialveterinaria.mapper;
//import com.uca.pncsegundoparcialveterinaria.dto.request.ProductRequest;
//import com.uca.pncsegundoparcialveterinaria.dto.response.ProductResponse;
//import com.uca.pncsegundoparcialveterinaria.entities.Product;
import org.springframework.stereotype.Component;

@Component
public class ProductMapper {

    public Product toEntity(ProductRequest request) {
        return Product.builder()
                .name(request.getName().trim().toLowerCase())
                .description(request.getDescription())
                .category(request.getCategory())
                .price(request.getPrice())
                .stock(request.getStock())
                .available(request.getAvailable())
                .expirationDate(request.getExpirationDate())
                .supplier(request.getSupplier().trim())
                .build();
    }

    public ProductResponse toResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .category(product.getCategory())
                .price(product.getPrice())
                .stock(product.getStock())
                .available(product.getAvailable())
                .requiresPrescription(product.getRequiresPrescription())
                .expirationDate(product.getExpirationDate())
                .supplier(product.getSupplier())
                .build();
    }

    public void updateEntity(Product product, ProductRequest request) {
        product.setName(request.getName().trim().toLowerCase());
        product.setDescription(request.getDescription());
        product.setCategory(request.getCategory());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setAvailable(request.getAvailable());
        product.setExpirationDate(request.getExpirationDate());
        product.setSupplier(request.getSupplier().trim());
    }
}