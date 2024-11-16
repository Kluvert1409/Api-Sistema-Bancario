package com.github.Kluvert1409.sistemabancario.pojos;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "conta_poupanca")
public class ContaPoupanca extends Conta {

    private double rendimento;

    public ContaPoupanca() {
    }

    public ContaPoupanca(String nomeConta, String tipoConta, double rendimento) {
        super(nomeConta, tipoConta);
        this.rendimento = rendimento;
    }

    public ContaPoupanca(String nomeConta, String tipoConta, double saldoConta, double rendimento) {
        super(nomeConta, tipoConta, saldoConta);
        this.rendimento = rendimento;
    }

    public ContaPoupanca(int numeroConta, String nomeConta, String tipoConta, double rendimento) {
        super(numeroConta, nomeConta, tipoConta);
        this.rendimento = rendimento;
    }

    public ContaPoupanca(int numeroConta, String nomeConta, String tipoConta, double saldoConta, double rendimento) {
        super(numeroConta, nomeConta, tipoConta, saldoConta);
        this.rendimento = rendimento;
    }

    public double getRendimento() {
        return rendimento;
    }

    public void setRendimento(double rendimento) {
        this.rendimento = rendimento;
    }

    public void setRendimentoConta(double rendimento) {
        setSaldoConta(getSaldoConta() + (getSaldoConta() * rendimento));
    }

    public double getRendimentoConta() {
        return getSaldoConta() + (getSaldoConta() * rendimento);
    }

    @Override
    public String setSacar(double valorSaque) throws Exception {
        if (valorSaque <= 0) {
            throw new Exception("Valor de saque inválido");
        } else if (getSaldoConta() < valorSaque) {
            throw new Exception("Saldo insuficiente para saque");
        } else {
            setSaldoConta(getSaldoConta() - (valorSaque));
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
    public String getRetornoSimples() {
        return (getNumeroConta() + ";" + getSaldoConta() + ";" + getNomeConta() + ";" + getTipoConta() + ";" + getRendimento());
    }

    @Override
    public String getRetorno() {
        if (getSaldoConta() > 0) {
            return ("Nome do cliente: " + getNomeConta() + "\n"
                    + "Saldo: " + getSaldoConta() + "\n"
                    + "Numero da conta:" + getNumeroConta() + "\n"
                    + "Rendimento da conta: " + getRendimento()) + "\n"
                    + "Saldo no próximo mês: " + (getSaldoConta() + (getSaldoConta() * getRendimento()));
        } else {
            return ("Nome do cliente: " + getNomeConta() + "\n"
                    + "Numero da conta: " + getNumeroConta() + "\n"
                    + "Saldo: " + getSaldoConta() + "\n"
                    + "Rendimento da conta: " + getRendimento()) + " ao mês";
        }
    }
}
