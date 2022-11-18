package com.fptpoly.main.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Feedback {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "STT")
    private int stt;
    @Basic
    @Column(name = "Email")
    private String email;
    @Basic
    @Column(name = "Hoten")
    private String hoten;
    @Basic
    @Column(name = "Noidung")
    private String noidung;
    @Basic
    @Column(name = "Ngay")
    private Date ngay;

    
}
