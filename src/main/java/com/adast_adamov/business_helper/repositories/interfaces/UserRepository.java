package com.adast_adamov.business_helper.repositories.interfaces;

import com.adast_adamov.business_helper.models.User;

public interface UserRepository {

    User getUserByLogin(String login);

}
