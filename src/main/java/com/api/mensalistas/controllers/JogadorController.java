package com.api.mensalistas.controllers;

import com.api.mensalistas.dtos.JogadorDto;
import com.api.mensalistas.models.JogadorModel;
import com.api.mensalistas.services.ErrorResponse;
import com.api.mensalistas.services.JogadorService;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;
import java.util.UUID;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/Jogadores")
public class JogadorController {

    final JogadorService jogadorService;

    public JogadorController(JogadorService jogadorService) {
        this.jogadorService = jogadorService;
    }

    @PostMapping
    public ResponseEntity<Object> saveJogador(@RequestBody @Valid JogadorDto jogadorDto){
        if(jogadorService.existsByNome(jogadorDto.getNome())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Esse nome já existe!");
        }
        if(jogadorService.existsByEmail(jogadorDto.getEmail())){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflict: Esse email já está em uso!");
        }
        var jogadorModel = new JogadorModel();
        BeanUtils.copyProperties(jogadorDto, jogadorModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(jogadorService.save(jogadorModel));
    }

    @GetMapping
    public ResponseEntity<Page<JogadorModel>> getAllJogarores(@PageableDefault(page = 0, size = 10, sort = "id", direction = Sort.Direction.ASC) Pageable pageable){
        return ResponseEntity.status(HttpStatus.OK).body(jogadorService.findAll(pageable));
    }
  
    @GetMapping("/{id}")
    public ResponseEntity<Object> getOneJogador(@PathVariable(value = "id") UUID id) {
        Optional<JogadorModel> jogadorModelOptional = jogadorService.findById(id);
        if (!jogadorModelOptional.isPresent()) {
            ErrorResponse errorResponse = new ErrorResponse("Jogador not found.");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
        }
        return ResponseEntity.status(HttpStatus.OK).body(jogadorModelOptional.get());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteJogador(@PathVariable(value = "id") UUID id){
        Optional<JogadorModel> jogadorModelOptional = jogadorService.findById(id);
        if (!jogadorModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Jogador not found.");
        }
        jogadorService.delete(jogadorModelOptional.get());
        return ResponseEntity.status(HttpStatus.OK).body("Jogador deletado com sucesso.");
    }

    @PutMapping("/{id}")
    public ResponseEntity<Object> updateJogador(@PathVariable(value = "id") UUID id,
                                                    @RequestBody @Valid JogadorDto jogadorDto){
        Optional<JogadorModel> jogadorModelOptional = jogadorService.findById(id);
        if (!jogadorModelOptional.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Jogador not found.");
        }
        var jogadorModel = new JogadorModel();
        BeanUtils.copyProperties(jogadorDto, jogadorModel);
        jogadorModel.setId(jogadorModelOptional.get().getId());
        return ResponseEntity.status(HttpStatus.OK).body(jogadorService.save(jogadorModel));
    }
}
