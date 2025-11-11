package com.prova.prova.model;

import com.prova.prova.utils.StatusAluno;
import jakarta.persistence.*;

import java.util.List;



@Entity
@Table(name = "alunos")
public class Aluno {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String matricula;

    private StatusAluno statusAluno;

    @ManyToOne
    private Curso curso;


    public Aluno(Long id, String nome, String matricula, StatusAluno statusAluno, Curso curso) {
        this.id = id;
        this.nome = nome;
        this.matricula = matricula;
        this.statusAluno = statusAluno;
        this.curso = curso;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public StatusAluno getStatusAluno() {
        return statusAluno;
    }

    public void setStatusAluno(StatusAluno statusAluno) {
        this.statusAluno = statusAluno;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
