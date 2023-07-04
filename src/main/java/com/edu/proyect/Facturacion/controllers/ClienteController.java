package com.edu.proyect.Facturacion.controllers;

import com.edu.proyect.Facturacion.models.Cliente;
import com.edu.proyect.Facturacion.service.ClienteService;
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
public class ClienteController {
    @Autowired
    private ClienteService clienteService;

    @GetMapping("/cliente/list")
    public ResponseEntity<List<Cliente>> listAll(){
        try {
            return new ResponseEntity<>(clienteService.findByAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/cliente/findbyId/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id){
        try {
            Cliente cliente = clienteService.findById(id);
            if(cliente != null){
                return new ResponseEntity<>(cliente, HttpStatus.OK);
            }
            return new ResponseEntity<>("NO ENCONTRADA",HttpStatus.NOT_FOUND);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/cliente/save")
    public ResponseEntity<Cliente> saveCliente(@RequestBody Cliente cliente){
        try {
            return new ResponseEntity<>(clienteService.save(cliente), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/cliente/actualizar/{id}")
    public ResponseEntity<Cliente> actualizarCliente(@PathVariable Integer id, @RequestBody Cliente cliente) {
        try {
            if (clienteService.findById(id) == null) {
                return ResponseEntity.notFound().build();
            }
            cliente.setNombres(cliente.getNombres());
            cliente.setApellidos(cliente.getApellidos());
            cliente.setCelular(cliente.getCelular());
            cliente.setDireccion(cliente.getDireccion());

            return new ResponseEntity<>(clienteService.save(cliente), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
