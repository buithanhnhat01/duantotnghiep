package com.fptpoly.main.Dao;

import com.fptpoly.main.Entity.Billaccessories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BillaccessoriesRepository extends JpaRepository<Billaccessories, String> {


    Billaccessories findAllByMahd(String mahd);


    void deleteAllByMahd(String id);
    List<Billaccessories> findAllByTrangthaiAndAccountByMatv_MatvOrderByNgaymuaDesc(String status,String matv);
    List<Billaccessories> findAllByTrangthaiOrderByNgaymuaDesc(String trangthai);
}