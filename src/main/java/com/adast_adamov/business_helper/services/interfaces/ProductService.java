package com.adast_adamov.business_helper.services.interfaces;

import com.adast_adamov.business_helper.exceptions.UserInputValidatationException;
import com.adast_adamov.business_helper.models.Product;

import java.util.List;

public interface ProductService {

    List<String> getBanks();

    List<Product> getProducts(String bankName) throws UserInputValidatationException;
}
