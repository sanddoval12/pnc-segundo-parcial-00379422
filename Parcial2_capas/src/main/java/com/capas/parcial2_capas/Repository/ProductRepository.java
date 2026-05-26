package com.capas.parcial2_capas.Repository;

//package com.uca.pncsegundoparcialveterinaria.repository;
import com.capas.parcial2_capas.Entities.Product;

//import com.uca.pncsegundoparcialveterinaria.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByNameIgnoreCase(String name);
    boolean existsByNameIgnoreCaseAndIdNot(String name, Long id);
    List<Product> findByCategory(Product.Category category);
    List<Product> findByAvailable(Boolean available);
    List<Product> findByCategoryAndAvailable(Product.Category category, Boolean available);
}