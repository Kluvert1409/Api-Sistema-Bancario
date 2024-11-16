package com.github.Kluvert1409.sistemabancario.pojos;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Conta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int numeroConta;

    private String tipoConta;

    private double saldoConta;

    private String nomeConta;

    public Conta() {
    }

    public Conta(String nomeConta, String tipoConta) {
        this.nomeConta = nomeConta;
        this.tipoConta = tipoConta;
    }

    public Conta(String nomeConta, String tipoConta, double saldoConta) {
        this.nomeConta = nomeConta;
        this.tipoConta = tipoConta;
        this.saldoConta = saldoConta;
    }

    public Conta(int numeroConta, String nomeConta, String tipoConta) {
        this.numeroConta = numeroConta;
        this.nomeConta = nomeConta;
        this.tipoConta = tipoConta;
    }

    public Conta(int numeroConta, String nomeConta, String tipoConta, double saldoConta) {
        this.numeroConta = numeroConta;
        this.nomeConta = nomeConta;
        this.tipoConta = tipoConta;
        this.saldoConta = saldoConta;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
    }

    public void setNumeroConta(int numeroConta) {
        this.numeroConta = numeroConta;
    }

    public int getNumeroConta() {
        return numeroConta;
    }

    public double getSaldoConta() {
        return saldoConta;
    }

    public void setSaldoConta(double saldoConta) {
        this.saldoConta = saldoConta;
    }

    public String getNomeConta() {
        return nomeConta;
    }

    public void setNomeConta(String nomeConta) {
        this.nomeConta = nomeConta;
    }

    public abstract String setSacar(double valorSaque) throws Exception;

    public abstract String setDepositar(double valorDeposito) throws Exception;

    public abstract String getRetornoSimples();

    public abstract String getRetorno();
}
