package com.pessoas.backend.controller;

import com.pessoas.backend.dto.PessoaDto;
import com.pessoas.backend.service.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pessoas")
@CrossOrigin(origins = "http://localhost:4200")
public class PessoaController {
    @Autowired
    private PessoaService service;

    @PostMapping("/")
    public PessoaDto create(@RequestBody PessoaDto dto) {
        return service.create(dto);
    }
    @GetMapping("/get/{id}")
    public PessoaDto getById(@PathVariable Long id) {
        return service.getById(id);
    }
    @GetMapping("/")
    public List<PessoaDto> getAll() {
        return service.getAll();
    }
    @PutMapping("/{id}")
    public PessoaDto update(@PathVariable Long id, @RequestBody PessoaDto dto) {
        return service.update(id, dto);
    }
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
