package br.cadastrovendas.model;
// Generated Oct 4, 2012 3:51:06 PM by Hibernate Tools 3.2.1.GA

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * Venda generated by hbm2java
 */
@Entity
@Table(name = "VENDA")
public class Venda implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id_venda;
    @Column
    private int id_usuario;
    @Column
    @Temporal(TemporalType.DATE)
    private Date data_venda;
    @Column
    private double valor_venda;
    @Transient
    private String nome_usuario;
    @Transient
    private List<Produto> produtos;

    public Venda() {
    }

    public int getId_Venda() {
        return id_venda;
    }

    public void setId_Venda(int id_venda) {
        this.id_venda = id_venda;
    }

    public int getId_Usuario() {
        return id_usuario;
    }

    public void setId_Usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getData_venda() {
        return new SimpleDateFormat("dd/MM/yyyy").format(data_venda);
    }

    public void setData_venda(Date data_venda) {
        this.data_venda = data_venda;
    }
    
    public List<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(List<Produto> produtos) {
        this.produtos = produtos;
    }

    public String getNome_usuario() {
        return nome_usuario;
    }

    public void setNome_usuario(String nome_usuario) {
        this.nome_usuario = nome_usuario;
    }
    
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }

    public double getValor_venda() {
        return valor_venda;
    }

    public void setValor_venda(double valor_venda) {
        this.valor_venda = valor_venda;
    }
    
}
