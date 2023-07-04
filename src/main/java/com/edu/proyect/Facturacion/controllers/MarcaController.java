package com.edu.proyect.Facturacion.controllers;
import com.edu.proyect.Facturacion.models.Marca;
import com.edu.proyect.Facturacion.service.MarcaService;
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
public class MarcaController {
    @Autowired
    private MarcaService marcaService;

    @GetMapping("/marca/list")
    public ResponseEntity<List<Marca>> listAll(){
        try {
            return new ResponseEntity<>(marcaService.findByAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @PostMapping("/marca/save")
    public ResponseEntity<Marca> saveArea(@RequestBody Marca marca){
        try {
            return new ResponseEntity<>(marcaService.save(marca), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
