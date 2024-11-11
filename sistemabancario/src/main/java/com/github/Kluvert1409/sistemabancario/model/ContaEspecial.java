package com.github.Kluvert1409.sistemabancario.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * @author Kluvert
 */
@Entity
@Table(name = "conta_especial")
public class ContaEspecial extends Conta {

    private double limite;

    public ContaEspecial() {
    }

    public ContaEspecial(String nomeConta, String tipoConta, double limite) {
        super(nomeConta, tipoConta);
        this.limite = limite;
    }

    public ContaEspecial(String nomeConta, String tipoConta, double saldoConta, double taxa) {
        super(nomeConta, tipoConta, saldoConta);
        this.limite = limite;
    }

    public ContaEspecial(int numeroConta, String nomeConta, String tipoConta, double limite) {
        super(numeroConta, nomeConta, tipoConta);
        this.limite = limite;
    }

    public ContaEspecial(int numeroConta, String nomeConta, String tipoConta, double saldoConta, double limite) {
        super(numeroConta, nomeConta, tipoConta, saldoConta);
        this.limite = limite;
    }

    public double getLimite() {
        return limite;
    }

    public void setLimite(double limite) {
        this.limite = limite;
    }

    @Override
    public String setSacar(double valorSaque) throws Exception {
        if (valorSaque <= 0) {
            throw new Exception("Valor de saque inválido");
        } else if (valorSaque > getSaldoConta() + getLimite()) {
            throw new Exception("Saldo insuficiente para saque");
        } else {
            setSaldoConta(getSaldoConta() - valorSaque);
            return ("Saque realizado com sucesso");
        }
    }

    @Override
    public String setDepositar(double valorDeposito) throws Exception {
        if (valorDeposito <= 0) {
            throw new Exception("Valor de depósito inválido");
        } else {
            setSaldoConta(getSaldoConta() + valorDeposito);
            return "Depósito realizado com sucesso";
        }
    }

    @Override
    public String getRetorno() {
        return ("Nome do cliente: " + getNomeConta() + "\n"
                + "Numero da conta: " + getNumeroConta() + "\n"
                + "Saldo: " + getSaldoConta() + "\n"
                + "Limite adicional: " + getLimite());
    }
}
