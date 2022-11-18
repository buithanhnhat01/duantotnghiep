package com.fptpoly.main.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Billaccessoriesdetail {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Stt")
    private int stt;

    @Basic
    @Column(name = "Soluong")
    private Integer soluong;
    @Basic
    @Column(name = "Gia")
    private Double gia;
    @Basic
    @Column(name = "Ghichu")
    private String ghichu;
    @ManyToOne
    @JoinColumn(name = "Mahd", referencedColumnName = "Mahd")
    private Billaccessories billaccessoriesByMahd;
    @ManyToOne
    @JoinColumn(name = "Malk", referencedColumnName = "Malk")
    private Accessories accessoriesByMalk;

    
}
