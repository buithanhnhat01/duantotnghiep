package com.fptpoly.main.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Account {

    @Id
    @Column(name = "Matv",unique = true)
    @NotEmpty(message = "Vui Lòng Nhập Tên Đăng Nhập")
    private String matv;
    @Basic
    @Column(name = "Hoten")
    private String hoten;
    @Basic
    @Column(name = "Sdt")
    private String sdt;
    @Basic
    @Column(name = "Email")
    private String email;
    @Basic
    @Column(name = "Hinh")
    private String hinh;
    @Basic
    @Column(name = "Gioitinh")
    private Boolean gioitinh;
    @Basic
    @Column(name = "Diachi")
    private String diachi;
    @Basic
    @Column(name = "Password")
    @NotEmpty(message = "Vui Lòng Nhập Mật Khẩu")
    private String password;

    @Basic
    @Column(name = "Role")
    private String role;

    @JsonIgnore
    @OneToMany(mappedBy = "accountByMatv")
    private List<Appointment> appointmentsByMatv;

    @JsonIgnore
    @OneToMany(mappedBy = "accountByMatv")
    private List<Appointment> appointmentsByManv;

    @JsonIgnore
    @OneToMany(mappedBy = "accountByMatv")
    private List<Billaccessories> billaccessoriesByMatv;

    @JsonIgnore
    @OneToMany(mappedBy = "accountByMatv")
    private List<Billcar> billcarsByMatv;

    @JsonIgnore
    @OneToMany(mappedBy = "accountByManv")
    private List<Billcar> billcarsByManv;

    @JsonIgnore
    @OneToMany(mappedBy = "account")
    private List<Cartaccessories> cartaccessories = new java.util.ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "accountByMatv")
    private List<Cartcar> cartcarsByMatv;

    @JsonIgnore
    @OneToMany(mappedBy = "accountByMatv")
    private List<Voteaccessories> voteaccessoriesByMatv;

    @JsonIgnore
    @OneToMany(mappedBy = "accountByMatv")
    private List<Votecar> votecarsByMatv;

    
}
