package com.api.mensalistas.controllers;

import com.api.mensalistas.dtos.PagamentoDto;
import com.api.mensalistas.models.JogadorModel;
import com.api.mensalistas.models.PagamentoModel;
import com.api.mensalistas.repositories.JogadorRepository;
import com.api.mensalistas.services.JogadorService;
import com.api.mensalistas.services.PagamentoService;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/Pagamentos")

public class PagamentoController {

    final JogadorService jogadorService;
    final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService, JogadorService jogadorService) {
        this.jogadorService = jogadorService;
        this.pagamentoService = pagamentoService;
    }



    @PostMapping("/PagarJogador")
    public ResponseEntity<Object> savePagamento(@RequestParam("nomeJogador") String nomeJogador, @RequestBody @Valid PagamentoDto pagamentoDto) {
        JogadorModel jogador = jogadorService.findByNome(nomeJogador);
        if (jogador != null) {
            PagamentoModel pagamentoModel = new PagamentoModel();
            BeanUtils.copyProperties(pagamentoDto, pagamentoModel);
            pagamentoModel.setJogador(jogador);

            PagamentoModel savedPagamento = pagamentoService.save(pagamentoModel);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPagamento);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Jogador not found.");
        }   
    }



     @PostMapping
     public ResponseEntity<Object> savePagamento(@RequestBody @Valid PagamentoDto pagamentoDto){
         var pagamentoModel = new PagamentoModel();
         BeanUtils.copyProperties(pagamentoDto, pagamentoModel);
         return ResponseEntity.status(HttpStatus.CREATED).body(pagamentoService.save(pagamentoModel));
     }


    @GetMapping
    public ResponseEntity<Page<PagamentoModel>> getAllPagamentos(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(pagamentoService.findAll(pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getOnePagamento(@PathVariable(value = "id") UUID id){
        Optional<PagamentoModel> pagamentoModelOptional = pagamentoService.findById(id);
        if (!pagamentoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pagamento not found.");
        }
        return ResponseEntity.status(HttpStatus.OK).body(pagamentoModelOptional.get());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deletePagamento(@PathVariable(value = "id") UUID id){
        Optional<PagamentoModel> pagamentoModelOptional = pagamentoService.findById(id);
        if (!pagamentoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pagamento not found.");
        }
        pagamentoService.delete(pagamentoModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Pagamento deletado com sucesso.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updatePagamento(@PathVariable(value = "id") UUID id,
                                                    @RequestBody @Valid PagamentoDto pagamentoDto){
        Optional<PagamentoModel> pagamentoModelOptional = pagamentoService.findById(id);
        if (!pagamentoModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pagamento not found.");
        }
        var pagamentoModel = new PagamentoModel();
        BeanUtils.copyProperties(pagamentoDto, pagamentoModel);
        pagamentoModel.setId(pagamentoModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(pagamentoService.save(pagamentoModel));
    }
}
