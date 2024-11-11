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
    private JLabel nomeTopo, idTopo, valorLabel, saque, deposito, consulta, fotoUsuario;
    private JButton botaoDepositar, botaoSacar, botaoConsultar;
    private JPanel panelBackGround, panelSuperior;
    private JLayeredPane layeredPane;

    private int id;

    public InterfaceOperacoes(String nomeConta, int idConta) {
        this.id = idConta;
        configurarJanela();
        inicializarComponentes(nomeConta, idConta);
        adicionarAcoesBotoes();
        criarLayeredPane();
        criarPanelSuperior();
        criarPanelBackground();
    }

    private void configurarJanela() {
        setTitle("Operações da Conta");
        setSize(520, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setLayout(null);
    }

    private void criarLayeredPane() {
        layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 520, 600);
        add(layeredPane);
    }

    private void criarPanelSuperior() {
        panelSuperior = new JPanel();
        panelSuperior.setBackground(new Color(255, 102, 0));
        panelSuperior.setBounds(0, 0, 520, 100);
        panelSuperior.setVisible(true);
        layeredPane.add(panelSuperior, JLayeredPane.DEFAULT_LAYER);
    }

    private void criarPanelBackground() {
        panelBackGround = new JPanel();
        panelBackGround.setBackground(new Color(32, 32, 32));
        panelBackGround.setBounds(0, 0, 520, 600);
        layeredPane.add(panelBackGround, JLayeredPane.DEFAULT_LAYER);
    }

    private void inicializarComponentes(String nomeConta, int idConta) {

        fotoUsuario = new JLabel();
        fotoUsuario.setBounds(20, 0, 150, 100);
        fotoUsuario.setFont(new Font("Cascadian", Font.PLAIN, 22));
        fotoUsuario.setForeground(Color.white);
        ImageIcon imagemUser = new ImageIcon("C:\\Users\\Kluvert\\Documents\\Intellij repositório\\sistemabancario\\sistemabancario\\src\\main\\java\\com\\github\\Kluvert1409\\sistemabancario\\imagens\\user_60px.png");
        fotoUsuario.setIcon(imagemUser);
        add(fotoUsuario);

        nomeTopo = new JLabel("Olá, " + nomeConta);
        nomeTopo.setBounds(100, 45, 150, 30);
        nomeTopo.setFont(new Font("Cascadian", Font.BOLD, 22));
        nomeTopo.setForeground(Color.white);
        add(nomeTopo);

        idTopo = new JLabel("N° da Conta: " + idConta);
        idTopo.setBounds(395, 70, 150, 30);
        idTopo.setFont(new Font("Cascadian", Font.PLAIN, 16));
        idTopo.setForeground(Color.white);
        add(idTopo);

        valorLabel = new JLabel("Valor:");
        valorLabel.setBounds(50, 175, 150, 30);
        valorLabel.setFont(new Font("Cascadian", Font.BOLD, 18));
        valorLabel.setForeground(new Color(255, 102, 0));
        add(valorLabel);

        saque = new JLabel("Saque");
        saque.setBounds(70, 340, 150, 30);
        saque.setFont(new Font("Cascadian", Font.BOLD, 18));
        saque.setForeground(new Color(255, 102, 0));
        add(saque);

        deposito = new JLabel("Depósito");
        deposito.setBounds(210, 340, 150, 30);
        deposito.setFont(new Font("Cascadian", Font.BOLD, 18));
        deposito.setForeground(new Color(255, 102, 0));
        add(deposito);

        consulta = new JLabel("Consulta");
        consulta.setBounds(360, 340, 150, 30);
        consulta.setFont(new Font("Cascadian", Font.BOLD, 18));
        consulta.setForeground(new Color(255, 102, 0));
        add(consulta);

        valor = new JTextField();
        valor.setBounds(200, 175, 250, 30);
        valor.setFont(new Font("Cascadian", Font.PLAIN, 18));
        valor.setBackground(new Color(255, 255, 255));
        valor.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        add(valor);

        botaoSacar = new JButton();
        botaoSacar.setBounds(45, 275, 105, 60);
        botaoSacar.setBackground(new Color(255, 102, 0));
        botaoSacar.setForeground(Color.white);
        botaoSacar.setFocusPainted(false);
        ImageIcon imagemSaque = new ImageIcon("C:\\Users\\Kluvert\\Documents\\Intellij repositório\\sistemabancario\\sistemabancario\\src\\main\\java\\com\\github\\Kluvert1409\\sistemabancario\\imagens\\get_cash_80px.png");
        botaoSacar.setIcon(imagemSaque);
        add(botaoSacar);

        botaoDepositar = new JButton();
        botaoDepositar.setBounds(195, 275, 105, 60);
        botaoDepositar.setBackground(new Color(255, 102, 0));
        botaoDepositar.setForeground(Color.white);
        botaoDepositar.setFocusPainted(false);
        ImageIcon imagemDeposito = new ImageIcon("C:\\Users\\Kluvert\\Documents\\Intellij repositório\\sistemabancario\\sistemabancario\\src\\main\\java\\com\\github\\Kluvert1409\\sistemabancario\\imagens\\real_30px.png");
        botaoDepositar.setIcon(imagemDeposito);
        add(botaoDepositar);

        botaoConsultar = new JButton();
        botaoConsultar.setBounds(345, 275, 105, 60);
        botaoConsultar.setBackground(new Color(255, 102, 0));
        botaoConsultar.setForeground(Color.white);
        botaoConsultar.setFocusPainted(false);
        ImageIcon imagemConsultar = new ImageIcon("C:\\Users\\Kluvert\\Documents\\Intellij repositório\\sistemabancario\\sistemabancario\\src\\main\\java\\com\\github\\Kluvert1409\\sistemabancario\\imagens\\consultation_60px.png");
        botaoConsultar.setIcon(imagemConsultar);
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
        if (textoValor.isEmpty() || textoValor.equals("0")) {
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
