package com.adast_adamov.business_helper.repositories.implementations;

import com.adast_adamov.business_helper.exceptions.ExpiredTokenException;
import com.adast_adamov.business_helper.models.User;
import com.adast_adamov.business_helper.repositories.interfaces.TokenRepository;
import com.adast_adamov.business_helper.repositories.interfaces.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

@Repository
public class StaticTokenRepository implements TokenRepository {

    Map<String, User> tokenToUserMap = new HashMap<>();

    @Override
    public String getToken(User user) {

        List<String> tokensToDelete = tokenToUserMap.keySet().stream()
                .filter(key -> tokenToUserMap.get(key).getId().equals(user.getId()))
                .collect(Collectors.toList());

        tokensToDelete.forEach(tokenToUserMap::remove);

        String token = RandomStringUtils.randomAlphabetic(20);

        tokenToUserMap.put(token, user);

        return token;
    }

    @Override
    public User getUser(String token) throws ExpiredTokenException {
        if (tokenToUserMap.containsKey(token)) {
            return tokenToUserMap.get(token);
        }
        throw new ExpiredTokenException();
    }
}
