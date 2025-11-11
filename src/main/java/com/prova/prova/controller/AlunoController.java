package com.prova.prova.controller;


import com.prova.prova.model.Aluno;
import com.prova.prova.service.AlunoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

    private final AlunoService alunoService;

    public AlunoController(AlunoService alunoService) {
        this.alunoService = alunoService;
    }

    @Operation(summary = "Lista alunos", description = "Retorna todos os alunos presentes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alunos retornados com sucesso"),
    })
    @GetMapping
    public ResponseEntity<List<Aluno>> listarAlunos() {
        List<Aluno> Alunos = alunoService.listarAlunos();
        return ResponseEntity.ok(Alunos);
    }

    @Operation(summary = "Busca por ID", description = "Retorna o aluno especificado buscando por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aluno retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Aluno não encontrado"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> listarAlunoId(@PathVariable Long id) {
        Optional<Aluno> opt = alunoService.buscarPorId(id);
        if (opt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuário não encontrado");
        }
        return ResponseEntity.ok(opt.get());
    }

    @Operation(summary = "Cria um novo aluno", description = "Cria o aluno com os parametros passados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Aluno criado com sucesso"),
    })
    @PostMapping
    public ResponseEntity<Aluno> criarAluno(@RequestBody Aluno aluno) {
        Aluno novoAluno = alunoService.criarAluno(aluno);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAluno);
    }

    @Operation(summary = "Atualiza um aluno existente", description = "Substitui os dados antigos do aluno pelos novos dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Aluno atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Aluno não encontrado"),
    })
    @PutMapping("/{id}")
    public Aluno atualizarAluno(@PathVariable Long id, @RequestBody Aluno aluno) {
        return alunoService.atualizarAluno(id, aluno);
    }

    @Operation(summary = "Deleta um aluno existente", description = "Deleta o aluno buscando pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Aluno deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Aluno não encontrado"),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAluno(@PathVariable Long id) {
        Optional<Aluno> opt = alunoService.buscarPorId(id);
        if (opt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuário não encontrado");
        }

        alunoService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
