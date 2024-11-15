package com.github.Kluvert1409.sistemabancario.dataLoader;

import com.github.Kluvert1409.sistemabancario.model.Conta;
import com.github.Kluvert1409.sistemabancario.model.ContaCorrente;
import com.github.Kluvert1409.sistemabancario.model.ContaEspecial;
import com.github.Kluvert1409.sistemabancario.model.ContaPoupanca;
import com.github.Kluvert1409.sistemabancario.repository.ContaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final ContaRepository contaRepository;

    public DataLoader(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (contaRepository.count() == 0) {
            Conta conta1 = new ContaCorrente("Kaik", "Conta Corrente", 3000.00, 1.5);
            Conta conta2 = new ContaCorrente("Mariana", "Conta Corrente", 4000.00, 1.5);
            Conta conta3 = new ContaEspecial("Lucas", "Conta Especial", 1500.00, 100);
            Conta conta4 = new ContaPoupanca("Rafael", "Conta Poupanca", 1000.00, 0.05);
            Conta conta5 = new ContaEspecial("João", "Conta Especial", 500.00, 100);

            contaRepository.save(conta1);
            contaRepository.save(conta2);
            contaRepository.save(conta3);
            contaRepository.save(conta4);
            contaRepository.save(conta5);
        }
    }
}
