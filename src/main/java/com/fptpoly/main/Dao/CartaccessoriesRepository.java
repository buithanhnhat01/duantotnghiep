package com.fptpoly.main.Dao;

import com.fptpoly.main.Entity.Cartaccessories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartaccessoriesRepository extends JpaRepository<Cartaccessories,Integer> {
    List<Cartaccessories> findAllByAccount_Matv(String matv);
    Cartaccessories findAllByStt(int stt);
}