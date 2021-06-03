package com.adast_adamov.business_helper.repositories.interfaces;

import com.adast_adamov.business_helper.models.UserProduct;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserProductRepository extends JpaRepository<UserProduct, Long> {
}
