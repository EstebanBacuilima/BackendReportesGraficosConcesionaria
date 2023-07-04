package com.edu.proyect.Facturacion.controllers;
import com.edu.proyect.Facturacion.models.TipoVenta;
import com.edu.proyect.Facturacion.service.TipoVentaService;
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
public class TipoVentaController {
    @Autowired
    private TipoVentaService tipoVentaService;

    @GetMapping("/tipoVenta/list")
    public ResponseEntity<List<TipoVenta>> listAll(){
        try {
            return new ResponseEntity<>(tipoVentaService.findByAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tipoVenta/listP")
    public ResponseEntity<Page<TipoVenta>> listAllTipoVentaPage(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int size, @RequestParam(defaultValue = "tipoVenta") String order, @RequestParam(defaultValue = "false") boolean asc){
        try {
            return new ResponseEntity<>(tipoVentaService.findByAll(PageRequest.of(page, size, Sort.by(order).descending())), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tipoVenta/findbyId/{id}")
    public ResponseEntity<?> getAreaById(@PathVariable("id") Integer id){
        try {
            TipoVenta tipoVenta = tipoVentaService.findById(id);
            if(tipoVenta != null){
                return new ResponseEntity<>(tipoVenta, HttpStatus.OK);
            }
            return new ResponseEntity<>("NO ENCONTRADA",HttpStatus.NOT_FOUND);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/tipoVenta/save")
    public ResponseEntity<TipoVenta> save(@RequestBody TipoVenta tipoVenta){
        try {
            return new ResponseEntity<>(tipoVentaService.save(tipoVenta), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
