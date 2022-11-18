package com.fptpoly.main.Dao;

import com.fptpoly.main.Entity.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, String> {
    Car findCarsByIdcar(String id);
    List<Car> findAllByBrandByMa(String hang);
    List<Car> findAllByTypecarByLoaixe(String loai);
    List<Car> findAllByNamsx(int nam);

    /*Page<Car> findTencar(String idcar, Pageable pageable);*/

    @Query(value = "FROM Car c WHERE c.tencar like ?1")
    Page searchAllByTencarLike(String ten,Pageable pageable);

    /*List<Car> findAllByOrderByNamsxDesc;*/
    /*Page<Car> findAlls(Pageable pageable);*/

}