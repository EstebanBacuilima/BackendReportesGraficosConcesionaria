package com.edu.proyect.Facturacion.controllers;

import com.edu.proyect.Facturacion.models.DetalleVenta;
import com.edu.proyect.Facturacion.service.DetalleVentaService;
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
public class DetalleVentaController {
    @Autowired
    private DetalleVentaService detalleVentaService;

    @GetMapping("/detalleVenta/list")
    public ResponseEntity<List<DetalleVenta>> listAll(){
        try {
            return new ResponseEntity<>(detalleVentaService.findByAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/detalleVenta/findbyId/{id}")
    public ResponseEntity<?> getAreaById(@PathVariable("id") Integer id){
        try {
            DetalleVenta detalleVenta = detalleVentaService.findById(id);
            if(detalleVenta != null){
                return new ResponseEntity<>(detalleVenta, HttpStatus.OK);
            }
            return new ResponseEntity<>("AREA NO ENCONTRADA",HttpStatus.NOT_FOUND);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/detalleVenta/save")
    public ResponseEntity<DetalleVenta> saveArea(@RequestBody DetalleVenta detalleVenta){
        try {
            return new ResponseEntity<>(detalleVentaService.save(detalleVenta), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/detalleVenta/actualizar/{id}")
    public ResponseEntity<DetalleVenta> actualizarArea(@PathVariable Integer id, @RequestBody DetalleVenta detalleVenta) {
        try {
            if (detalleVentaService.findById(id) == null) {
                return ResponseEntity.notFound().build();
            }
            detalleVenta.setDetalles(detalleVenta.getDetalles());
            detalleVenta.setCantidad(detalleVenta.getCantidad());
            return new ResponseEntity<>(detalleVentaService.save(detalleVenta), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
