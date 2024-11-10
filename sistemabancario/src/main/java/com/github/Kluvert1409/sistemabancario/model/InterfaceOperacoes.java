package com.github.Kluvert1409.sistemabancario.model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

/**
 * @author Kluvert
 */
public class InterfaceOperacoes extends JFrame {

    private JTextField valor;
    private JLabel nomeTopo, idTopo, valorLabel;
    private JButton botaoDepositar, botaoSacar, botaoConsultar;

    private int id;

    public InterfaceOperacoes(String nomeConta, int idConta) {
        this.id = idConta;
        configurarJanela();
        inicializarComponentes(nomeConta, idConta);
        adicionarAcoesBotoes();
    }

    private void configurarJanela() {
        setTitle("Operações da Conta");
        setSize(520, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
    }

    private void inicializarComponentes(String nomeConta, int idConta) {
        nomeTopo = new JLabel("Olá, " + nomeConta);
        nomeTopo.setBounds(50, 10, 150, 30);
        nomeTopo.setFont(new Font("Arial", Font.BOLD, 18));
        nomeTopo.setForeground(new Color(34, 45, 50));
        add(nomeTopo);

        idTopo = new JLabel("N° da Conta: " + idConta);
        idTopo.setBounds(250, 10, 150, 30);
        idTopo.setFont(new Font("Arial", Font.BOLD, 18));
        idTopo.setForeground(new Color(34, 45, 50));
        add(idTopo);

        valorLabel = new JLabel("Valor:");
        valorLabel.setBounds(50, 220, 150, 30);
        valorLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        valorLabel.setForeground(new Color(34, 45, 50));
        add(valorLabel);

        valor = new JTextField();
        valor.setBounds(200, 220, 250, 30);
        valor.setFont(new Font("Arial", Font.PLAIN, 14));
        valor.setBackground(new Color(255, 255, 255));
        valor.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        add(valor);

        botaoDepositar = new JButton("Depositar");
        botaoDepositar.setBounds(50, 280, 150, 40);
        botaoDepositar.setBackground(Color.black);
        botaoDepositar.setForeground(Color.white);
        botaoDepositar.setFocusPainted(false);
        add(botaoDepositar);

        botaoSacar = new JButton("Sacar");
        botaoSacar.setBounds(220, 280, 150, 40);
        botaoSacar.setBackground(Color.black);
        botaoSacar.setForeground(Color.white);
        botaoSacar.setFocusPainted(false);
        add(botaoSacar);

        botaoConsultar = new JButton("Consultar");
        botaoConsultar.setBounds(50, 340, 150, 40);
        botaoConsultar.setBackground(Color.black);
        botaoConsultar.setForeground(Color.white);
        botaoConsultar.setFocusPainted(false);
        add(botaoConsultar);
    }

    private void adicionarAcoesBotoes() {
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

    private void realizarDeposito() {
        String textoValor = valor.getText().trim();
        if (textoValor.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, insira um valor para o depósito", "Aviso", JOptionPane.WARNING_MESSAGE);
        } else {
            double valor = Double.parseDouble(textoValor);
            try {
                URL url = new URL("http://localhost:8080/conta/depositar/" + id + "/" + valor);
                HttpURLConnection conexaoHttp = (HttpURLConnection) url.openConnection();
                conexaoHttp.setRequestMethod("PUT");
                int resposta = conexaoHttp.getResponseCode();

                if (resposta == 200) {
                    JOptionPane.showMessageDialog(this, "Depósito realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao realizar depósito", "Erro", JOptionPane.ERROR_MESSAGE);
                }

                conexaoHttp.disconnect();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erro ao realizar depósito: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void realizarSaque() {
        String textoValor = valor.getText().trim();
        if (textoValor.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, insira um valor para o saque", "Aviso", JOptionPane.WARNING_MESSAGE);
        } else {
            double valor = Double.parseDouble(textoValor);
            try {
                URL url = new URL("http://localhost:8080/conta/sacar/" + id + "/" + valor);
                HttpURLConnection conexaoHttp = (HttpURLConnection) url.openConnection();
                conexaoHttp.setRequestMethod("PUT");
                int resposta = conexaoHttp.getResponseCode();

                if (resposta == 200) {
                    JOptionPane.showMessageDialog(this, "Saque realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao realizar saque", "Erro", JOptionPane.ERROR_MESSAGE);
                }

                conexaoHttp.disconnect();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erro ao realizar saque: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
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
                String dadosConta = scanner.useDelimiter("\\A").next();
                scanner.close();
                JOptionPane.showMessageDialog(this, dadosConta, "Informações da Conta", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao consultar conta", "Erro", JOptionPane.ERROR_MESSAGE);
            }

            conexaoHttp.disconnect();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao consultar conta: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}
