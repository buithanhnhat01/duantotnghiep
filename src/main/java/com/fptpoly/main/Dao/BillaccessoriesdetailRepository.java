package com.fptpoly.main.Dao;

import com.fptpoly.main.Entity.Billaccessoriesdetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BillaccessoriesdetailRepository extends JpaRepository<Billaccessoriesdetail, Integer> {


    void deleteAllByBillaccessoriesByMahd_Mahd(String id);

    @Modifying
    @Query("DELETE from Billaccessoriesdetail b where b.billaccessoriesByMahd.mahd=?1")
    void deleteMadh(String x);

    void deleteBillaccessoriesdetailByBillaccessoriesByMahd_Mahd(String id);

    List<Billaccessoriesdetail> findAllByBillaccessoriesByMahd_Mahd(String id);
}