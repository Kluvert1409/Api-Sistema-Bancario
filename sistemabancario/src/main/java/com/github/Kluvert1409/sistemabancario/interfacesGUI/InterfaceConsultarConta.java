package com.github.Kluvert1409.sistemabancario.interfacesGUI;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.HttpURLConnection;
import java.net.URL;

public class InterfaceConsultarConta extends JFrame {

    private JPanel panelBackGround;
    private JButton botaoVoltar;
    private ImageIcon imagemVoltar;
    private JTable tabelaContas;
    private JScrollPane scrollPane;

    public InterfaceConsultarConta() {
        configurarJanela();
        adicionarTabela();
        adicionarBotoes();
        panelBackground();
        adicionarAcoesBotoes();
        carregarDadosTabela();
    }

    private void configurarJanela() {
        setTitle("Sistema Bancário");
        setSize(655, 430);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
    }

    private void panelBackground() {
        panelBackGround = new JPanel();
        panelBackGround.setBackground(new Color(32, 32, 32));
        panelBackGround.setBounds(0, 0, 655, 430);
        add(panelBackGround);
    }

    private void adicionarTabela() {
        tabelaContas = new JTable();
        tabelaContas.setFillsViewportHeight(true);
        tabelaContas.setEnabled(false);
        tabelaContas.setFont(new Font("Arial", Font.PLAIN, 14));
        tabelaContas.setRowHeight(30);
        tabelaContas.setIntercellSpacing(new Dimension(0, 0));

        scrollPane = new JScrollPane(tabelaContas);
        scrollPane.setBounds(20, 50, 600, 175);
        add(scrollPane);
    }

    private void adicionarBotoes() {
        botaoVoltar = new JButton();
        botaoVoltar.setBounds(20, 305, 80, 35);
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

    private void carregarDadosTabela() {
        String[] colunas = {"N° da Conta", "Saldo", "Nome", "Tipo Conta", "Complemento"};
        DefaultTableModel model = new DefaultTableModel(colunas, 0);

        try {
            int id = 1;
            boolean dadosExistem = true;
            while (dadosExistem) {
                URL url = new URL("http://localhost:8080/conta/retornarDadosSimples/" + id);
                HttpURLConnection conexaoHttp = (HttpURLConnection) url.openConnection();
                conexaoHttp.setRequestMethod("GET");
                int status = conexaoHttp.getResponseCode();

                if (status == HttpURLConnection.HTTP_OK) {
                    try (var leitor = new java.io.BufferedReader(new java.io.InputStreamReader(conexaoHttp.getInputStream()))) {
                        String resposta = leitor.readLine();
                        if (resposta != null && !resposta.isEmpty() && !resposta.equals("Conta não encontrada")) {
                            String[] dadosConta = resposta.split(";");
                            model.addRow(dadosConta);
                        } else {
                            dadosExistem = false;
                        }
                    } catch (Exception e) {
                        break;
                    }
                } else {
                    dadosExistem = false;
                }
                id++;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar os dados: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }
        tabelaContas.setModel(model);
    }

    private void retornar() {
        InterfaceFuncionario novaJanela = new InterfaceFuncionario();
        novaJanela.setVisible(true);
        dispose();
    }
}