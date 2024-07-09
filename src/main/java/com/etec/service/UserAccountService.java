package com.etec.service;

import com.etec.entities.UserAccount;

public interface UserAccountService {

    Object listId(Integer id);
    Object update(Integer id, UserAccount user);
    Object delete(Integer id);

}
