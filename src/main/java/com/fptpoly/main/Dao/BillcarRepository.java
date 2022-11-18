package com.fptpoly.main.Dao;

import com.fptpoly.main.Entity.Billcar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BillcarRepository extends JpaRepository<Billcar, String> {
}