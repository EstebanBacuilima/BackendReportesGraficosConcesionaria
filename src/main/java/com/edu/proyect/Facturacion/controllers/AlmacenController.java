package com.edu.proyect.Facturacion.controllers;

import com.edu.proyect.Facturacion.models.Almacen;
import com.edu.proyect.Facturacion.service.AlmacenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class AlmacenController {

    @Autowired
    private AlmacenService almacenService;

    @GetMapping("/almacen/list")
    public ResponseEntity<List<Almacen>> listAlmacen(){
        try {
            return new ResponseEntity<>(almacenService.findByAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/almacen/listP")
    public ResponseEntity<Page<Almacen>> listAlmacenByNombresAndPage(@RequestParam("nombres") List<String> nombres, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "1") int size, @RequestParam(defaultValue = "nombreAlmacen") String order, @RequestParam(defaultValue = "true") boolean asc) {
        try {
            List<Almacen> almacenes = new ArrayList<>();
            for (String nombre : nombres) {
                almacenes.addAll(almacenService.findByEmpresaNombreEmpresa(PageRequest.of(page, size, Sort.by(order).descending()), nombre).getContent());
            }
            // CREATE - AND SET NEW VALUES FOR THE END AND FINISH PAGES FOR LIST THE DATA
            int startIndex = page * size;
            int endIndex = Math.min(startIndex + size, almacenes.size());
            // CREATE PageImpl - CREATE NEW PAGE
            Page<Almacen> resultPage = new PageImpl<>(almacenes.subList(startIndex, endIndex), PageRequest.of(page, size), almacenes.size());
            return new ResponseEntity<>(resultPage, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/almacen/listByCiudadesP")
    public ResponseEntity<Page<Almacen>> listAlmacenByCiudadesAndPage(@RequestParam("ciudades") List<String> ciudades, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "1") int size, @RequestParam(defaultValue = "nombreAlmacen") String order, @RequestParam(defaultValue = "true") boolean asc) {
        try {
            List<Almacen> almacenes = new ArrayList<>();
            for (String ciudad : ciudades) {
                almacenes.addAll(almacenService.findByCiudadNombreCiudad(PageRequest.of(page, size, Sort.by(order).descending()), ciudad).getContent());
            }
            // CREATE - AND SET NEW VALUES FOR THE END AND FINISH PAGES FOR LIST THE DATA
            int startIndex = page * size;
            int endIndex = Math.min(startIndex + size, almacenes.size());
            // CREATE PageImpl - CREATE NEW PAGE
            Page<Almacen> resultPage = new PageImpl<>(almacenes.subList(startIndex, endIndex), PageRequest.of(page, size), almacenes.size());
            return new ResponseEntity<>(resultPage, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/almacen/findbyId/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id){
        try {
            Almacen almacen = almacenService.findById(id);
            if(almacen != null){
                return new ResponseEntity<>(almacen, HttpStatus.OK);
            }
            return new ResponseEntity<>("NOT FOUD",HttpStatus.NOT_FOUND);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/almacen/save")
    public ResponseEntity<Almacen> saveArea(@RequestBody Almacen almacen){
        try {
            return new ResponseEntity<>(almacenService.save(almacen), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/almacen/actualizar/{id}")
    public ResponseEntity<Almacen> actualizarArea(@PathVariable Integer id, @RequestBody Almacen almacen) {
        try {
            if (almacenService.findById(id) == null) {
                return ResponseEntity.notFound().build();
            }
            almacen.setNombreAlmacen(almacen.getNombreAlmacen());
            almacen.setDireccionAlmacen(almacen.getDireccionAlmacen());
            almacen.setEstado(almacen.getEstado());
            return new ResponseEntity<>(almacenService.save(almacen), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
