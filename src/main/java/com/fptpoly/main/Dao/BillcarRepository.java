package com.fptpoly.main.Dao;

import com.fptpoly.main.Entity.Billcar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillcarRepository extends JpaRepository<Billcar, String> {

    List<Billcar> findAllByTrangthaiAndAccountByMatv_Matv(String trangthai,String matv);
}