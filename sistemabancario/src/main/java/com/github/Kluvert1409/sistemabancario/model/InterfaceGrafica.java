package com.github.Kluvert1409.sistemabancario.model;

import java.awt.Color;
import java.awt.Font;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.lang.Exception;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 *
 * @author Kluvert
 */
public class InterfaceGrafica extends JFrame {

    private JTextField nomeConta, valor;
    private JComboBox<String> tipoConta;
    private JTextArea resultadoArea;
    private JLabel nomeLabel, tipoContaLabel, valorLabel;
    private JButton botaoCriarConta, botaoDepositar, botaoSacar, botaoConsultar, botao;

    private ContaCorrente contaCorrente;
    private ContaPoupanca contaPoupanca;
    private ContaEspecial contaEspecial;
    private int id = 1;

    public InterfaceGrafica() {
        configurarJanela();
        inicializarComponentes();
        adicionarAcoesBotoes();
    }

    private void configurarJanela() {
        setTitle("Sistema Bancário");
        setSize(1440, 720);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
    }

    private void inicializarComponentes() {
        adicionarCamposTexto();
        adicionarLabels();
        adicionarBotoes();
        adicionarAreaDeTexto();
    }

    private void adicionarCamposTexto() {
        nomeConta = new JTextField();
        nomeConta.setBounds(200, 30, 250, 30);
        add(nomeConta);

        tipoConta = new JComboBox<>(new String[]{"Conta Corrente", "Conta Poupança", "Conta Especial"});
        tipoConta.setBounds(200, 80, 250, 30);
        add(tipoConta);

        valor = new JTextField();
        valor.setBounds(200, 190, 250, 30);
        add(valor);
    }

    private void adicionarLabels() {
        nomeLabel = new JLabel("Nome:");
        nomeLabel.setBounds(50, 30, 150, 30);
        add(nomeLabel);

        tipoContaLabel = new JLabel("Tipo de Conta:");
        tipoContaLabel.setBounds(50, 80, 150, 30);
        add(tipoContaLabel);

        valorLabel = new JLabel("Valor:");
        valorLabel.setBounds(50, 190, 150, 30);
        add(valorLabel);
    }

    private void adicionarBotoes() {
        botaoCriarConta = new JButton("Criar Conta");
        botaoCriarConta.setBounds(50, 130, 150, 40);
        botaoCriarConta.setBackground(Color.black);
        botaoCriarConta.setForeground(Color.white);
        botaoCriarConta.setFocusPainted(false);
        add(botaoCriarConta);

        botaoDepositar = new JButton("Depositar");
        botaoDepositar.setBounds(50, 240, 150, 40);
        botaoDepositar.setBackground(Color.black);
        botaoDepositar.setForeground(Color.white);
        botaoDepositar.setFocusPainted(false);
        add(botaoDepositar);

        botaoSacar = new JButton("Sacar");
        botaoSacar.setBounds(220, 240, 150, 40);
        botaoSacar.setBackground(Color.black);
        botaoSacar.setForeground(Color.white);
        botaoSacar.setFocusPainted(false);
        add(botaoSacar);

        botaoConsultar = new JButton("Consultar");
        botaoConsultar.setBounds(50, 300, 150, 40);
        botaoConsultar.setBackground(Color.black);
        botaoConsultar.setForeground(Color.white);
        botaoConsultar.setFocusPainted(false);
        add(botaoConsultar);
    }

    private void adicionarAreaDeTexto() {
        resultadoArea = new JTextArea();
        resultadoArea.setBounds(50, 360, 1340, 300);
        resultadoArea.setFont(new Font("Consolas", Font.BOLD, 20));
        resultadoArea.setEditable(false);
        add(resultadoArea);
    }

