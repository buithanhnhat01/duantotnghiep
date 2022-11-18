package com.fptpoly.main.Dao;

import com.fptpoly.main.Entity.Brand;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public interface BrandRepository extends JpaRepository<Brand, String> {

}