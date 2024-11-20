package com.github.Kluvert1409.sistemabancario.interfacesGUI;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InterfacePrimaria extends JFrame {

    private JButton botaoFuncionario, botaoUsuario;
    private JPanel panelBackGround;
    private JLabel texto;

    public InterfacePrimaria() {
        configurarJanela();
        criarTexto();
        criarBotoes();
        adicionarAcoesBotoes();
        panelBackground();
    }

    private void configurarJanela() {
        setTitle("Operações da Conta");
        setSize(520, 350);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
    }

    private void panelBackground() {
        panelBackGround = new JPanel();
        panelBackGround.setBackground(new Color(32, 32, 32));
        panelBackGround.setBounds(0, 0, 520, 600);
        add(panelBackGround);
    }

    private void criarTexto() {
        texto = new JLabel("Quem está usando o aplicativo?");
        texto.setBounds(90, 60, 330, 30);
        texto.setFont(new Font("Cascadian", Font.BOLD, 22));
        texto.setForeground(new Color(255, 102, 0));
        add(texto);
    }

    private void criarBotoes() {
        botaoFuncionario = new JButton("Funcionário");
        botaoFuncionario.setBounds(120, 150, 105, 60);
        botaoFuncionario.setBackground(new Color(255, 102, 0));
        botaoFuncionario.setForeground(Color.white);
        botaoFuncionario.setFont(new Font("Cascadian", Font.BOLD, 16));
        botaoFuncionario.setFocusPainted(false);
        botaoFuncionario.setBorder(new LineBorder(new Color(235, 94, 0), 2));
        add(botaoFuncionario);

        botaoUsuario = new JButton("Usuário");
        botaoUsuario.setBounds(268, 150, 105, 60);
        botaoUsuario.setBackground(new Color(255, 102, 0));
        botaoUsuario.setForeground(Color.white);
        botaoUsuario.setFont(new Font("Cascadian", Font.BOLD, 16));
        botaoUsuario.setFocusPainted(false);
        botaoUsuario.setBorder(new LineBorder(new Color(235, 94, 0), 2));
        add(botaoUsuario);
    }

    private void adicionarAcoesBotoes() {
        botaoFuncionario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                encaminharFuncionario();
            }
        });

        botaoFuncionario.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                botaoFuncionario.setBorder(new LineBorder(new Color(255, 229, 204), 2));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botaoFuncionario.setBorder(new LineBorder(new Color(235, 94, 0), 2));
            }
        });

        botaoUsuario.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                encaminharUsuario();
            }
        });

        botaoUsuario.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                botaoUsuario.setBorder(new LineBorder(new Color(255, 229, 204), 2));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botaoUsuario.setBorder(new LineBorder(new Color(235, 94, 0), 2));
            }
        });
    }

    private void encaminharFuncionario() {
        InterfaceFuncionario novaJanela = new InterfaceFuncionario();
        novaJanela.setVisible(true);
        dispose();
    }

    private void encaminharUsuario() {
        InterfaceLoginUsuario novaJanela = new InterfaceLoginUsuario();
        novaJanela.setVisible(true);
        dispose();
    }
}