    private void adicionarAcoesBotoes() {
        botaoCriarConta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                criarConta();
            }
        });

        botaoDepositar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarDeposito();
            }
        });

        botaoSacar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarSaque();
            }
        });

        botaoConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consultarConta();
            }
        });
    }


    private void criarConta() {
        String nome = nomeConta.getText().trim();
        if (nome.isEmpty()) {
            resultadoArea.setText("Por favor, insira um nome para a conta");
        } else {
            int tipo = tipoConta.getSelectedIndex();
            String tipoContaString = "";
            switch (tipo) {
                case 0:
                    tipoContaString = "ContaCorrente";
                    break;
                case 1:
                    tipoContaString = "ContaPoupanca";
                    break;
                case 2:
                    tipoContaString = "ContaEspecial";
                    break;
                default:
                    resultadoArea.setText("Tipo de conta inválido");
                    return;
            }

            try {
                URL url = new URL("http://localhost:8080/conta/criarConta/" + tipoContaString + "/" + nome);
                HttpURLConnection conexaoHttp = (HttpURLConnection) url.openConnection();
                conexaoHttp.setRequestMethod("POST");
                int resposta = conexaoHttp.getResponseCode();

                if (resposta == 200) {
                    resultadoArea.setText("Conta criada com sucesso!");
                } else {
                    resultadoArea.setText("Erro ao criar conta");
                }

                conexaoHttp.disconnect();
            } catch (Exception e) {
                resultadoArea.setText("Erro ao criar conta: " + e.getMessage());
            }
        }
    }

    private void realizarDeposito() {
        String textoValor = this.valor.getText().trim();
        if (textoValor.isEmpty()) {
            resultadoArea.setText("Por favor, insira um valor para o depósito");
        } else {
            double valor = Double.parseDouble(this.valor.getText());


            try {
                URL url = new URL("http://localhost:8080/conta/depositar/" + id + "/" + valor);
                HttpURLConnection conexaoHttp = (HttpURLConnection) url.openConnection();
                conexaoHttp.setRequestMethod("PUT");
                int resposta = conexaoHttp.getResponseCode();

                if (resposta == 200) {
                    resultadoArea.setText("Depósito realizado com sucesso!");
                } else {
                    resultadoArea.setText("Erro ao realizar depósito");
                }

                conexaoHttp.disconnect();
            } catch (Exception e) {
                resultadoArea.setText("Erro ao realizar depósito: " + e.getMessage());
            }
        }
    }

    private void realizarSaque() {
        String textoValor = this.valor.getText().trim();
        if (textoValor.isEmpty()) {
            resultadoArea.setText("Por favor, insira um valor para o saque");
        } else {
            double valor = Double.parseDouble(this.valor.getText());
            try {
                URL url = new URL("http://localhost:8080/conta/sacar/" + id + "/" + valor);
                HttpURLConnection conexaoHttp = (HttpURLConnection) url.openConnection();
                conexaoHttp.setRequestMethod("PUT");
                int resposta = conexaoHttp.getResponseCode();

                if (resposta == 200) {
                    resultadoArea.setText("Saque realizado com sucesso!");
                } else {
                    resultadoArea.setText("Erro ao realizar saque");
                }
                conexaoHttp.disconnect();
            } catch (Exception e) {
                resultadoArea.setText("Erro ao realizar saque: " + e.getMessage());
            }
        }
    }


    private void consultarConta() {
        try {
            URL url = new URL("http://localhost:8080/conta/retornarDados/" + id);
            HttpURLConnection conexaoHttp = (HttpURLConnection) url.openConnection();
            conexaoHttp.setRequestMethod("GET");
            int resposta = conexaoHttp.getResponseCode();

            if (resposta == 200) {
                Scanner scanner = new Scanner(conexaoHttp.getInputStream());
                resultadoArea.setText(scanner.useDelimiter("\\A").next());
                scanner.close();
            } else {
                resultadoArea.setText("Erro ao consultar conta");
            }

            conexaoHttp.disconnect();
        } catch (Exception e) {
            resultadoArea.setText("Erro ao consultar conta: " + e.getMessage());
        }
    }

}