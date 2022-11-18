package com.fptpoly.main.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Votecar {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Stt")
    private int stt;

    @Basic
    @Column(name = "Ngay")
    private Date ngay;
    @Basic
    @Column(name = "noidung")
    private String noidung;
    @Basic
    @Column(name = "danhgia")
    private Integer danhgia;

    @ManyToOne
    @JoinColumn(name = "Matv", referencedColumnName = "Matv")
    private Account accountByMatv;
    @ManyToOne
    @JoinColumn(name = "Idcar", referencedColumnName = "Idcar")
    private Car carByMaxe;

}
