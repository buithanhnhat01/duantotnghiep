package com.fptpoly.main.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Accessories {

    @Id
    @Column(name = "Malk")
    private String malk;

    @Basic
    @Column(name = "Ten")
    private String ten;
    @Basic
    @Column(name = "Gia")
    private Double gia;
    @Basic
    @Column(name = "Soluong")
    private Integer soluong;
    @Basic
    @Column(name = "Ghichu")
    private String ghichu;
    @Basic
    @Column(name = "Xuatxu")
    private String xuatxu;
    @ManyToOne
    @JoinColumn(name = "Idcar", referencedColumnName = "Idcar")
    private Car carByMacar;
    @JsonIgnore
    @OneToMany(mappedBy = "accessoriesByMalk")
    private List<Billaccessoriesdetail> billaccessoriesdetailsByMalk;

    @JsonIgnore
    @OneToMany(mappedBy = "accessoriesByMalk")
    private List<Cartaccessories> cartaccessoriesByMalk;

    @JsonIgnore
    @OneToMany(mappedBy = "accessoriesByMalk")
    private List<Imagesaccessories> imagesaccessoriesByMalk;

    @JsonIgnore
    @OneToMany(mappedBy = "accessoriesByMalk")
    private List<Voteaccessories> voteaccessoriesByMalk;

}
