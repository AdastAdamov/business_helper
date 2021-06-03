package com.adast_adamov.business_helper.services.implementations;

import com.adast_adamov.business_helper.exceptions.UserInputValidatationException;
import com.adast_adamov.business_helper.models.Product;
import com.adast_adamov.business_helper.repositories.interfaces.ProductRepository;
import com.adast_adamov.business_helper.services.interfaces.ProductService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<String> getBanks() {
        return productRepository.getBankNames();
    }

    @Override
    public List<Product> getProducts(String bankName) {
        return productRepository.getProducts(bankName);
    }
}
