package com.edu.proyect.Facturacion.controllers;
import com.edu.proyect.Facturacion.models.Venta;
import com.edu.proyect.Facturacion.service.VentaService;
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
public class VentaController {
    @Autowired
    private VentaService ventaService;

    @GetMapping("/venta/list")
    public ResponseEntity<List<Venta>> listVentas(){
        try {
            return new ResponseEntity<>(ventaService.findByAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/venta/findbyId/{id}")
    public ResponseEntity<?> getVentaById(@PathVariable("id") Integer id){
        try {
            Venta venta = ventaService.findById(id);
            if(venta != null){
                return new ResponseEntity<>(venta, HttpStatus.OK);
            }
            return new ResponseEntity<>("NO ENCONTRADA",HttpStatus.NOT_FOUND);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/venta/save")
    public ResponseEntity<Venta> saveVenta(@RequestBody Venta venta){
        try {
            return new ResponseEntity<>(ventaService.save(venta), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
