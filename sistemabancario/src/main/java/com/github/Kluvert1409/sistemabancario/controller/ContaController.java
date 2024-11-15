package com.github.Kluvert1409.sistemabancario.controller;

import com.github.Kluvert1409.sistemabancario.model.Conta;
import com.github.Kluvert1409.sistemabancario.model.ContaCorrente;
import com.github.Kluvert1409.sistemabancario.model.ContaEspecial;
import com.github.Kluvert1409.sistemabancario.model.ContaPoupanca;
import com.github.Kluvert1409.sistemabancario.repository.ContaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/criarConta/{tipoConta}/{nomeConta}/{valorComplemento}")
    public ResponseEntity<String> criarConta(@PathVariable String tipoConta, @PathVariable String nomeConta, @PathVariable double valorComplemento
    ) throws Exception {
        Conta conta;

        switch (tipoConta) {
            case "ContaCorrente":
                conta = new ContaCorrente(nomeConta, "Conta Corrente", valorComplemento);
                break;
            case "ContaPoupanca":
                conta = new ContaPoupanca(nomeConta, "Conta Poupanca", valorComplemento);
                break;
            case "ContaEspecial":
                conta = new ContaEspecial(nomeConta, "Conta Especial", valorComplemento);
                break;
            default:
                throw new Exception("Tipo de conta inválido: " + tipoConta);
        }
        contaRepository.save(conta);
        return ResponseEntity.ok("Conta criada com sucesso! ID da conta: " + conta.getNumeroConta());
    }


    @DeleteMapping("/apagarConta/{id}")
    public ResponseEntity<String> apagarConta(@PathVariable("id") int id) {
        Optional<Conta> contaOptional = contaRepository.findById(id);

        if (!contaOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conta não encontrada");
        }
        contaRepository.deleteById(id);
        return ResponseEntity.status(HttpStatus.OK).body("Conta apagada com sucesso");
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
    public ResponseEntity<String> sacar(@PathVariable("id") int id, @PathVariable("valorSaque") double valorSaque) {
        Optional<Conta> contaOptional = contaRepository.findById(id);

        if (!contaOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Conta não encontrada");
        }

        Conta contaExistente = contaOptional.get();

        try {
            contaExistente.setSacar(valorSaque);
            contaRepository.save(contaExistente);
            return ResponseEntity.ok("Saque realizado com sucesso");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro ao processar o saque");
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

    @GetMapping("/retornarDadosSimples/{id}")
    public String retornarDadosSimples(@PathVariable("id") int id) {
        Optional<Conta> contaOptional = contaRepository.findById(id);

        if (contaOptional.isPresent()) {
            Conta conta = contaOptional.get();
            return conta.getRetornoSimples();
        } else {
            return "Conta não encontrada";
        }
    }
}