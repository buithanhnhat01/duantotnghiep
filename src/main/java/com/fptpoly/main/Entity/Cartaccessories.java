package com.fptpoly.main.Entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

@AllArgsConstructor
@NoArgsConstructor
@Entity
@Data
public class Cartaccessories {

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
    @ManyToOne
    @JoinColumn(name = "Malk")
    private Accessories accessoriesByMalk;
    @ManyToOne
    @JoinColumn(name = "Matv")
    private Account account;

    
}
