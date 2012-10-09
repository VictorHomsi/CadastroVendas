package br.cadastrovendas.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import org.apache.commons.lang.builder.ToStringBuilder;

@Entity
@Table(name = "USUARIO")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_usuario;
    @Column
    private String nome_usuario;

    public Usuario() {
    }

    public Usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }
    public int getId_Usuario() {
        return id_usuario;
    }

    public void setId_Usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNomeUsuario() {
        return nome_usuario;
    }

    public void setNomeUsuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
