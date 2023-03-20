package com.leiton.ejercicioLogin.entity;

import java.time.Instant;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "USERS")
@Getter
@Setter
public class UserEntity {
    
    @Id
    private String id;

    private Instant createdDate;

    private Instant lastLogin;

    private String token;

    private Boolean isActive;

    private String name;

    private String email;

    private String password;

    @OneToMany(fetch = FetchType.EAGER, targetEntity = PhoneEntity.class)
    @Cascade(CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private List<PhoneEntity> phones;
}