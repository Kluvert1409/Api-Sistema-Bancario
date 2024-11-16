package com.github.Kluvert1409.sistemabancario.interfacesGUI;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.HttpURLConnection;
import java.net.URL;

public class InterfaceAtualizarConta extends JFrame {

    private JTextField numeroConta, nomeConta, complemento;
    private JLabel numeroLabel, nomeLabel, complementoLabel, tipoContaLabel;
    private JButton botaoAtualizarConta, botaoVoltar;
    private JComboBox<String> tipoConta;
    private JPanel panelBackGround;
    private ImageIcon imagemVoltar;

    public InterfaceAtualizarConta() {
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

        numeroConta = new JTextField();
        numeroConta.setBounds(200, 60, 250, 30);
        numeroConta.setFont(new Font("Cascadian", Font.BOLD, 16));
        numeroConta.setBackground(Color.lightGray);
        numeroConta.setForeground(Color.black);
        numeroConta.setBorder(new LineBorder(new Color(235, 94, 0), 1));
        add(numeroConta);

        nomeConta = new JTextField();
        nomeConta.setBounds(200, 110, 250, 30);
        nomeConta.setFont(new Font("Cascadian", Font.BOLD, 16));
        nomeConta.setBackground(Color.lightGray);
        nomeConta.setForeground(Color.black);
        nomeConta.setBorder(new LineBorder(new Color(235, 94, 0), 1));
        add(nomeConta);

        complemento = new JTextField();
        complemento.setBounds(200, 160, 250, 30);
        complemento.setFont(new Font("Cascadian", Font.BOLD, 16));
        complemento.setBackground(Color.lightGray);
        complemento.setForeground(Color.black);
        complemento.setBorder(new LineBorder(new Color(235, 94, 0), 1));
        add(complemento);

        tipoConta = new JComboBox<>(new String[]{"Conta Corrente", "Conta Poupança", "Conta Especial"});
        tipoConta.setBounds(200, 210, 250, 30);
        tipoConta.setFont(new Font("Cascadian", Font.PLAIN, 18));
        tipoConta.setBackground(Color.lightGray);
        tipoConta.setForeground(Color.black);
        tipoConta.setBorder(new LineBorder(new Color(235, 94, 0), 1));
        add(tipoConta);
    }

    private void adicionarLabels() {

        numeroLabel = new JLabel("N° da conta:");
        numeroLabel.setBounds(50, 60, 150, 30);
        numeroLabel.setFont(new Font("Cascadian", Font.BOLD, 18));
        numeroLabel.setForeground(new Color(255, 102, 0));
        add(numeroLabel);

        nomeLabel = new JLabel("Nome:");
        nomeLabel.setBounds(50, 110, 150, 30);
        nomeLabel.setFont(new Font("Cascadian", Font.BOLD, 18));
        nomeLabel.setForeground(new Color(255, 102, 0));
        add(nomeLabel);

        complementoLabel = new JLabel("Taxa:");
        complementoLabel.setBounds(50, 160, 150, 30);
        complementoLabel.setFont(new Font("Cascadian", Font.BOLD, 18));
        complementoLabel.setForeground(new Color(255, 102, 0));
        add(complementoLabel);

        tipoContaLabel = new JLabel("Tipo de Conta:");
        tipoContaLabel.setBounds(50, 210, 150, 30);
        tipoContaLabel.setFont(new Font("Cascadian", Font.BOLD, 18));
        tipoContaLabel.setForeground(new Color(255, 102, 0));
        add(tipoContaLabel);
    }

    private void adicionarBotoes() {

        botaoAtualizarConta = new JButton("Atualizar Conta");
        botaoAtualizarConta.setBounds(50, 260, 150, 40);
        botaoAtualizarConta.setFont(new Font("Cascadian", Font.BOLD, 18));
        botaoAtualizarConta.setBackground(new Color(255, 102, 0));
        botaoAtualizarConta.setForeground(Color.white);
        botaoAtualizarConta.setBorder(new LineBorder(new Color(235, 94, 0), 1));
        botaoAtualizarConta.setFocusPainted(false);
        add(botaoAtualizarConta);

        botaoVoltar = new JButton();
        botaoVoltar.setBounds(50, 450, 80, 35);
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
        botaoAtualizarConta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                atualizar();
            }
        });

        botaoAtualizarConta.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                botaoAtualizarConta.setBorder(new LineBorder(new Color(255, 229, 204), 2));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botaoAtualizarConta.setBorder(new LineBorder(new Color(235, 94, 0), 2));
            }
        });

        tipoConta.addItemListener(new java.awt.event.ItemListener() {
            @Override
            public void itemStateChanged(java.awt.event.ItemEvent e) {
                atualizarLabelComplemento();
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

    private void atualizarLabelComplemento() {
        int tipo = tipoConta.getSelectedIndex();

        switch (tipo) {
            case 0:
                complementoLabel.setText("Taxa:");
                break;
            case 1:
                complementoLabel.setText("Rendimento:");
                break;
            case 2:
                complementoLabel.setText("Limite:");
                break;
            default:
                complementoLabel.setText("Taxa:");
                break;
        }
    }

    private void atualizar() {
        String numeroString = numeroConta.getText().trim();
        String nome = nomeConta.getText().trim();

        if (numeroString.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, insira um número de conta", "Aviso", JOptionPane.WARNING_MESSAGE);
        } else {
            String complementoString = complemento.getText().trim();
            if (complementoString.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, insira um valor de complemento", "Aviso", JOptionPane.WARNING_MESSAGE);
            } else {
                double valorComplemento = Double.parseDouble(complementoString);

                int tipo = tipoConta.getSelectedIndex();
                String tipoContaString;
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
                        tipoContaString = "";
                        break;
                }

                try {
                    int id = Integer.parseInt(numeroString);
                    try {
                        URL url = new URL("http://localhost:8080/conta/atualizarConta/" + id + "/" + tipoContaString + "/" + valorComplemento + "/" + nome);
                        HttpURLConnection conexaoHttp = (HttpURLConnection) url.openConnection();
                        conexaoHttp.setRequestMethod("PUT");
                        int resposta = conexaoHttp.getResponseCode();

                        if (resposta == 200) {
                            JOptionPane.showMessageDialog(this, "Conta atualizada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                        } else {
                            JOptionPane.showMessageDialog(this, "Erro ao atualizar a conta", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                        conexaoHttp.disconnect();
                    } catch (Exception e) {
                        JOptionPane.showMessageDialog(this, "Erro ao tentar atualizar a conta: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(this, "Por favor, insira somente números no campo número conta", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }

    private void retornar() {
        InterfaceFuncionario novaJanela = new InterfaceFuncionario();
        novaJanela.setVisible(true);
        dispose();
    }
}