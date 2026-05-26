package com.capas.parcial2_capas.Controller;

//package com.uca.pncsegundoparcialveterinaria.controller;
import com.capas.parcial2_capas.DTO.Request.ProductRequest;
import com.capas.parcial2_capas.DTO.Request.StockUpdateRequest;

import com.capas.parcial2_capas.DTO.Response.ProductResponse;
import com.capas.parcial2_capas.Entities.Product;
import com.capas.parcial2_capas.Service.ProductServiceImpl;
import com.capas.parcial2_capas.Service.ProductService;
import com.capas.parcial2_capas.Utils.ApiResponse;

//import com.uca.pncsegundoparcialveterinaria.dto.request.ProductRequest;
//import com.uca.pncsegundoparcialveterinaria.dto.request.StockUpdateRequest;
//import com.uca.pncsegundoparcialveterinaria.dto.response.ProductResponse;
//import com.uca.pncsegundoparcialveterinaria.entities.Product;

//import com.uca.pncsegundoparcialveterinaria.service.ProductService;
//import com.uca.pncsegundoparcialveterinaria.utils.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<ApiResponse<ProductResponse>> create(
            @Valid @RequestBody ProductRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponse.success("Producto creado exitosamente", productService.create(request)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(ApiResponse.success("Producto encontrado", productService.getById(id)));
    }

    @GetMapping
    public ResponseEntity<ApiResponse<List<ProductResponse>>> getAll(
            @RequestParam(required = false) Product.Category category,
            @RequestParam(required = false) Boolean available) {
        return ResponseEntity.ok(ApiResponse.success("Productos obtenidos exitosamente",
                productService.getAll(category, available)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<ProductResponse>> update(
            @PathVariable Long id,
            @Valid @RequestBody ProductRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Producto actualizado exitosamente",
                productService.update(id, request)));
    }

    @PatchMapping("/{id}/stock")
    public ResponseEntity<ApiResponse<ProductResponse>> updateStock(
            @PathVariable Long id,
            @Valid @RequestBody StockUpdateRequest request) {
        return ResponseEntity.ok(ApiResponse.success("Stock actualizado exitosamente",
                productService.updateStock(id, request)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<Void>> delete(@PathVariable Long id) {
        productService.delete(id);
        return ResponseEntity.ok(ApiResponse.success("Producto eliminado exitosamente"));
    }
}