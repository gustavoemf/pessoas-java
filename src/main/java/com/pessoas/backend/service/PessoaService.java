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

        for (PessoaEntity pessoaEntity : lista) listaDTO.add(this.toPessoaDTO(pessoaEntity));

        return listaDTO;
    }

    public PessoaDto update(Long id, PessoaDto dto) {
        Optional<PessoaEntity> optionalPessoa = repository.findById(id);

        if (optionalPessoa.isEmpty()) {
            throw new IllegalArgumentException("Pessoa com o ID " + id + " não encontrada.");
        }

        PessoaEntity pessoaEntity = optionalPessoa.get();

        pessoaEntity.setNome(dto.getNome());
        pessoaEntity.setIdade(dto.getIdade());

        return toPessoaDTO(repository.save(pessoaEntity));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private PessoaDto toPessoaDTO(PessoaEntity pessoa) {
        PessoaDto pessoaDTO = new PessoaDto();
        pessoaDTO.setId(pessoa.getId());
        pessoaDTO.setNome(pessoa.getNome());
        pessoaDTO.setIdade(pessoa.getIdade());
        return pessoaDTO;
    }

    private PessoaEntity toPessoa(PessoaDto pessoaDTO) {
        PessoaEntity pessoa = new PessoaEntity();
        pessoa.setId(pessoaDTO.getId());
        pessoa.setNome(pessoaDTO.getNome());
        pessoa.setIdade(pessoaDTO.getIdade());
        return pessoa;
    }
}
