package com.prova.prova.service;

import com.prova.prova.model.Curso;
import com.prova.prova.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {
    @Autowired
    private CursoRepository cursoRepository;

    public CursoService(CursoRepository CursoRepository) {
        this.cursoRepository = CursoRepository;
    }

    // Criar Curso
    public Curso criarCurso(Curso curso) {
        return cursoRepository.save(curso);
    }
    // Buscar Cursos
    public List<Curso> listarCursos(){
        return cursoRepository.findAll();
    }
    // Buscar Curso
    public Optional<Curso> buscarPorId(Long id){
        return cursoRepository.findById(id);
    }

    public Curso atualizarCurso (Long id, Curso curso){
        cursoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Curso não encontrado"));
        return cursoRepository.save(curso);
    }

    public void delete(Long id){
        cursoRepository.deleteById(id);
    }
}
