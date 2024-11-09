package com.github.Kluvert1409.sistemabancario.controller;

import com.github.Kluvert1409.sistemabancario.model.Conta;
import com.github.Kluvert1409.sistemabancario.model.ContaCorrente;
import com.github.Kluvert1409.sistemabancario.model.ContaEspecial;
import com.github.Kluvert1409.sistemabancario.model.ContaPoupanca;
import com.github.Kluvert1409.sistemabancario.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("conta")
public class ContaController {

    @Autowired
    private final ContaRepository contaRepository;

    public ContaController(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    @PostMapping("/criarConta/{tipoConta}/{nomeConta}")
    public ResponseEntity<String> criarConta(@PathVariable String tipoConta, @PathVariable String nomeConta) throws Exception {
        Conta conta;

        switch (tipoConta) {
            case "ContaCorrente":
                conta = new ContaCorrente(nomeConta, "Conta Corrente", 1.5);
                break;
            case "ContaPoupanca":
                conta = new ContaPoupanca(nomeConta, "Conta Poupanca", 0.05);
                break;
            case "ContaEspecial":
                conta = new ContaEspecial(nomeConta, "Conta Especial", 100);
                break;
            default:
                throw new Exception("Tipo de conta inválido: " + tipoConta);
        }

        contaRepository.save(conta);
        return ResponseEntity.ok("Conta criada com sucesso! ID da conta: " + conta.getNumeroConta());
    }


    @DeleteMapping("/apagarConta/{id}")
    public void apagarConta(@PathVariable("id") int id) {
            contaRepository.deleteById(id);
    }

    @PutMapping("/atualizarConta/{id}")
    public void atualizarConta(@PathVariable("id") int id, @RequestParam("nomeConta") String nomeConta) {
        Optional<Conta> contaOptional = contaRepository.findById(id);
        if (contaOptional.isPresent()) {
            Conta contaExistente = contaOptional.get();

                contaExistente.setNomeConta(nomeConta);
                contaRepository.save(contaExistente);
        }
    }

    @PutMapping("/depositar/{id}/{valorDeposito}")
    public void depositar(@PathVariable("id") int id, @PathVariable("valorDeposito") double valorDeposito) {
        Optional<Conta> contaOptional = contaRepository.findById(id);
        if (contaOptional.isPresent()) {
            Conta contaExistente = contaOptional.get();

            try {
                contaExistente.setDepositar(valorDeposito);
                contaRepository.save(contaExistente);
            } catch (Exception e) {
            }
        }
    }

    @PutMapping("/sacar/{id}/{valorSaque}")
    public void sacar(@PathVariable("id") int id, @PathVariable("valorSaque") double valorSaque) {
        Optional<Conta> contaOptional = contaRepository.findById(id);
        if (contaOptional.isPresent()) {
            Conta contaExistente = contaOptional.get();

            try {
                contaExistente.setSacar(valorSaque);
                contaRepository.save(contaExistente);
            } catch (Exception e) {
            }
        }
    }


    @GetMapping("/retornarDados/{id}")
    public String retornarDados(@PathVariable("id") int id) {
        Optional<Conta> contaOptional = contaRepository.findById(id);

        if (contaOptional.isPresent()) {
            Conta conta = contaOptional.get();
            return conta.getRetorno();
        } else {
            return "Conta não encontrada";
        }
    }
}