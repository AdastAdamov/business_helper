package com.adast_adamov.business_helper.repositories.interfaces;

import com.adast_adamov.business_helper.exceptions.UserInputValidatationException;
import com.adast_adamov.business_helper.models.Product;
import com.adast_adamov.business_helper.models.UserProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "SELECT * from products WHERE bank_name = ?1", nativeQuery = true)
    List<Product> getProducts(String bankName);

    @Query(value = "SELECT DISTINCT bank_name FROM products", nativeQuery = true)
    List<String> getBankNames();
}
