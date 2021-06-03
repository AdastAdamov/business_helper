package com.adast_adamov.business_helper.services.implementations;

import com.adast_adamov.business_helper.exceptions.ExpiredTokenException;
import com.adast_adamov.business_helper.models.User;
import com.adast_adamov.business_helper.repositories.interfaces.TokenRepository;
import com.adast_adamov.business_helper.services.interfaces.TokenService;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

    private final TokenRepository tokenRepository;

    public TokenServiceImpl(TokenRepository tokenRepository) {
        this.tokenRepository = tokenRepository;
    }

    @Override
    public String getToken(User user) {
        return tokenRepository.getToken(user);
    }

    @Override
    public User getUser(String token) throws ExpiredTokenException {
        return tokenRepository.getUser(token);
    }
}
