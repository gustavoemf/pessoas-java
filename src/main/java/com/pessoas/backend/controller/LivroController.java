package com.pessoas.backend.controller;

import com.pessoas.backend.dto.LivroDto;
import com.pessoas.backend.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/livros")
@CrossOrigin(origins = "http://localhost:4200")
public class LivroController {
    @Autowired
    private LivroService service;

    @PostMapping("/")
    public LivroDto create(@RequestBody LivroDto dto) {
        return service.create(dto);
    }
    @GetMapping("/get/{id}")
    public LivroDto getById(@PathVariable Long id) {
        return service.getById(id);
    }
    @GetMapping("/")
    public List<LivroDto> getAll() {
        return service.getAll();
    }
    @PutMapping("/{id}")
    public LivroDto update(@PathVariable Long id, @RequestBody LivroDto dto) {
        return service.update(id, dto);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
