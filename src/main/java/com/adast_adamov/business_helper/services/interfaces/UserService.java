package com.adast_adamov.business_helper.services.interfaces;

import com.adast_adamov.business_helper.exceptions.InvalidPasswordException;
import com.adast_adamov.business_helper.exceptions.UserNotExistsException;
import com.adast_adamov.business_helper.models.User;
import com.adast_adamov.business_helper.models.requests.AuthorizationRequest;

public interface UserService {

    User authorizeUser(AuthorizationRequest authorizationRequest) throws UserNotExistsException, InvalidPasswordException;
}
