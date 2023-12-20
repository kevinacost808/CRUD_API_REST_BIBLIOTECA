package com.kevin.apiRestBibliotecaLibrosBidereccional.repositorio;

import com.kevin.apiRestBibliotecaLibrosBidereccional.entidades.Biblioteca;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BibliotecaRepositorio extends JpaRepository<Biblioteca, Integer> {
}
