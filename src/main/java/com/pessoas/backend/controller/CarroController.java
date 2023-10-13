package com.pessoas.backend.controller;

import com.pessoas.backend.dto.CarroDto;
import com.pessoas.backend.service.CarroService;
import com.pessoas.backend.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/carros")
@CrossOrigin(origins = "http://localhost:4200")
public class CarroController {
    @Autowired
    private CarroService service;

    @PostMapping("/")
    public CarroDto create(@RequestBody CarroDto dto) {
        return service.create(dto);
    }
    @GetMapping("/get/{id}")
    public CarroDto getById(@PathVariable Long id) {
        return service.getById(id);
    }
    @GetMapping("/")
    public List<CarroDto> getAll() {
        return service.getAll();
    }
    @PutMapping("/{id}")
    public CarroDto update(@PathVariable Long id, @RequestBody CarroDto dto) {
        return service.update(id, dto);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
