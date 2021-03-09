package com.accountservice.repository;

import com.accountservice.model.AccountModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountProjectionRepository extends JpaRepository<AccountModel, String> {
}
