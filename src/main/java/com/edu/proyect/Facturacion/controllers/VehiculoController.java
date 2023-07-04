package com.edu.proyect.Facturacion.controllers;
import com.edu.proyect.Facturacion.models.Categoria;
import com.edu.proyect.Facturacion.models.Vehiculo;
import com.edu.proyect.Facturacion.service.VehiculoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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
public class VehiculoController {
    @Autowired
    private VehiculoService vehiculoService;

    @GetMapping("/vehiculo/list")
    public ResponseEntity<List<Vehiculo>> listAll(){
        try {
            return new ResponseEntity<>(vehiculoService.findByAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/vehiculo/listP")
    public ResponseEntity<Page<Vehiculo>> listAllVehiculos( @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size, @RequestParam(defaultValue = "idVehiculo") String order, @RequestParam(defaultValue = "false") boolean asc){
        try {
            return new ResponseEntity<>(vehiculoService.findByAll( PageRequest.of(page, size, Sort.by(order).descending())), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/vehiculo/findbyId/{id}")
    public ResponseEntity<?> getById(@PathVariable("id") Integer id){
        try {
            Vehiculo vehiculo = vehiculoService.findById(id);
            if(vehiculo != null){
                return new ResponseEntity<>(vehiculo, HttpStatus.OK);
            }
            return new ResponseEntity<>("NO ENCONTRADA",HttpStatus.NOT_FOUND);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/vehiculo/save")
    public ResponseEntity<Vehiculo> save(@RequestBody Vehiculo vehiculo){
        try {
            return new ResponseEntity<>(vehiculoService.save(vehiculo), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/vehiculo/actualizar/{id}")
    public ResponseEntity<Vehiculo> actualizarVehiculo(@PathVariable Integer id, @RequestBody Vehiculo vehiculo) {
        try {
            if (vehiculoService.findById(id) == null) {
                return ResponseEntity.notFound().build();
            }
            vehiculo.setNombreVehiculo(vehiculo.getNombreVehiculo());
            vehiculo.setChasis(vehiculo.getChasis());
            vehiculo.setColor(vehiculo.getColor());
            vehiculo.setYear(vehiculo.getYear());
            return new ResponseEntity<>(vehiculoService.save(vehiculo), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
