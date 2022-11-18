package com.fptpoly.main.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Brand {

    @Id
    @Column(name = "Ma")
    private String ma;
    @Basic
    @Column(name = "Tenhang")
    private String tenhang;
    @Basic
    @Column(name = "Logo")
    private String logo;

    @JsonIgnore
    @OneToMany(mappedBy = "brandByMa")
    private List<Car> carsByMa;

   
}
