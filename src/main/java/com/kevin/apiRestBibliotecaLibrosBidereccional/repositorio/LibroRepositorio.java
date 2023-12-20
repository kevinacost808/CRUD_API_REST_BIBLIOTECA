package com.kevin.apiRestBibliotecaLibrosBidereccional.repositorio;

import com.kevin.apiRestBibliotecaLibrosBidereccional.entidades.Libro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibroRepositorio extends JpaRepository<Libro, Integer> {
}
