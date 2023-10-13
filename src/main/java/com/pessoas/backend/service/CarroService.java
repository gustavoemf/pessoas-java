package com.pessoas.backend.service;

import com.pessoas.backend.dto.CarroDto;
import com.pessoas.backend.entity.CarroEntity;
import com.pessoas.backend.repository.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CarroService {
    @Autowired
    private CarroRepository repository;

    public CarroDto create(CarroDto dto) {
        return this.toDto(repository.save(this.toEntity(dto)));
    }

    public CarroDto getById(Long id) {
        Optional<CarroEntity> database = repository.findById(id);
        if (database.isEmpty()) {
            throw new IllegalArgumentException("ERROR");
        }
        return toDto(database.get());
    }

    public List<CarroDto> getAll() {
        List<CarroEntity> lista = repository.findAll();
        List<CarroDto> listaDTO = new ArrayList<>();

        for (CarroEntity entity : lista) listaDTO.add(this.toDto(entity));

        return listaDTO;
    }

    public CarroDto update(Long id, CarroDto dto) {
        Optional<CarroEntity> optional = repository.findById(id);

        if (optional.isEmpty()) {
            throw new IllegalArgumentException("Carro com o ID " + id + " não encontrado.");
        }

        CarroEntity entity = optional.get();

        entity.setNome(dto.getNome());
        entity.setAno(dto.getAno());

        return toDto(repository.save(entity));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private CarroDto toDto(CarroEntity entity) {
        CarroDto dto = new CarroDto();
        dto.setId(entity.getId());
        dto.setNome(entity.getNome());
        dto.setAno(entity.getAno());
        return dto;
    }

    private CarroEntity toEntity(CarroDto dto) {
        CarroEntity entity = new CarroEntity();
        entity.setId(dto.getId());
        entity.setNome(dto.getNome());
        entity.setAno(dto.getAno());
        return entity;
    }
}
