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
public class Billcar {

    @Id
    @Column(name = "Mahd")
    private String mahd;
    @Basic
    @Column(name = "Tiencoc")
    private Double tiencoc;
    @Basic
    @Column(name = "Ngayban")
    private Date ngayban;
    @Basic
    @Column(name = "Khuyenmai")
    private Integer khuyenmai;
    @Basic
    @Column(name = "Tongtien")
    private Double tongtien;
    @Basic
    @Column(name = "Trangthai")
    private String trangthai;
    @ManyToOne
    @JoinColumn(name = "Idcar", referencedColumnName = "Idcar")
    private Car car;
    @ManyToOne
    @JoinColumn(name = "Manv", referencedColumnName = "Matv")
    private Account accountByManv;
    @ManyToOne
    @JoinColumn(name = "Matv", referencedColumnName = "Matv")
    private Account accountByMatv;

    
}
