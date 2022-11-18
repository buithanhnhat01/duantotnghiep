package com.fptpoly.main.Dao;

import com.fptpoly.main.Entity.Typecar;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypecarRepository extends JpaRepository<Typecar, String> {

}