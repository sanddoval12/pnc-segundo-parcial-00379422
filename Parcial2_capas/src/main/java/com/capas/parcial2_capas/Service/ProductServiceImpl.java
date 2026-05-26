package com.capas.parcial2_capas.Service;


//package com.uca.pncsegundoparcialveterinaria.service;
import com.capas.parcial2_capas.DTO.Request.ProductRequest;
import com.capas.parcial2_capas.DTO.Request.StockUpdateRequest;
import com.capas.parcial2_capas.DTO.Response.ProductResponse;
import com.capas.parcial2_capas.Entities.Product;
import com.capas.parcial2_capas.Exception.BusinessRuleException;
import com.capas.parcial2_capas.Exception.ResourceNotFoundException;
import com.capas.parcial2_capas.Mapper.ProductMapper;
import com.capas.parcial2_capas.Repository.ProductRepository;

//import com.uca.pncsegundoparcialveterinaria.dto.request.ProductRequest;
//import com.uca.pncsegundoparcialveterinaria.dto.request.StockUpdateRequest;
//import com.uca.pncsegundoparcialveterinaria.dto.response.ProductResponse;
//import com.uca.pncsegundoparcialveterinaria.entities.Product;
//import com.uca.pncsegundoparcialveterinaria.exception.BusinessRuleException;
//import com.uca.pncsegundoparcialveterinaria.exception.ResourceNotFoundException;
//import com.uca.pncsegundoparcialveterinaria.mapper.ProductMapper;
//import com.uca.pncsegundoparcialveterinaria.repository.ProductRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;


    @Override
    public ProductResponse create(ProductRequest request) {

        if (productRepository.existsByNameIgnoreCase(request.getName())) {
            throw new BusinessRuleException("Ya existe un producto con el nombre: " + request.getName());
        }

        if (!request.getExpirationDate().isAfter(LocalDate.now())) {
            throw new BusinessRuleException("La fecha de vencimiento debe ser posterior a la fecha actual");
        }

        Product product = productMapper.toEntity(request);

        product.setRequiresPrescription(
                product.getCategory() == Product.Category.MEDICINE ||
                        product.getCategory() == Product.Category.VACCINE
        );

        if (product.getStock() == 0) {
            product.setAvailable(false);
        }

        return productMapper.toResponse(productRepository.save(product));
    }


    @Override
    public ProductResponse getById(Long id) {
        return productMapper.toResponse(findById(id));
    }

    @Override
    public List<ProductResponse> getAll(Product.Category category, Boolean available) {
        List<Product> products;

        if (category != null && available != null) {
            products = productRepository.findByCategoryAndAvailable(category, available);
        } else if (category != null) {
            products = productRepository.findByCategory(category);
        } else if (available != null) {
            products = productRepository.findByAvailable(available);
        } else {
            products = productRepository.findAll();
        }

        return products.stream().map(productMapper::toResponse).toList();
    }


    @Override
    public ProductResponse update(Long id, ProductRequest request) {
        Product product = findById(id);

        if (productRepository.existsByNameIgnoreCaseAndIdNot(request.getName(), id)) {
            throw new BusinessRuleException("Ya existe otro producto con el nombre: " + request.getName());
        }

        if (!request.getExpirationDate().isAfter(LocalDate.now())) {
            throw new BusinessRuleException("La fecha de vencimiento debe ser posterior a la fecha actual");
        }

        productMapper.updateEntity(product, request);

        product.setRequiresPrescription(
                product.getCategory() == Product.Category.MEDICINE ||
                        product.getCategory() == Product.Category.VACCINE
        );

        if (product.getStock() == 0) {
            product.setAvailable(false);
        }

        return productMapper.toResponse(productRepository.save(product));
    }


    @Override
    public ProductResponse updateStock(Long id, StockUpdateRequest request) {
        Product product = findById(id);

        int newStock = product.getStock() + request.getAmount();

        if (newStock < 0) {
            throw new BusinessRuleException("El stock no puede ser menor a 0. Stock actual: " + product.getStock());
        }

        product.setStock(newStock);

        if (newStock == 0) {
            product.setAvailable(false);
        }

        return productMapper.toResponse(productRepository.save(product));
    }


    @Override
    public void delete(Long id) {
        Product product = findById(id);

        if (product.getCategory() == Product.Category.VACCINE && product.getAvailable()) {
            throw new BusinessRuleException("No se puede eliminar una vacuna que está disponible");
        }

        productRepository.delete(product);
    }
    
    private Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con id: " + id));
    }
}