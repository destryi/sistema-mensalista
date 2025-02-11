package com.api.mensalistas.services;

import com.api.mensalistas.models.JogadorModel;
import com.api.mensalistas.repositories.JogadorRepository;

import com.api.mensalistas.services.NotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class JogadorService {

    final JogadorRepository jogadorRepository;

    public JogadorService(JogadorRepository jogadorRepository) {
        this.jogadorRepository = jogadorRepository;
    }

    @Transactional
    public JogadorModel save(JogadorModel jogadorModel) {
        return jogadorRepository.save(jogadorModel);
    }

    public boolean existsByNome(String nome) {
        return jogadorRepository.existsByNome(nome);
    }

    public boolean existsByEmail(String email) {
        return jogadorRepository.existsByEmail(email);
    }

    public Page<JogadorModel> findAll(Pageable pageable) {
        return jogadorRepository.findAll(pageable);
    }

    public Optional<JogadorModel> findById(UUID id) {
        return jogadorRepository.findById(id);
    }

    public JogadorModel findByNome(String nome) {
        return jogadorRepository.findByNome(nome);
    }

    @Transactional
    public void delete(JogadorModel jogadorModel) {
        jogadorRepository.delete(jogadorModel);
    }
}
