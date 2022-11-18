package com.fptpoly.main.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Billaccessories {


    @Id
    @Column(name = "Mahd")
    private String mahd;

    @Basic
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "Ngaymua")
    private Date ngaymua = new Date();
    @Basic
    @Column(name = "Ngaynhan")
    private Date ngaynhan;
    @Basic
    @Column(name = "Tongtien")
    private Double tongtien;
    @Basic
    @Column(name = "Trangthai")
    private String trangthai;
    @Basic
    @Column(name = "Ghichu")
    private String ghichu;
    @ManyToOne
    @JoinColumn(name = "Matv", referencedColumnName = "Matv")
    private Account accountByMatv;

    @JsonIgnore
    @OneToMany(mappedBy = "billaccessoriesByMahd")
    private List<Billaccessoriesdetail> billaccessoriesdetailsByMahd;

    
}
