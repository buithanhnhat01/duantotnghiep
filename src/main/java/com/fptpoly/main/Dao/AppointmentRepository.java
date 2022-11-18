package com.fptpoly.main.Dao;

import com.fptpoly.main.Entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    @Query("FROM Appointment a WHERE a.accountByMatv.matv=?1 ORDER BY a.ngayhen DESC")
    List<Appointment> fillappointment(String matv);

    @Query("SELECT a.khunggio FROM Appointment a where a.ngayhen=?1 and a.carByIdcar.idcar=?2")
    List<String> findNgayhenAndCarByIdcar(Date ngayhen, String idcar);
}