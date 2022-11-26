package com.fptpoly.main.Dao;

import com.fptpoly.main.Entity.Votecar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VotecarRepository extends JpaRepository<Votecar, Integer> {

    List<Votecar> findAllByCarByMaxe_IdcarOrderByNgayDesc(String idcar);
}