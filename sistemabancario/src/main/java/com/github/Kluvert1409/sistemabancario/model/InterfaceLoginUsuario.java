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
import java.util.Scanner;

public class InterfaceLoginUsuario extends JFrame {

    private JTextField campoId;
    private JButton botaoConfirmar, botaoVoltar;
    private JPanel panelBackGround;
    private JLabel texto;
    private ImageIcon imagemVoltar;

    public InterfaceLoginUsuario() {
        configurarJanela();
        inicializarComponentes();
        adicionarBotoes();
        adicionarAcoes();
        panelBackground();
    }

    private void configurarJanela() {
        setTitle("Login do Usuário");
        setSize(520, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
    }

    private void panelBackground() {
        panelBackGround = new JPanel();
        panelBackGround.setBackground(new Color(32, 32, 32));
        panelBackGround.setBounds(0, 0, 520, 400);
        add(panelBackGround);
    }

    private void inicializarComponentes() {
        texto = new JLabel("Digite o número da conta:");
        texto.setBounds(50, 60, 500, 30);
        texto.setFont(new Font("Cascadian", Font.BOLD, 18));
        texto.setForeground(new Color(255, 102, 0));
        add(texto);

        campoId = new JTextField();
        campoId.setBounds(50, 110, 200, 40);
        campoId.setFont(new Font("Cascadian", Font.BOLD, 16));
        campoId.setBackground(Color.lightGray);
        campoId.setForeground(Color.black);
        campoId.setBorder(new LineBorder(new Color(235, 94, 0), 1));
        add(campoId);
    }

    private void adicionarBotoes() {
        botaoConfirmar = new JButton("Confirmar");
        botaoConfirmar.setBounds(290, 110, 150, 40);
        botaoConfirmar.setFont(new Font("Cascadian", Font.BOLD, 18));
        botaoConfirmar.setBackground(new Color(255, 102, 0));
        botaoConfirmar.setForeground(Color.white);
        botaoConfirmar.setBorder(new LineBorder(new Color(235, 94, 0), 1));
        botaoConfirmar.setFocusPainted(false);
        add(botaoConfirmar);

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
                        JOptionPane.showMessageDialog(InterfaceLoginUsuario.this, "Id inválido. Insira um número.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        botaoConfirmar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                botaoConfirmar.setBorder(new LineBorder(new Color(255, 229, 204), 2));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botaoConfirmar.setBorder(new LineBorder(new Color(235, 94, 0), 2));
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
        InterfaceOperacoes novaJanela = new InterfaceOperacoes(nome, id);
        novaJanela.setVisible(true);
        dispose();
    }

    private void retornar() {
        InterfacePrimaria novaJanela = new InterfacePrimaria();
        novaJanela.setVisible(true);
        dispose();
    }
}
