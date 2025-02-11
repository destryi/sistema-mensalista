package com.api.mensalistas.services;

import com.api.mensalistas.models.PagamentoModel;
import com.api.mensalistas.repositories.PagamentoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;
import java.util.UUID;

@Service
public class PagamentoService {

    final PagamentoRepository pagamentoRepository;

    public PagamentoService(PagamentoRepository pagamentoRepository) {
        this.pagamentoRepository = pagamentoRepository;
    }

    @Transactional
    public PagamentoModel save(PagamentoModel pagamentoModel) {
        return pagamentoRepository.save(pagamentoModel);
    }

    public boolean existsByAno(short ano) {
        return pagamentoRepository.existsByAno(ano);
    }

    public boolean existsByMes(short mes) {
        return pagamentoRepository.existsByMes(mes);
    }

    public Page<PagamentoModel> findAll(Pageable pageable) {
        return pagamentoRepository.findAll(pageable);
    }

    public Optional<PagamentoModel> findById(UUID id) {
        return pagamentoRepository.findById(id);
    }

    @Transactional
    public void delete(PagamentoModel pagamentoModel) {
        pagamentoRepository.delete(pagamentoModel);
    }
}
