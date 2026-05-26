package com.capas.parcial2_capas.Service;

//package com.uca.pncsegundoparcialveterinaria.service;
import com.capas.parcial2_capas.DTO.Request.ProductRequest;
import com.capas.parcial2_capas.DTO.Request.StockUpdateRequest;
import com.capas.parcial2_capas.DTO.Response.ProductResponse;
import com.capas.parcial2_capas.Entities.Product;

//import com.uca.pncsegundoparcialveterinaria.dto.request.ProductRequest;
//import com.uca.pncsegundoparcialveterinaria.dto.request.StockUpdateRequest;
//import com.uca.pncsegundoparcialveterinaria.dto.response.ProductResponse;
//import com.uca.pncsegundoparcialveterinaria.entities.Product;
import java.util.List;

public interface ProductService {
    ProductResponse create(ProductRequest request);
    ProductResponse getById(Long id);
    List<ProductResponse> getAll(Product.Category category, Boolean available);
    ProductResponse update(Long id, ProductRequest request);
    ProductResponse updateStock(Long id, StockUpdateRequest request);
    void delete(Long id);
}