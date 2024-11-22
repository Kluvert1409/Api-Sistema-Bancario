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

    private static InterfacePrimaria janela = new InterfacePrimaria();

    public static void main(String[] args) {
        SpringApplication.run(SistemabancarioApplication.class, args);

            janela.setVisible(true);
    }
}