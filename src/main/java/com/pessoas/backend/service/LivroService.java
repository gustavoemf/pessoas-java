package com.pessoas.backend.service;

import com.pessoas.backend.dto.LivroDto;
import com.pessoas.backend.entity.LivroEntity;
import com.pessoas.backend.repository.LivroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LivroService {
    @Autowired
    private LivroRepository repository;

    public LivroDto create(LivroDto dto) {
        return this.toDto(repository.save(this.toEntity(dto)));
    }

    public LivroDto getById(Long id) {
        Optional<LivroEntity> database = repository.findById(id);
        if (database.isEmpty()) {
            throw new IllegalArgumentException("ERROR");
        }
        return toDto(database.get());
    }

    public List<LivroDto> getAll() {
        List<LivroEntity> lista = repository.findAll();
        List<LivroDto> listaDTO = new ArrayList<>();

        for (LivroEntity entity : lista) listaDTO.add(this.toDto(entity));

        return listaDTO;
    }

    public LivroDto update(Long id, LivroDto dto) {
        Optional<LivroEntity> optional = repository.findById(id);

        if (optional.isEmpty()) {
            throw new IllegalArgumentException("Livro com o ID " + id + " não encontrado.");
        }

        LivroEntity entity = optional.get();

        entity.setTitulo(dto.getTitulo());
        entity.setAutor(dto.getAutor());

        return toDto(repository.save(entity));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private LivroDto toDto(LivroEntity entity) {
        LivroDto dto = new LivroDto();
        dto.setId(entity.getId());
        dto.setTitulo(entity.getTitulo());
        dto.setAutor(entity.getAutor());
        return dto;
    }

    private LivroEntity toEntity(LivroDto dto) {
        LivroEntity entity = new LivroEntity();
        entity.setId(dto.getId());
        entity.setTitulo(dto.getTitulo());
        entity.setAutor(dto.getAutor());
        return entity;
    }
}
