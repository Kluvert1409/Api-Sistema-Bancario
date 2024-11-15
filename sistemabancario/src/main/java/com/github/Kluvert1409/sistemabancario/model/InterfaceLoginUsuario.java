package com.github.Kluvert1409.sistemabancario.model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class InterfaceLoginUsuario extends JFrame {

    private JTextField campoId;
    private JButton botaoConfirmar;

    public InterfaceLoginUsuario() {
        configurarJanela();
        inicializarComponentes();
        adicionarAcoes();
    }

    private void configurarJanela() {
        setTitle("Login do Usuário");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
    }

    private void inicializarComponentes() {
        JLabel labelId = new JLabel("Digite o número da conta:");
        labelId.setBounds(100, 30, 300, 20);
        labelId.setFont(new Font("Cascadian", Font.BOLD, 14));
        add(labelId);

        campoId = new JTextField();
        campoId.setBounds(50, 60, 300, 30);
        campoId.setFont(new Font("Cascadian", Font.PLAIN, 14));
        add(campoId);

        botaoConfirmar = new JButton("Confirmar");
        botaoConfirmar.setBounds(125, 110, 150, 30);
        botaoConfirmar.setFont(new Font("Cascadian", Font.BOLD, 14));
        botaoConfirmar.setBackground(new Color(255, 102, 0));
        botaoConfirmar.setForeground(Color.WHITE);
        botaoConfirmar.setFocusPainted(false);
        add(botaoConfirmar);
    }

    private void adicionarAcoes() {
        botaoConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idTexto = campoId.getText().trim();
                if (idTexto.isEmpty()) {
                    JOptionPane.showMessageDialog(InterfaceLoginUsuario.this, "Por favor, insira um ID.", "Aviso", JOptionPane.WARNING_MESSAGE);
                } else {
                    try {
                        int id = Integer.parseInt(idTexto);
                        processarConta(id);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(InterfaceLoginUsuario.this, "ID inválido. Insira um número.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });
    }

    private void processarConta(int id) {
        try {
            URL url = new URL("http://localhost:8080/conta/retornarDadosSimples/" + id);
            HttpURLConnection conexaoHttp = (HttpURLConnection) url.openConnection();
            conexaoHttp.setRequestMethod("GET");

            int resposta = conexaoHttp.getResponseCode();

            if (resposta == 200) {
                Scanner scanner = new Scanner(conexaoHttp.getInputStream());
                String dadosConta = scanner.nextLine();
                scanner.close();

                if (!"Conta não encontrada".equals(dadosConta)) {
                    String[] partes = dadosConta.split(";");
                    String nome = partes[0];
                    int idConta = Integer.parseInt(partes[1]);

                    redirecionarParaOperacoes(nome, idConta);
                } else {
                    JOptionPane.showMessageDialog(this, "Número da conta não encontrado.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao verificar o número. Código: " + resposta, "Erro", JOptionPane.ERROR_MESSAGE);
            }

            conexaoHttp.disconnect();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao processar a conta: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void redirecionarParaOperacoes(String nome, int id) {
        InterfaceOperacoes operacoesJanela = new InterfaceOperacoes(nome, id);
        operacoesJanela.setVisible(true);
        dispose();
    }
}
