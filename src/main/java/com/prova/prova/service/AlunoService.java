package com.prova.prova.service;


import com.prova.prova.model.Aluno;
import com.prova.prova.repository.AlunoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class AlunoService {
    @Autowired
    private AlunoRepository alunoRepository;

    public AlunoService(AlunoRepository alunoRepository) {
        this.alunoRepository = alunoRepository;
    }

    // Criar Aluno
    public Aluno criarAluno(Aluno aluno) {
        return alunoRepository.save(aluno);
    }
    // Buscar Alunos
    public List<Aluno> listarAlunos(){
        return alunoRepository.findAll();
    }
    // Buscar Aluno
    public Optional<Aluno> buscarPorId(Long id){
        return alunoRepository.findById(id);
    }

    public Aluno atualizarAluno (Long id, Aluno aluno){
        alunoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Aluno não encontrado"));
        return alunoRepository.save(aluno);
    }

    public void delete(Long id){
        alunoRepository.deleteById(id);
    }

}
