package com.github.Kluvert1409.sistemabancario;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.github.Kluvert1409.sistemabancario.interfacesGUI.InterfacePrimaria;
import java.awt.GraphicsEnvironment;

/**
 * @author Kluvert
 */
@SpringBootApplication
public class SistemabancarioApplication {

    public static void main(String[] args) {
        SpringApplication.run(SistemabancarioApplication.class, args);

        if (!GraphicsEnvironment.isHeadless()) {
            InterfacePrimaria janela = new InterfacePrimaria();
            janela.setVisible(true);
        } else {
            System.out.println("Modo headless detectado. Interface gráfica não será inicializada.");
        }
    }
}