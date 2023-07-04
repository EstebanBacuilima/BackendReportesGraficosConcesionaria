package com.edu.proyect.Facturacion.controllers;

import com.edu.proyect.Facturacion.models.Categoria;
import com.edu.proyect.Facturacion.service.CategoriaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class CategoriaController {

    @Autowired
    private CategoriaService categoriaService;

    @GetMapping("/categoria/list")
    public ResponseEntity<List<Categoria>> listALl(){
        try {
            return new ResponseEntity<>(categoriaService.findByAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/categoria/listP")
    public ResponseEntity<Page<Categoria>> listAllCategoriaPage(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int size, @RequestParam(defaultValue = "nombreCategoria") String order, @RequestParam(defaultValue = "false") boolean asc){
        try {
            return new ResponseEntity<>(categoriaService.findByAll(PageRequest.of(page, size, Sort.by(order).descending())), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/categoria/findbyId/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id){
        try {
            Categoria categoria = categoriaService.findById(id);
            if(categoria != null){
                return new ResponseEntity<>(categoria, HttpStatus.OK);
            }
            return new ResponseEntity<>("NO ENCONTRADA",HttpStatus.NOT_FOUND);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/categoria/save")
    public ResponseEntity<Categoria> saveArea(@RequestBody Categoria categoria){
        try {
            return new ResponseEntity<>(categoriaService.save(categoria), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/categoria/actualizar/{id}")
    public ResponseEntity<Categoria> actualizarArea(@PathVariable Integer id, @RequestBody Categoria categoria) {
        try {
            if (categoriaService.findById(id) == null) {
                return ResponseEntity.notFound().build();
            }
            categoria.setNombreCategoria(categoria.getNombreCategoria());
            categoria.setEstado(categoria.getEstado());
            return new ResponseEntity<>(categoriaService.save(categoria), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
