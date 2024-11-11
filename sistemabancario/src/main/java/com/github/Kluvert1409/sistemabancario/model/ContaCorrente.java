package com.github.Kluvert1409.sistemabancario.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * @author Kluvert
 */
@Entity
@Table(name = "conta_corrente")
public class ContaCorrente extends Conta {

    private double taxa;

    public ContaCorrente() {
    }

    public ContaCorrente(String nomeConta, String tipoConta, double taxa) {
        super(nomeConta, tipoConta);
        this.taxa = taxa;
    }

    public ContaCorrente(String nomeConta, String tipoConta, double saldoConta, double taxa) {
        super(nomeConta, tipoConta, saldoConta);
        this.taxa = taxa;
    }

    public ContaCorrente(int numeroConta, String nomeConta, String tipoConta, double taxa) {
        super(numeroConta, nomeConta, tipoConta);
        this.taxa = taxa;
    }

    public ContaCorrente(int numeroConta, String nomeConta, String tipoConta, double saldoConta, double taxa) {
        super(numeroConta, nomeConta, tipoConta, saldoConta);
        this.taxa = taxa;
    }

    public double getTaxa() {
        return taxa;
    }

    public void setTaxa(double taxa) {
        this.taxa = taxa;
    }

    @Override
    public String setSacar(double valorSaque) throws Exception {
        if (valorSaque <= 0) {
            throw new Exception("Valor de saque inválido");
        } else if (getSaldoConta() < valorSaque) {
            throw new Exception("Saldo insuficiente para saque");
        } else {
            setSaldoConta(getSaldoConta() - (valorSaque + taxa));
            return "Saque realizado com sucesso";
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
                + "Taxa por operação: " + getTaxa());
    }
}
