package com.github.Kluvert1409.sistemabancario.model;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Kluvert
 */
public class InterfaceGrafica extends JFrame {

    private JTextField nomeConta;
    private JComboBox<String> tipoConta;
    private JLabel nomeLabel, tipoContaLabel;
    private JPanel panelBackGround;
    private JButton botaoCriarConta;

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
        setSize(520, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
    }

    private void inicializarComponentes() {
        adicionarCamposTexto();
        adicionarLabels();
        adicionarBotoes();
        panelBackground();
    }


    private void panelBackground() {
        panelBackGround = new JPanel();
        panelBackGround.setBackground(new Color(32, 32, 32));
        panelBackGround.setBounds(0, 0, 520, 600);
        add(panelBackGround);
    }

    private void adicionarCamposTexto() {
        nomeConta = new JTextField();
        nomeConta.setBounds(200, 60, 250, 30);
        nomeConta.setFont(new Font("Cascadian", Font.BOLD, 16));
        nomeConta.setBackground(Color.lightGray);
        nomeConta.setForeground(Color.black);
        nomeConta.setBorder(new LineBorder(new Color(235, 94, 0), 1));
        add(nomeConta);

        tipoConta = new JComboBox<>(new String[]{"Conta Corrente", "Conta Poupança", "Conta Especial"});
        tipoConta.setBounds(200, 110, 250, 30);
        tipoConta.setFont(new Font("Cascadian", Font.PLAIN, 18));
        tipoConta.setBackground(Color.lightGray);
        tipoConta.setForeground(Color.black);
        tipoConta.setBorder(new LineBorder(new Color(235, 94, 0), 1));
        add(tipoConta);
    }

    private void adicionarLabels() {

        nomeLabel = new JLabel("Nome:");
        nomeLabel.setBounds(50, 60, 150, 30);
        nomeLabel.setFont(new Font("Cascadian", Font.BOLD, 18));
        nomeLabel.setForeground(new Color(255, 102, 0));
        add(nomeLabel);

        tipoContaLabel = new JLabel("Tipo de Conta:");
        tipoContaLabel.setBounds(50, 110, 150, 30);
        tipoContaLabel.setFont(new Font("Cascadian", Font.BOLD, 18));
        tipoContaLabel.setForeground(new Color(255, 102, 0));
        add(tipoContaLabel);
    }

    private void adicionarBotoes() {
        botaoCriarConta = new JButton("Criar Conta");
        botaoCriarConta.setBounds(50, 170, 150, 40);
        botaoCriarConta.setFont(new Font("Cascadian", Font.PLAIN, 18));
        botaoCriarConta.setBackground(new Color(255, 102, 0));
        botaoCriarConta.setForeground(Color.white);
        botaoCriarConta.setBorder(new LineBorder(new Color(235, 94, 0), 1));
        botaoCriarConta.setFocusPainted(false);
        add(botaoCriarConta);
    }

    private void adicionarAcoesBotoes() {
        botaoCriarConta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                criarConta();
            }
        });

        botaoCriarConta.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                botaoCriarConta.setBorder(new LineBorder(new Color(255, 229, 204), 2));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botaoCriarConta.setBorder(new LineBorder(new Color(235, 94, 0), 2));
            }
        });
    }

    private void criarConta() {
        String nome = nomeConta.getText().trim();
        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, insira um nome para a conta", "Aviso", JOptionPane.WARNING_MESSAGE);
        } else {
            int tipo = tipoConta.getSelectedIndex();
            String tipoContaString;
            switch (tipo) {
                case 0 -> tipoContaString = "ContaCorrente";
                case 1 -> tipoContaString = "ContaPoupanca";
                case 2 -> tipoContaString = "ContaEspecial";
                default -> tipoContaString = "";
            }

            try {
                URL url = new URL("http://localhost:8080/conta/criarConta/" + tipoContaString + "/" + nome);
                HttpURLConnection conexaoHttp = (HttpURLConnection) url.openConnection();
                conexaoHttp.setRequestMethod("POST");
                int resposta = conexaoHttp.getResponseCode();

                if (resposta == 200) {
                    JOptionPane.showMessageDialog(this, "Conta criada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                    // Abra a nova janela de operações e feche a atual
                    InterfaceOperacoes operacoesJanela = new InterfaceOperacoes(nome, id);
                    operacoesJanela.setVisible(true);

                    dispose(); // Fechar a janela atual

                } else {
                    JOptionPane.showMessageDialog(this, "Erro ao criar conta", "Erro", JOptionPane.ERROR_MESSAGE);
                }

                conexaoHttp.disconnect();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erro ao criar conta: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
}