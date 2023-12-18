package com.kevin.apiRestBibliotecaLibrosBidereccional.entidades;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "biblioteca")
public class Biblioteca {
    @Id
    private int id;
    @NotNull
    private String nombre;

    @OneToMany(mappedBy = "biblioteca", cascade = CascadeType.ALL)
    private Set<Libro> libros = new HashSet<Libro>();

    public Biblioteca(int id, String nombre, Set<Libro> libros) {
        this.id = id;
        this.nombre = nombre;
        this.libros = libros;
    }

    public Biblioteca() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Set<Libro> getLibros() {
        return libros;
    }

    public void setLibros(Set<Libro> libros) {
        this.libros = libros;
        for(Libro libro: libros){
            libro.setBiblioteca(this);
        }
    }
}
