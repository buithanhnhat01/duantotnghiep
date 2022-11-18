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
public class Imagesaccessories {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Stt")
    private int stt;

    @Basic
    @Column(name = "Hinh")
    private String hinh;
    @ManyToOne
    @JoinColumn(name = "Malk", referencedColumnName = "Malk")
    private Accessories accessoriesByMalk;

    
}
