package com.fptpoly.main.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Typecar {

    @Id
    @Column(name = "Maloai")
    private String maloai;
    @Basic
    @Column(name = "Tenloai")
    private String tenloai;
    @Basic
    @Column(name = "Logo")
    private String logo;

    @JsonIgnore
    @OneToMany(mappedBy = "typecarByLoaixe")
    private List<Car> carsByMaloai;


}
