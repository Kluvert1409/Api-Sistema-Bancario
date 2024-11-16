package com.github.Kluvert1409.sistemabancario.interfacesGUI;

import javax.swing.*;
import java.awt.*;

public class InterfaceConsultarConta extends JFrame {

    private JPanel panelBackGround;

    public InterfaceConsultarConta() {
        configurarJanela();
        panelBackground();
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
}
