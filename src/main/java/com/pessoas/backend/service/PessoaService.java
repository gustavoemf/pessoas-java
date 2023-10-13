package com.pessoas.backend.service;

import com.pessoas.backend.dto.PessoaDto;
import com.pessoas.backend.entity.PessoaEntity;
import com.pessoas.backend.repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {
    @Autowired
    private PessoaRepository repository;

    public PessoaDto create(PessoaDto dto) {
        return this.toPessoaDTO(repository.save(this.toPessoa(dto)));
    }

    public PessoaDto getById(Long id) {
        Optional<PessoaEntity> database = repository.findById(id);
        if (database.isEmpty()) {
            throw new IllegalArgumentException("ERROR");
        }
        return toPessoaDTO(database.get());
    }

    public List<PessoaDto> getAll() {
        List<PessoaEntity> lista = repository.findAll();
        List<PessoaDto> listaDTO = new ArrayList<>();

        for (PessoaEntity entity : lista) listaDTO.add(this.toPessoaDTO(entity));

        return listaDTO;
    }

    public PessoaDto update(Long id, PessoaDto dto) {
        Optional<PessoaEntity> optional = repository.findById(id);

        if (optional.isEmpty()) {
            throw new IllegalArgumentException("Pessoa com o ID " + id + " não encontrada.");
        }

        PessoaEntity entity = optional.get();

        entity.setNome(dto.getNome());
        entity.setIdade(dto.getIdade());

        return toPessoaDTO(repository.save(entity));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private PessoaDto toPessoaDTO(PessoaEntity entity) {
        PessoaDto dto = new PessoaDto();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setIdade(entity.getIdade());
        return dto;
    }

    private PessoaEntity toPessoa(PessoaDto dto) {
        PessoaEntity entity = new PessoaEntity();
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setIdade(dto.getIdade());
        return entity;
    }
}
