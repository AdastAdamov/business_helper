package com.adast_adamov.business_helper.services.implementations;

import com.adast_adamov.business_helper.exceptions.InvalidPasswordException;
import com.adast_adamov.business_helper.exceptions.UserNotExistsException;
import com.adast_adamov.business_helper.models.User;
import com.adast_adamov.business_helper.models.requests.AuthorizationRequest;
import com.adast_adamov.business_helper.repositories.interfaces.UserRepository;
import com.adast_adamov.business_helper.services.interfaces.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User authorizeUser(AuthorizationRequest authorizationRequest) throws UserNotExistsException, InvalidPasswordException {
        User user = userRepository.getUserByLogin(authorizationRequest.getLogin());

        if (user == null) {
            throw new UserNotExistsException();
        }

        if (! user.getPassword().equals(authorizationRequest.getPassword())) {
            throw new InvalidPasswordException();
        }

        return user;
    }
}
