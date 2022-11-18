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
public class Voteaccessories {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Stt")
    private int stt;

    @Basic
    @Column(name = "Ngay")
    private Date ngay;
    @Basic
    @Column(name = "Noidung")
    private String noidung;
    @Basic
    @Column(name = "Danhgia")
    private Integer danhgia;

    @ManyToOne
    @JoinColumn(name = "Matv", referencedColumnName = "Matv")
    private Account accountByMatv;
    @ManyToOne
    @JoinColumn(name = "Malk", referencedColumnName = "Malk")
    private Accessories accessoriesByMalk;

    
}
