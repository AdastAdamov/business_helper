package com.adast_adamov.business_helper.services.implementations;

import com.adast_adamov.business_helper.models.UserProduct;
import com.adast_adamov.business_helper.models.requests.AddUserCardRequest;
import com.adast_adamov.business_helper.repositories.interfaces.UserProductRepository;
import com.adast_adamov.business_helper.services.interfaces.UserProductService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class UserProductServiceImpl implements UserProductService {

    private final UserProductRepository userProductRepository;

    public UserProductServiceImpl(UserProductRepository userProductRepository) {
        this.userProductRepository = userProductRepository;
    }


    @Override
    public void addUserCard(UserProduct userProduct) {

        userProductRepository.saveAndFlush(userProduct);
    }
}
