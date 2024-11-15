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

public class InterfaceAtualizar extends JFrame {

    private JTextField numeroConta;
    private JLabel texto;
    private JButton botaoApagarConta, botaoVoltar;
    private JPanel panelBackGround;
    private ImageIcon imagemVoltar;

    public InterfaceAtualizar() {
        configurarJanela();
        inicializarComponentes();
        adicionarAcoesBotoes();
    }

    private void configurarJanela() {
        setTitle("Sistema Bancário");
        setSize(520, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
    }

    private void inicializarComponentes() {
        adicionarLabels();
        adicionarCamposTexto();
        adicionarBotoes();
        panelBackground();
    }

    private void panelBackground() {
        panelBackGround = new JPanel();
        panelBackGround.setBackground(new Color(32, 32, 32));
        panelBackGround.setBounds(0, 0, 520, 600);
        add(panelBackGround);
    }

    private void adicionarLabels() {
        texto = new JLabel("Insira o número da conta que deseja deletar:");
        texto.setBounds(50, 60, 500, 30);
        texto.setFont(new Font("Cascadian", Font.BOLD, 18));
        texto.setForeground(new Color(255, 102, 0));
        add(texto);
    }

    private void adicionarCamposTexto() {
        numeroConta = new JTextField();
        numeroConta.setBounds(50, 110, 200, 40);
        numeroConta.setFont(new Font("Cascadian", Font.BOLD, 16));
        numeroConta.setBackground(Color.lightGray);
        numeroConta.setForeground(Color.black);
        numeroConta.setBorder(new LineBorder(new Color(235, 94, 0), 1));
        add(numeroConta);
    }

    private void adicionarBotoes() {
        botaoApagarConta = new JButton("Apagar Conta");
        botaoApagarConta.setBounds(290, 110, 150, 40);
        botaoApagarConta.setFont(new Font("Cascadian", Font.BOLD, 18));
        botaoApagarConta.setBackground(new Color(255, 102, 0));
        botaoApagarConta.setForeground(Color.white);
        botaoApagarConta.setBorder(new LineBorder(new Color(235, 94, 0), 1));
        botaoApagarConta.setFocusPainted(false);
        add(botaoApagarConta);

        botaoVoltar = new JButton();
        botaoVoltar.setBounds(50, 275, 80, 35);
        botaoVoltar.setFont(new Font("Cascadian", Font.BOLD, 18));
        botaoVoltar.setBackground(new Color(255, 102, 0));
        botaoVoltar.setForeground(Color.white);
        imagemVoltar = new ImageIcon(getClass().getResource("/imagens/back_48px.png"));
        botaoVoltar.setIcon(imagemVoltar);
        botaoVoltar.setBorder(new LineBorder(new Color(235, 94, 0), 1));
        botaoVoltar.setFocusPainted(false);
        add(botaoVoltar);
    }

    private void adicionarAcoesBotoes() {
        botaoApagarConta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                apagarConta();
            }
        });

        botaoApagarConta.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                botaoApagarConta.setBorder(new LineBorder(new Color(255, 229, 204), 2));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botaoApagarConta.setBorder(new LineBorder(new Color(235, 94, 0), 2));
            }
        });

        botaoVoltar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                retornar();
            }
        });

        botaoVoltar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                botaoVoltar.setBorder(new LineBorder(new Color(255, 229, 204), 2));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botaoVoltar.setBorder(new LineBorder(new Color(235, 94, 0), 2));
            }
        });
    }

    private void apagarConta() {
        String numeroString = numeroConta.getText().trim();
        if (numeroString.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, insira um número de conta", "Aviso", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                int id = Integer.parseInt(numeroString);
                try {
                    URL url = new URL("http://localhost:8080/conta/apagarConta/" + id);
                    HttpURLConnection conexaoHttp = (HttpURLConnection) url.openConnection();
                    conexaoHttp.setRequestMethod("DELETE");
                    int resposta = conexaoHttp.getResponseCode();

                    if (resposta == 200) {
                        JOptionPane.showMessageDialog(this, "Conta apagada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(this, "Erro ao apagar a conta", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                    conexaoHttp.disconnect();
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(this, "Erro ao tentar apagar a conta: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor, insira somente números", "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void retornar() {
        InterfaceFuncionario novaJanela = new InterfaceFuncionario();
        novaJanela.setVisible(true);
        dispose();
    }
}