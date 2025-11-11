package com.prova.prova.controller;

import com.prova.prova.model.Curso;
import com.prova.prova.service.CursoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    private final CursoService cursoService;


    public CursoController(CursoService cursoService) {
        this.cursoService = cursoService;
    }

    @Operation(summary = "Lista cursos", description = "Retorna todos os cursos presentes")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Cursos retornados com sucesso"),
    })
    @GetMapping
    public ResponseEntity<List<Curso>> listarCurso() {
        List<Curso> cursos = cursoService.listarCursos();
        return ResponseEntity.ok(cursos);
    }
    @Operation(summary = "Busca por ID", description = "Retorna o curso especificado buscando por ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Curso retornado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Curso não encontrado"),
    })
    @GetMapping("/{id}")
    public ResponseEntity<?> buscarCursoPorId(@PathVariable Long id) {
        Optional<Curso> opt = cursoService.buscarPorId(id);
        if (opt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuário não encontrado");
        }
        return ResponseEntity.ok(opt.get());
    }
    @Operation(summary = "Cria um novo curso", description = "Cria o curso com os parametros passados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Curso criado com sucesso"),
    })
    @PostMapping
    public ResponseEntity<Curso> criarCurso(@RequestBody Curso curso) {
        Curso novoCurso = cursoService.criarCurso(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoCurso);
    }

    @Operation(summary = "Atualiza um curso existente", description = "Substitui os dados antigos do curso pelos novos dados")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Curso atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Curso não encontrado"),
    })
    @PutMapping("/{id}")
    public Curso atualizarCurso(@PathVariable Long id, @RequestBody Curso curso) {
        return cursoService.atualizarCurso(id, curso);
    }
    
    @Operation(summary = "Deleta um curso existente", description = "Deleta o curso buscando pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "202", description = "Curso deletado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Curso não encontrado"),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCurso(@PathVariable Long id) {
        Optional<Curso> opt = cursoService.buscarPorId(id);
        if (opt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuário não encontrado");
        }

        cursoService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    
}
