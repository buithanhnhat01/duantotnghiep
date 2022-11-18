package com.fptpoly.main.Dao;

import com.fptpoly.main.Entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    Account findAllByMatv(String matv);
    Account findAllByRole(String role);
}