package com.fptpoly.main.Dao;

import com.fptpoly.main.Entity.Accessories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccessoriesRepository extends JpaRepository<Accessories, String> {
    Accessories findAllByMalk(String malk);
}