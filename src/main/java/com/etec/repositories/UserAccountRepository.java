package com.etec.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.etec.entities.UserAccount;


public interface UserAccountRepository extends JpaRepository<UserAccount, Integer> {

}
