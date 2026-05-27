package com.duoc.mscategoria.controller;


import com.duoc.mscategoria.entity.Categoria;
import com.duoc.mscategoria.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vf1/categorias")
public class CategoriaController {

    @Autowired
    private CategoriaRepository repository;

    @GetMapping
    public List<Categoria> listarCategorias() {
        return repository.findAll();
    }

    @PostMapping
    public ResponseEntity<Categoria> crearCategoria(@RequestBody Categoria categoria) {
        Categoria nueva = repository.save(categoria);
        return new ResponseEntity<>(nueva, HttpStatus.CREATED);
    }


}
