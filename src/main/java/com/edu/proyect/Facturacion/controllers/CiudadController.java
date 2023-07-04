package com.edu.proyect.Facturacion.controllers;

import com.edu.proyect.Facturacion.Payloads.CiudadesDto;
import com.edu.proyect.Facturacion.models.Ciudad;
import com.edu.proyect.Facturacion.service.CiudadService;
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
public class CiudadController {
    @Autowired
    private CiudadService ciudadService;

    @GetMapping("/ciudad/list")
    public ResponseEntity<List<Ciudad>> listAll(){
        try {
            return new ResponseEntity<>(ciudadService.findByAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/ciudad/listP")
    public ResponseEntity<Page<Ciudad>> listAllCiudadesPorAlmacenesPage(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size, @RequestParam(defaultValue = "nombre_ciudad") String order, @RequestParam(defaultValue = "true") boolean asc){
        try {
            return new ResponseEntity<>(ciudadService.findAllCiudadesPorAlmacenes(PageRequest.of(page, size, Sort.by(order).descending())), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/ciudad/findbyId/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id){
        try {
            Ciudad ciudad = ciudadService.findById(id);
            if(ciudad != null){
                return new ResponseEntity<>(ciudad, HttpStatus.OK);
            }
            return new ResponseEntity<>("NO ENCONTRADA",HttpStatus.NOT_FOUND);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/ciudad/save")
    public ResponseEntity<Ciudad> saveCiudad(@RequestBody Ciudad ciudad){
        try {
            return new ResponseEntity<>(ciudadService.save(ciudad), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
