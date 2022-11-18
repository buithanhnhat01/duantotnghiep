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
public class Cartcar {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Stt")
    private int stt;
    @ManyToOne
    @JoinColumn(name = "Idcar", referencedColumnName = "Idcar", nullable = false)
    private Car carByMaxe;
    @ManyToOne
    @JoinColumn(name = "Matv", referencedColumnName = "Matv", nullable = false)
    private Account accountByMatv;

    
}
