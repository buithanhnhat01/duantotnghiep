package com.fptpoly.main.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.util.Date;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "Stt")
    private int stt;

    @Basic
    /*@Pattern(regexp = "yyyy-MM-dd")*/
    @Column(name = "Ngayhen")
    private Date ngayhen;
    @Basic
    @Column(name = "Khunggio")
    private String khunggio;
    @Basic
    @Column(name = "Loai")
    private String loai;

    @Basic
    @Column(name = "Ghichu")
    private String ghichu;
    @ManyToOne
    @JoinColumn(name = "Matv", referencedColumnName = "Matv")
    private Account accountByMatv;
    @ManyToOne
    @JoinColumn(name = "Manv", referencedColumnName = "Matv")
    private Account accountByManv;
    @ManyToOne
    @JoinColumn(name = "Idcar", referencedColumnName = "Idcar")
    private Car carByIdcar;

    
}
