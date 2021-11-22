package com.VPI.VPI.Entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Administradores")
public class AdminEntity extends UsuarioEntity{

    public AdminEntity() {
    }
}
