package com.github.Kluvert1409.sistemabancario.model;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class InterfaceFuncionario extends JFrame {

    private JLabel texto, criarContaLabel, deletarContaLabel, atualizarContaLabel;
    private JPanel panelBackGround;
    private JButton botaoCriarConta, botaoDeletarConta, botaoAtualizarConta, botaoVoltar;
    private ImageIcon imagemCriar, imagemDeletar, imagemAtualizar, imagemVoltar;

    public InterfaceFuncionario() {
        configurarJanela();
        adicionarLabels();
        adicionarBotoes();
        panelBackground();
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

    private void panelBackground() {
        panelBackGround = new JPanel();
        panelBackGround.setBackground(new Color(32, 32, 32));
        panelBackGround.setBounds(0, 0, 520, 600);
        add(panelBackGround);
    }

    private void adicionarLabels() {

        texto = new JLabel("Qual operação você gostaria de realizar?");
        texto.setBounds(50, 60, 500, 30);
        texto.setFont(new Font("Cascadian", Font.BOLD, 22));
        texto.setForeground(new Color(255, 102, 0));
        add(texto);

        criarContaLabel = new JLabel("Criar Conta");
        criarContaLabel.setBounds(50, 215, 150, 30);
        criarContaLabel.setFont(new Font("Cascadian", Font.BOLD, 18));
        criarContaLabel.setForeground(new Color(255, 102, 0));
        add(criarContaLabel);

        deletarContaLabel = new JLabel("Apagar Conta");
        deletarContaLabel.setBounds(190, 215, 150, 30);
        deletarContaLabel.setFont(new Font("Cascadian", Font.BOLD, 18));
        deletarContaLabel.setForeground(new Color(255, 102, 0));
        add(deletarContaLabel);

        atualizarContaLabel = new JLabel("Atualizar Conta");
        atualizarContaLabel.setBounds(335, 215, 150, 30);
        atualizarContaLabel.setFont(new Font("Cascadian", Font.BOLD, 18));
        atualizarContaLabel.setForeground(new Color(255, 102, 0));
        add(atualizarContaLabel);
    }

    private void adicionarBotoes() {
        botaoCriarConta = new JButton();
        botaoCriarConta.setBounds(45, 150, 105, 60);
        botaoCriarConta.setBackground(new Color(255, 102, 0));
        botaoCriarConta.setForeground(Color.white);
        botaoCriarConta.setFocusPainted(false);
        imagemCriar = new ImageIcon(getClass().getResource("/imagens/add_64px.png"));
        botaoCriarConta.setIcon(imagemCriar);
        botaoCriarConta.setBorder(new LineBorder(new Color(235, 94, 0), 2));
        add(botaoCriarConta);

        botaoDeletarConta = new JButton();
        botaoDeletarConta.setBounds(195, 150, 105, 60);
        botaoDeletarConta.setBackground(new Color(255, 102, 0));
        botaoDeletarConta.setForeground(Color.white);
        botaoDeletarConta.setFocusPainted(false);
        imagemDeletar = new ImageIcon(getClass().getResource("/imagens/trash_64px.png"));
        botaoDeletarConta.setIcon(imagemDeletar);
        botaoDeletarConta.setBorder(new LineBorder(new Color(235, 94, 0), 2));
        add(botaoDeletarConta);

        botaoAtualizarConta = new JButton();
        botaoAtualizarConta.setBounds(345, 150, 105, 60);
        botaoAtualizarConta.setBackground(new Color(255, 102, 0));
        botaoAtualizarConta.setForeground(Color.white);
        botaoAtualizarConta.setFocusPainted(false);
        imagemAtualizar = new ImageIcon(getClass().getResource("/imagens/synchronize_100px.png"));
        botaoAtualizarConta.setIcon(imagemAtualizar);
        botaoAtualizarConta.setBorder(new LineBorder(new Color(235, 94, 0), 2));
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
        botaoCriarConta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                encaminharCriarConta();
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

        botaoDeletarConta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                encaminharDeletarConta();
            }
        });

        botaoDeletarConta.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                botaoDeletarConta.setBorder(new LineBorder(new Color(255, 229, 204), 2));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botaoDeletarConta.setBorder(new LineBorder(new Color(235, 94, 0), 2));
            }
        });

        botaoAtualizarConta.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                encaminharAtualizarConta();
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

    private void encaminharCriarConta() {
        InterfaceCriarConta novaJanela = new InterfaceCriarConta();
        novaJanela.setVisible(true);
        dispose();
    }

    private void encaminharDeletarConta() {
        InterfaceDeletarConta novaJanela = new InterfaceDeletarConta();
        novaJanela.setVisible(true);
        dispose();
    }

    private void encaminharAtualizarConta() {
        InterfaceAtualizar novaJanela = new InterfaceAtualizar();
        novaJanela.setVisible(true);
        dispose();
    }

    private void retornar() {
        InterfacePrimaria novaJanela = new InterfacePrimaria();
        novaJanela.setVisible(true);
        dispose();
    }
}