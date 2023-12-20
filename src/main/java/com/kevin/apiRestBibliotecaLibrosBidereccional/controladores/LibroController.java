package com.kevin.apiRestBibliotecaLibrosBidereccional.controladores;

import com.kevin.apiRestBibliotecaLibrosBidereccional.entidades.Biblioteca;
import com.kevin.apiRestBibliotecaLibrosBidereccional.repositorio.BibliotecaRepositorio;
import com.kevin.apiRestBibliotecaLibrosBidereccional.repositorio.LibroRepositorio;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.Optional;

@RestController
@RequestMapping(name = "/api/libros")
public class LibroController {
    @Autowired
    private BibliotecaRepositorio bibliotecaRepositorio;

    @Autowired
    private LibroRepositorio libroRepositorio;

    @GetMapping
    public ResponseEntity<Page<Biblioteca>> listarBiblioteca(Pageable pageable){
        return ResponseEntity.ok(bibliotecaRepositorio.findAll(pageable));
    }


    @PostMapping
    public ResponseEntity<Biblioteca> guardarBiblioteca(@Valid @RequestBody Biblioteca biblioteca){
        Biblioteca bibliotecaGuardar = bibliotecaRepositorio.save(biblioteca);
        URI ubicacion = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(bibliotecaGuardar.getId()).toUri();
        return ResponseEntity.created(ubicacion).body(bibliotecaGuardar);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Biblioteca> editarBiblioteca(@PathVariable int id, @Valid @RequestBody Biblioteca biblioteca){
        Optional<Biblioteca> bibliotecaOptional = bibliotecaRepositorio.findById(id);
        if (bibliotecaOptional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }

        biblioteca.setId(bibliotecaOptional.get().getId());
        bibliotecaRepositorio.save(biblioteca);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Biblioteca> eliminarBiblioteca(@PathVariable int id, @Valid @RequestBody Biblioteca biblioteca){
        Optional<Biblioteca> bibliotecaOptional = bibliotecaRepositorio.findById(id);
        if (bibliotecaOptional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }

        bibliotecaRepositorio.delete(bibliotecaOptional.get());

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Biblioteca> obtenerBibliotecaId(@PathVariable int id){
        Optional<Biblioteca> bibliotecaOptional = bibliotecaRepositorio.findById(id);
        if (bibliotecaOptional.isPresent()){
            return ResponseEntity.unprocessableEntity().build();
        }
        return ResponseEntity.ok(bibliotecaOptional.get());
    }

}
