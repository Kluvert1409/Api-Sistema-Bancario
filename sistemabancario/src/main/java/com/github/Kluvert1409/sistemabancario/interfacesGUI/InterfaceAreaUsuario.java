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
import java.util.Scanner;

public class InterfaceAreaUsuario extends JFrame {

    private JTextField valor;
    private JLabel nomeTopo, idTopo, valorLabel, saque, deposito, consulta, fotoUsuario, reais, saldoAtual;
    private JButton botaoDepositar, botaoSacar, botaoConsultar, botaoMostrarsaldo;
    private JPanel panelBackGround, panelSuperior;
    private JLayeredPane layeredPane;
    private ImageIcon imagemSaque, imagemDeposito, imagemConsultar, imagemUser, imagemMostrarSaldo, imagemSaldo;
    private boolean saldoMostrado = false;

    private URL url;
    private HttpURLConnection conexaoHttp;
    private int idConta;
    private String nomeConta;

    public InterfaceAreaUsuario(String nomeConta, int idConta) {
        this.nomeConta = nomeConta;
        this.idConta = idConta;
        configurarJanela();
        inicializarComponentes(nomeConta, idConta);
        criarBotoes();
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
        imagemUser = new ImageIcon(getClass().getResource("/imagens/user_60px.png"));
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

        reais = new JLabel();
        reais.setBounds(50, 175, 150, 40);
        imagemSaldo = new ImageIcon(getClass().getResource("/imagens/real_30px.png"));
        reais.setIcon(imagemSaldo);
        add(reais);

        saldoAtual = new JLabel("****");
        saldoAtual.setBounds(110, 175, 150, 40);
        saldoAtual.setFont(new Font("Cascadian", Font.BOLD, 22));
        saldoAtual.setForeground(Color.white);
        add(saldoAtual);

        valorLabel = new JLabel("Valor:");
        valorLabel.setBounds(50, 275, 150, 30);
        valorLabel.setFont(new Font("Cascadian", Font.BOLD, 18));
        valorLabel.setForeground(new Color(255, 102, 0));
        add(valorLabel);

        saque = new JLabel("Saque");
        saque.setBounds(70, 440, 150, 30);
        saque.setFont(new Font("Cascadian", Font.BOLD, 18));
        saque.setForeground(new Color(255, 102, 0));
        add(saque);

        deposito = new JLabel("Depósito");
        deposito.setBounds(210, 440, 150, 30);
        deposito.setFont(new Font("Cascadian", Font.BOLD, 18));
        deposito.setForeground(new Color(255, 102, 0));
        add(deposito);

        consulta = new JLabel("Consulta");
        consulta.setBounds(360, 440, 150, 30);
        consulta.setFont(new Font("Cascadian", Font.BOLD, 18));
        consulta.setForeground(new Color(255, 102, 0));
        add(consulta);

        valor = new JTextField();
        valor.setBounds(200, 275, 250, 30);
        valor.setFont(new Font("Cascadian", Font.PLAIN, 18));
        valor.setBackground(new Color(255, 255, 255));
        valor.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200)));
        add(valor);
    }

    private void criarBotoes() {
        botaoSacar = new JButton();
        botaoSacar.setBounds(45, 375, 105, 60);
        botaoSacar.setBackground(new Color(255, 102, 0));
        botaoSacar.setForeground(Color.white);
        botaoSacar.setFocusPainted(false);
        imagemSaque = new ImageIcon(getClass().getResource("/imagens/get_cash_80px.png"));
        botaoSacar.setBorder(new LineBorder(new Color(235, 94, 0), 2));
        botaoSacar.setIcon(imagemSaque);
        add(botaoSacar);

        botaoMostrarsaldo = new JButton();
        botaoMostrarsaldo.setBorderPainted(false);
        botaoMostrarsaldo.setContentAreaFilled(false);
        botaoMostrarsaldo.setFocusPainted(false);
        botaoMostrarsaldo.setBounds(375, -15, 60, 80);
        imagemMostrarSaldo = new ImageIcon(getClass().getResource("/imagens/eyelashes_2d_50px.png"));
        botaoMostrarsaldo.setIcon(imagemMostrarSaldo);
        add(botaoMostrarsaldo);

        botaoDepositar = new JButton();
        botaoDepositar.setBounds(195, 375, 105, 60);
        botaoDepositar.setBackground(new Color(255, 102, 0));
        botaoDepositar.setForeground(Color.white);
        botaoDepositar.setFocusPainted(false);
        imagemDeposito = new ImageIcon(getClass().getResource("/imagens/exchange_30px.png"));
        botaoDepositar.setBorder(new LineBorder(new Color(235, 94, 0), 2));
        botaoDepositar.setIcon(imagemDeposito);
        add(botaoDepositar);

        botaoConsultar = new JButton();
        botaoConsultar.setBounds(345, 375, 105, 60);
        botaoConsultar.setBackground(new Color(255, 102, 0));
        botaoConsultar.setForeground(Color.white);
        botaoConsultar.setFocusPainted(false);
        imagemConsultar = new ImageIcon(getClass().getResource("/imagens/consultation_60px.png"));
        botaoConsultar.setBorder(new LineBorder(new Color(235, 94, 0), 2));
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

        botaoDepositar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                botaoDepositar.setBorder(new LineBorder(new Color(255, 229, 204), 2));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botaoDepositar.setBorder(new LineBorder(new Color(235, 94, 0), 2));
            }
        });

        botaoMostrarsaldo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostraSaldo();
            }
        });

        botaoSacar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                realizarSaque();
            }
        });

        botaoSacar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                botaoSacar.setBorder(new LineBorder(new Color(255, 229, 204), 2));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botaoSacar.setBorder(new LineBorder(new Color(235, 94, 0), 2));
            }
        });

        botaoConsultar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                consultarConta();
            }
        });

        botaoConsultar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                botaoConsultar.setBorder(new LineBorder(new Color(255, 229, 204), 2));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                botaoConsultar.setBorder(new LineBorder(new Color(235, 94, 0), 2));
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
                url = new URL("http://localhost:8080/conta/depositar/" + idConta + "/" + valor);
                conexaoHttp = (HttpURLConnection) url.openConnection();
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
                url = new URL("http://localhost:8080/conta/sacar/" + idConta + "/" + valor);
                conexaoHttp = (HttpURLConnection) url.openConnection();
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
            url = new URL("http://localhost:8080/conta/retornarDados/" + idConta);
            conexaoHttp = (HttpURLConnection) url.openConnection();
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

    private void mostraSaldo() {
        try {
            url = new URL("http://localhost:8080/conta/retornarDadosSimples/" + idConta);
            conexaoHttp = (HttpURLConnection) url.openConnection();
            conexaoHttp.setRequestMethod("GET");

            int resposta = conexaoHttp.getResponseCode();
            if (resposta == 200) {
                Scanner scanner = new Scanner(conexaoHttp.getInputStream());
                String dadosConta = scanner.useDelimiter("\\A").next();
                scanner.close();

                String[] partes = dadosConta.split(";");
                String saldo = partes[1];

                if (saldoMostrado == false) {
                    saldoAtual.setText(saldo);
                    saldoAtual.setBounds(110, 172, 150, 40);
                    botaoMostrarsaldo.setIcon(new ImageIcon(getClass().getResource("/imagens/eye_24px.png")));
                    saldoMostrado = true;
                } else {
                    saldoAtual.setText("****");
                    saldoAtual.setBounds(110, 175, 150, 40);
                    botaoMostrarsaldo.setIcon(new ImageIcon(getClass().getResource("/imagens/eyelashes_2d_50px.png")));
                    saldoMostrado = false;
                        }
            } else {
                JOptionPane.showMessageDialog(this, "Erro ao buscar saldo. Código: " + resposta, "Erro", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao mostrar saldo: " + e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }
}