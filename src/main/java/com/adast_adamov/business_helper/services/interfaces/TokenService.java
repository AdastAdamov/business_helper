package com.adast_adamov.business_helper.services.interfaces;

import com.adast_adamov.business_helper.exceptions.ExpiredTokenException;
import com.adast_adamov.business_helper.models.User;

public interface TokenService {

    String getToken(User user);
    User getUser(String token) throws ExpiredTokenException;

}
