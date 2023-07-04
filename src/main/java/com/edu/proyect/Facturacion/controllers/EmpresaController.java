package com.edu.proyect.Facturacion.controllers;

import com.edu.proyect.Facturacion.Dtos.EmpresaDto;
import com.edu.proyect.Facturacion.Payloads.*;
import com.edu.proyect.Facturacion.models.Empresa;
import com.edu.proyect.Facturacion.service.EmpresaService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@CrossOrigin("*")
@RestController
@RequestMapping("/api")
public class EmpresaController {
    @Autowired
    private EmpresaService empresaService;

    @GetMapping("/empresa/list")
    public ResponseEntity<List<Empresa>> listAllEmpresas(){
        try {
            return new ResponseEntity<>(empresaService.findByAll(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/empresa/listP")
    public ResponseEntity<Page<Empresa>> listAllEmpresasPage(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "3") int size, @RequestParam(defaultValue = "nombreEmpresa") String order, @RequestParam(defaultValue = "false") boolean asc){
        try {
            return new ResponseEntity<>(empresaService.findByAll(PageRequest.of(page, size, Sort.by(order).descending())), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // CONSULTAS CATERIAS, TIPOS, VEHICULOS, MARCAS
    @GetMapping("/empresaCategorias/listP/{nameEmpresa}")
    public ResponseEntity<Page<CategoriaDto>> getAllCategoriasPorEmpresas(@PathVariable("nameEmpresa") List<String> nameEmpresa, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int size, @RequestParam(defaultValue = "nombre_categoria") String order, @RequestParam(defaultValue = "false") boolean asc){
        try {
            List<CategoriaDto> list = new ArrayList<>();
            for (String nombre : nameEmpresa) {
                list.addAll(empresaService.getAllCategoriasPorEmpresas(nombre));
            }
            // CREATE - AND SET NEW VALUES FOR THE END AND FINISH PAGES FOR LIST THE DATA
            int startIndex = page * size;
            int endIndex = Math.min(startIndex + size, list.size());
            // CREATE PageImpl - CREATE NEW PAGE
            Page<CategoriaDto> resultPage = new PageImpl<>(list.subList(startIndex, endIndex), PageRequest.of(page, size), list.size());
            return new ResponseEntity<>(resultPage, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/empresaTipoVentas/listP/{nameEmpresa2}")
    public ResponseEntity<Page<TipoVentaDto>> getAllTiposPorEmpresas(@PathVariable("nameEmpresa2") List<String> nameEmpresa2, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int size, @RequestParam(defaultValue = "tipo_venta") String order, @RequestParam(defaultValue = "false") boolean asc){
        try {
            List<TipoVentaDto> list = new ArrayList<>();
            for (String nombre : nameEmpresa2) {
                list.addAll(empresaService.getAllTiposVehiculosPorEmpresas(nombre));
            }
            // CREATE - AND SET NEW VALUES FOR THE END AND FINISH PAGES FOR LIST THE DATA
            int startIndex = page * size;
            int endIndex = Math.min(startIndex + size, list.size());
            // CREATE PageImpl - CREATE NEW PAGE
            Page<TipoVentaDto> resultPage = new PageImpl<>(list.subList(startIndex, endIndex), PageRequest.of(page, size), list.size());
            return new ResponseEntity<>(resultPage, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/empresaMarcas/listP/{nameEmpresa3}")
    public ResponseEntity<Page<MarcasDto>> getAllMarcasPorEmpresas(@PathVariable("nameEmpresa3") List<String> nameEmpresa3, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int size, @RequestParam(defaultValue = "nombre_marca") String order, @RequestParam(defaultValue = "false") boolean asc){
        try {
            List<MarcasDto> list = new ArrayList<>();
            for (String nombre : nameEmpresa3) {
                list.addAll(empresaService.getAllMarcasPorEmpresas(nombre));
            }
            // CREATE - AND SET NEW VALUES FOR THE END AND FINISH PAGES FOR LIST THE DATA
            int startIndex = page * size;
            int endIndex = Math.min(startIndex + size, list.size());
            // CREATE PageImpl - CREATE NEW PAGE
            Page<MarcasDto> resultPage = new PageImpl<>(list.subList(startIndex, endIndex), PageRequest.of(page, size, Sort.by(order).descending()), list.size());
            return new ResponseEntity<>(resultPage, HttpStatus.OK);
        } catch (Exception e){
            System.out.println("aaaaaaaaaaaaaa " + e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/empresaVehiculos/listP/{nameEmpresa}")
    public ResponseEntity<Page<VehiculosDto>> getAllVehiculosPorEmpresas(@PathVariable("nameEmpresa") List<String> nameEmpresa, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int size, @RequestParam(defaultValue = "nombre_vehiculo") String order, @RequestParam(defaultValue = "false") boolean asc){
        try {
            List<VehiculosDto> list = new ArrayList<>();
            for (String nombre : nameEmpresa) {
                list.addAll(empresaService.getAllVehiculosPorEmpresas(nombre));
            }
            // CREATE - AND SET NEW VALUES FOR THE END AND FINISH PAGES FOR LIST THE DATA
            int startIndex = page * size;
            int endIndex = Math.min(startIndex + size, list.size());
            // CREATE PageImpl - CREATE NEW PAGE
            Page<VehiculosDto> resultPage = new PageImpl<>(list.subList(startIndex, endIndex), PageRequest.of(page, size), list.size());
            return new ResponseEntity<>(resultPage, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/empresaAlmacenes/listP/{nameEmpresa}")
    public ResponseEntity<Page<AlmacenesDto>> getAllAlmacenesPorEmpresas(@PathVariable("nameEmpresa") String nameEmpresa, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "2") int size, @RequestParam(defaultValue = "nombre_almacen") String order, @RequestParam(defaultValue = "false") boolean asc){
        try {
            return new ResponseEntity<>(empresaService.getAllAlmacenesPorEmpresas(nameEmpresa,PageRequest.of(page, size, Sort.by(order).descending())), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // FIN

    @GetMapping("/empresaDTO/listReportVentas")
    public ResponseEntity<List<VentasEmpresasDto>> listEmpresasAndVentas(){
        try {
            return new ResponseEntity<>(empresaService.listAllEmpresasAndVentas(), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/empresaDTO/list")
    public ResponseEntity<List<EmpresaDto>> listAreaSimplificada() {
        try {
            // GET ALL EMPRESAS
            List<Empresa> empresas = empresaService.findByAll();

            // GO TO THE DTO
            List<EmpresaDto> empresasSimplificadas = empresas.stream().map(empresa -> {
                EmpresaDto ciudadDTO = new EmpresaDto(
                        empresa.getNombreEmpresa(),
                        empresa.getObjetivo()
                        //empresa.getCiudad().getNombreCiudad()
                );

                return new EmpresaDto(
                        empresa.getNombreEmpresa(),
                        empresa.getObjetivo()
                        //ciudadDTO.getNombreCiudad()
                );
            }).collect(Collectors.toList());

            return new ResponseEntity<>(empresasSimplificadas, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // SEARCH EMPRESA BY NAME
    @GetMapping("empresaDTO/searchByName/{texto}")
    public ResponseEntity<List<EmpresaDto>> listEmpresasName(@PathVariable("texto") String texto){
        try {
            // GET ALL EMPRESAS
            List<Empresa> empresas = empresaService.searchByName(texto);

            // GO TO THE DTO
            List<EmpresaDto> empresasSimplificadas = empresas.stream().map(empresa -> {
                EmpresaDto ciudadDTO = new EmpresaDto(
                        empresa.getNombreEmpresa(),
                        empresa.getObjetivo()
                        //empresa.getCiudad().getNombreCiudad()
                );

                return new EmpresaDto(
                        empresa.getNombreEmpresa(),
                        empresa.getObjetivo()
                        //ciudadDTO.getNombreCiudad()
                );
            }).collect(Collectors.toList());
            return new ResponseEntity<>(empresasSimplificadas, HttpStatus.OK);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("empresaDTO/listEmpresasAndVentaForDates/{dateOne}/{dateTwo}")
    public ResponseEntity<List<VentasEmpresasDto>> listEmpresasAndVentaForDates(@PathVariable("dateOne") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateOne, @PathVariable("dateTwo") @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate dateTwo){
        try {
            return new ResponseEntity<>(empresaService.listAllEmpresasAndVentasByDates(dateOne,dateTwo), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/empresaDTO/findAllVentasByEmpresa/{name}")
    public ResponseEntity<List<VentasCanalesEmpresaDto>> findAllVentasByEmpresa(@PathVariable("name") String name){
        try {
            return new ResponseEntity<>(empresaService.listAllCanalesVentasByEmpresa(name), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // EMPRESAS
    @GetMapping("/empresaDTO/listAllVentasColorByEmpresa/{nameEmpresa}")
    public ResponseEntity<List<VentasEmpresasAlmacenColores>> listAllVentasColorByEmpresa(
            @PathVariable("nameEmpresa") List<String> nameEmpresa
    ){
        try {
            List<VentasEmpresasAlmacenColores> list = empresaService.listAllVentasColorByEmpresa(nameEmpresa);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/empresaDTO/listAllVentasByEmpresaYear/{nameEmpresa}")
    public ResponseEntity<List<VentasEmpresasAlmacenesYearsDto>> listAllVentasByEmpresaYear(
            @PathVariable("nameEmpresa") List<String> nameEmpresa
    ){
        try {
            List<VentasEmpresasAlmacenesYearsDto> list = empresaService.listAllVentasByEmpresaYear(nameEmpresa);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/empresaDTO/listAllVentasCanalesByEmpresa/{nameEmpresa}")
    public ResponseEntity<List<VentasCanalesEmpresaDto>> listAllVentasCanalesByEmpresa(
            @PathVariable("nameEmpresa") List<String> nameEmpresa
    ){
        try {
            List<VentasCanalesEmpresaDto> list = empresaService.listAllVentasCanalesByEmpresa(nameEmpresa);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/empresaDTO/listAllVentasByEmpresa/{nameEmpresa}")
    public ResponseEntity<List<VentasEmpresaAlmacenTipoDateDto>> listAllVentasByEmpresa(
            @PathVariable("nameEmpresa") List<String> nameEmpresa
    ){
        try {
            List<VentasEmpresaAlmacenTipoDateDto> list = empresaService.listAllVentasByEmpresa(nameEmpresa);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ALMACENES
    @GetMapping("/empresaDTO/listAllVentasByEmpresaColorPorAlmacen/{nameAlmacen}")
    public ResponseEntity<List<VentasEmpresasAlmacenColores>> listAllVentasByEmpresaColorPorAlmacen(
            @PathVariable("nameAlmacen") List<String> nameAlmacen
    ){
        try {
            List<VentasEmpresasAlmacenColores> list = empresaService.listAllVentasByEmpresaColorPorAlmacen(nameAlmacen);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/empresaDTO/listAllVentasByEmpresaYearPorAlmacen/{nameAlmacen}")
    public ResponseEntity<List<VentasEmpresasAlmacenesYearsDto>> listAllVentasByEmpresaYearPorAlmacen(
            @PathVariable("nameAlmacen") List<String> nameAlmacen
    ){
        try {
            List<VentasEmpresasAlmacenesYearsDto> list = empresaService.listAllVentasByEmpresaYearPorAlmacen(nameAlmacen);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/empresaDTO/listAllVentasCanalesByEmpresaPorAlmacen/{nameAlmacen}")
    public ResponseEntity<List<VentasCanalesEmpresaDto>> listAllVentasCanalesByEmpresaPorAlmacen(
            @PathVariable("nameAlmacen") List<String> nameAlmacen
    ){
        try {
            List<VentasCanalesEmpresaDto> list = empresaService.listAllVentasCanalesByEmpresaPorAlmacen(nameAlmacen);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/empresaDTO/listAllVentasByEmpresaPorAlmacen/{nameAlmacen}")
    public ResponseEntity<List<VentasEmpresaAlmacenTipoDateDto>> listAllVentasByEmpresaPorAlmacen(
            @PathVariable("nameAlmacen") List<String> nameAlmacen
    ){
        try {
            List<VentasEmpresaAlmacenTipoDateDto> list = empresaService.listAllVentasByEmpresaPorAlmacen(nameAlmacen);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // LAS 15 ---------------------------------------------------------------
    @GetMapping("/empresaDTO/findAllVentasByEmpresaPorTipoAndDate/{nameAlmacen}/{tipoCarro}")
    public ResponseEntity<List<VentasEmpresaAlmacenTipoDateDto>> findAllVentasByEmpresaPorTipoAndDate(@PathVariable("nameAlmacen") List<String> nameAlmacen, @PathVariable("tipoCarro") List<String> tipoCarro){
        try {
            Map<String, Double> ventasPorAlmacen = new HashMap<>();
            for (String nombreAlmacen : nameAlmacen) {
                for (String tipo : tipoCarro) {
                    List<VentasEmpresaAlmacenTipoDateDto> ventas = empresaService.listAllVentasByEmpresaPorTipoAndDate(nombreAlmacen, tipo);
                    for (VentasEmpresaAlmacenTipoDateDto venta : ventas) {
                        if (ventasPorAlmacen.containsKey(venta.getNomAlmacen())) {
                            double ventasAnteriores = ventasPorAlmacen.get(venta.getNomAlmacen());
                            ventasPorAlmacen.put(venta.getNomAlmacen(), ventasAnteriores + venta.getNumVentas());
                        } else {
                            ventasPorAlmacen.put(venta.getNomAlmacen(), venta.getNumVentas());
                        }
                    }
                }
            }

            List<VentasEmpresaAlmacenTipoDateDto> ventasTotales = new ArrayList<>();

            for (Map.Entry<String, Double> entry : ventasPorAlmacen.entrySet()) {
                ventasTotales.add(new VentasEmpresaAlmacenTipoDateDto() {
                    @Override
                    public String getNomAlmacen() {
                        return entry.getKey();
                    }

                    @Override
                    public Double getNumVentas() {
                        return entry.getValue();
                    }
                });            }
            return new ResponseEntity<>(ventasTotales, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/empresaDTO/listAllVentasByEmpresaPorTipoAndMarcas/{nameAlmacen}/{nameCategoria}/{nameMarca}")
    public ResponseEntity<List<VentasEmpresaAlmacenTipoDateDto>> listAllVentasByEmpresaPorTipoAndMarcas(
            @PathVariable("nameAlmacen") List<String> nameAlmacen,
            @PathVariable("nameCategoria") List<String> nameCategoria,
            @PathVariable("nameMarca") List<String> nameMarca
    ){
        try {
            List<VentasEmpresaAlmacenTipoDateDto> list = empresaService.listAllVentasByEmpresaPorTipoAndMarcas(nameAlmacen, nameCategoria, nameMarca);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/empresaDTO/listAllVentasByEmpresaPorTipoAndMarcasAndCanal/{nameAlmacen}/{nameCategoria}/{nameMarca}/{nameCanal}")
    public ResponseEntity<List<VentasEmpresaAlmacenTipoDateDto>> listAllVentasByEmpresaPorTipoAndMarcasAndCanal(
            @PathVariable("nameAlmacen") List<String> nameAlmacen,
            @PathVariable("nameCategoria") List<String> nameCategoria,
            @PathVariable("nameMarca") List<String> nameMarca,
            @PathVariable("nameCanal") List<String> nameCanal
    ){
        try {
            List<VentasEmpresaAlmacenTipoDateDto> list = empresaService.listAllVentasByEmpresaPorTipoAndMarcasAndCanal(nameAlmacen, nameCategoria, nameMarca,nameCanal);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/empresaDTO/listAllVentasByEmpresaPorTipoAndMarcasAndCanalAndVehiculo/{nameAlmacen}/{nameCategoria}/{nameMarca}/{nameCanal}/{nameVehiculo}")
    public ResponseEntity<List<VentasEmpresaAlmacenTipoDateDto>> listAllVentasByEmpresaPorTipoAndMarcasAndCanalAndVehiculo(
            @PathVariable("nameAlmacen") List<String> nameAlmacen,
            @PathVariable("nameCategoria") List<String> nameCategoria,
            @PathVariable("nameMarca") List<String> nameMarca,
            @PathVariable("nameCanal") List<String> nameCanal,
            @PathVariable("nameVehiculo") List<String> nameVehiculo
    ){
        try {
            List<VentasEmpresaAlmacenTipoDateDto> list = empresaService.listAllVentasByEmpresaPorTipoAndMarcasAndCanalAndVehiculo(nameAlmacen, nameCategoria, nameMarca,nameCanal,nameVehiculo);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // CANALES

    @GetMapping("/empresaDTO/listAllVentasByEmpresaPorAlmacenAndCategoria/{nameAlmacen}/{nameCategoria}")
    public ResponseEntity<List<VentasCanalesEmpresaDto>> listAllVentasByEmpresaPorAlmacenAndCategoria(
            @PathVariable("nameAlmacen") List<String> nameAlmacen,
            @PathVariable("nameCategoria") List<String> nameCategoria
    ){
        try {
            List<VentasCanalesEmpresaDto> list = empresaService.listAllVentasByEmpresaPorAlmacenAndCategoria(nameAlmacen, nameCategoria);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/empresaDTO/listAllVentasByEmpresaPorAlmacenAndCategoriaAndMarca/{nameAlmacen}/{nameCategoria}/{nameMarca}")
    public ResponseEntity<List<VentasCanalesEmpresaDto>> listAllVentasByEmpresaPorAlmacenAndCategoriaAndMarca(
            @PathVariable("nameAlmacen") List<String> nameAlmacen,
            @PathVariable("nameCategoria") List<String> nameCategoria,
            @PathVariable("nameMarca") List<String> nameMarca
    ){
        try {
            List<VentasCanalesEmpresaDto> list = empresaService.listAllVentasByEmpresaPorAlmacenAndCategoriaAndMarca(nameAlmacen, nameCategoria, nameMarca);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/empresaDTO/listAllVentasByEmpresaPorAlmacenAndCategoriaAndMarcaAndCanal/{nameAlmacen}/{nameCategoria}/{nameMarca}/{nameCanal}")
    public ResponseEntity<List<VentasCanalesEmpresaDto>> listAllVentasByEmpresaPorAlmacenAndCategoriaAndMarcaAndCanal(
            @PathVariable("nameAlmacen") List<String> nameAlmacen,
            @PathVariable("nameCategoria") List<String> nameCategoria,
            @PathVariable("nameMarca") List<String> nameMarca,
            @PathVariable("nameCanal") List<String> nameCanal
    ){
        try {
            List<VentasCanalesEmpresaDto> list = empresaService.listAllVentasByEmpresaPorAlmacenAndCategoriaAndMarcaAndCanal(nameAlmacen, nameCategoria, nameMarca,nameCanal);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/empresaDTO/listAllVentasByEmpresaPorAlmacenAndCategoriaAndMarcaAndCanalAndVehiculo/{nameAlmacen}/{nameCategoria}/{nameMarca}/{nameCanal}/{nameVehiculo}")
    public ResponseEntity<List<VentasCanalesEmpresaDto>> listAllVentasByEmpresaPorAlmacenAndCategoriaAndMarcaAndCanalAndVehiculo(
            @PathVariable("nameAlmacen") List<String> nameAlmacen,
            @PathVariable("nameCategoria") List<String> nameCategoria,
            @PathVariable("nameMarca") List<String> nameMarca,
            @PathVariable("nameCanal") List<String> nameCanal,
            @PathVariable("nameVehiculo") List<String> nameVehiculo
    ){
        try {
            List<VentasCanalesEmpresaDto> list = empresaService.listAllVentasByEmpresaPorAlmacenAndCategoriaAndMarcaAndCanalAndVehiculo(nameAlmacen, nameCategoria, nameMarca,nameCanal,nameVehiculo);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // COLOR

    @GetMapping("/empresaDTO/listAllVentasByEmpresaColorPorAlmacenAndCategoria/{nameAlmacen}/{nameCategoria}")
    public ResponseEntity<List<VentasEmpresasAlmacenColores>> listAllVentasByEmpresaColorPorAlmacenAndCategoria(
            @PathVariable("nameAlmacen") List<String> nameAlmacen,
            @PathVariable("nameCategoria") List<String> nameCategoria
    ){
        try {
            List<VentasEmpresasAlmacenColores> list = empresaService.listAllVentasByEmpresaColorPorAlmacenAndCategoria(nameAlmacen, nameCategoria);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/empresaDTO/listAllVentasByEmpresaColorPorAlmacenAndCategoriaAndMarca/{nameAlmacen}/{nameCategoria}/{nameMarca}")
    public ResponseEntity<List<VentasEmpresasAlmacenColores>> listAllVentasByEmpresaColorPorAlmacenAndCategoriaAndMarca(
            @PathVariable("nameAlmacen") List<String> nameAlmacen,
            @PathVariable("nameCategoria") List<String> nameCategoria,
            @PathVariable("nameMarca") List<String> nameMarca
    ){
        try {
            List<VentasEmpresasAlmacenColores> list = empresaService.listAllVentasByEmpresaColorPorAlmacenAndCategoriaAndMarca(nameAlmacen, nameCategoria, nameMarca);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/empresaDTO/listAllVentasByEmpresaColorPorAlmacenAndCategoriaAndMarcaAndCanal/{nameAlmacen}/{nameCategoria}/{nameMarca}/{nameCanal}")
    public ResponseEntity<List<VentasEmpresasAlmacenColores>> listAllVentasByEmpresaColorPorAlmacenAndCategoriaAndMarcaAndCanal(
            @PathVariable("nameAlmacen") List<String> nameAlmacen,
            @PathVariable("nameCategoria") List<String> nameCategoria,
            @PathVariable("nameMarca") List<String> nameMarca,
            @PathVariable("nameCanal") List<String> nameCanal
    ){
        try {
            List<VentasEmpresasAlmacenColores> list = empresaService.listAllVentasByEmpresaColorPorAlmacenAndCategoriaAndMarcaAndCanal(nameAlmacen, nameCategoria, nameMarca,nameCanal);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/empresaDTO/listAllVentasByEmpresaColorPorAlmacenAndCategoriaAndMarcaAndCanalAndVehiculo/{nameAlmacen}/{nameCategoria}/{nameMarca}/{nameCanal}/{nameVehiculo}")
    public ResponseEntity<List<VentasEmpresasAlmacenColores>> listAllVentasByEmpresaColorPorAlmacenAndCategoriaAndMarcaAndCanalAndVehiculo(
            @PathVariable("nameAlmacen") List<String> nameAlmacen,
            @PathVariable("nameCategoria") List<String> nameCategoria,
            @PathVariable("nameMarca") List<String> nameMarca,
            @PathVariable("nameCanal") List<String> nameCanal,
            @PathVariable("nameVehiculo") List<String> nameVehiculo
    ){
        try {
            List<VentasEmpresasAlmacenColores> list = empresaService.listAllVentasByEmpresaColorPorAlmacenAndCategoriaAndMarcaAndCanalAndVehiculo(nameAlmacen, nameCategoria, nameMarca,nameCanal,nameVehiculo);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    // YEARS

    @GetMapping("/empresaDTO/listAllVentasByEmpresaYearPorAlmacenAndCategoria/{nameAlmacen}/{nameCategoria}")
    public ResponseEntity<List<VentasEmpresasAlmacenesYearsDto>> listAllVentasByEmpresaYearPorAlmacenAndCategoria(
            @PathVariable("nameAlmacen") List<String> nameAlmacen,
            @PathVariable("nameCategoria") List<String> nameCategoria
    ){
        try {
            List<VentasEmpresasAlmacenesYearsDto> list = empresaService.listAllVentasByEmpresaYearPorAlmacenAndCategoria(nameAlmacen, nameCategoria);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/empresaDTO/listAllVentasByEmpresaYearPorAlmacenAndCategoriaAndMarca/{nameAlmacen}/{nameCategoria}/{nameMarca}")
    public ResponseEntity<List<VentasEmpresasAlmacenesYearsDto>> listAllVentasByEmpresaYearPorAlmacenAndCategoriaAndMarca(
            @PathVariable("nameAlmacen") List<String> nameAlmacen,
            @PathVariable("nameCategoria") List<String> nameCategoria,
            @PathVariable("nameMarca") List<String> nameMarca
    ){
        try {
            List<VentasEmpresasAlmacenesYearsDto> list = empresaService.listAllVentasByEmpresaYearPorAlmacenAndCategoriaAndMarca(nameAlmacen, nameCategoria, nameMarca);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/empresaDTO/listAllVentasByEmpresaYearPorAlmacenAndCategoriaAndMarcaAndCanal/{nameAlmacen}/{nameCategoria}/{nameMarca}/{nameCanal}")
    public ResponseEntity<List<VentasEmpresasAlmacenesYearsDto>> listAllVentasByEmpresaYearPorAlmacenAndCategoriaAndMarcaAndCanal(
            @PathVariable("nameAlmacen") List<String> nameAlmacen,
            @PathVariable("nameCategoria") List<String> nameCategoria,
            @PathVariable("nameMarca") List<String> nameMarca,
            @PathVariable("nameCanal") List<String> nameCanal
    ){
        try {
            List<VentasEmpresasAlmacenesYearsDto> list = empresaService.listAllVentasByEmpresaYearPorAlmacenAndCategoriaAndMarcaAndCanal(nameAlmacen, nameCategoria, nameMarca,nameCanal);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/empresaDTO/listAllVentasByEmpresaYearPorAlmacenAndCategoriaAndMarcaAndCanalAndVehiculo/{nameAlmacen}/{nameCategoria}/{nameMarca}/{nameCanal}/{nameVehiculo}")
    public ResponseEntity<List<VentasEmpresasAlmacenesYearsDto>> listAllVentasByEmpresaYearPorAlmacenAndCategoriaAndMarcaAndCanalAndVehiculo(
            @PathVariable("nameAlmacen") List<String> nameAlmacen,
            @PathVariable("nameCategoria") List<String> nameCategoria,
            @PathVariable("nameMarca") List<String> nameMarca,
            @PathVariable("nameCanal") List<String> nameCanal,
            @PathVariable("nameVehiculo") List<String> nameVehiculo
    ){
        try {
            List<VentasEmpresasAlmacenesYearsDto> list = empresaService.listAllVentasByEmpresaYearPorAlmacenAndCategoriaAndMarcaAndCanalAndVehiculo(nameAlmacen, nameCategoria, nameMarca,nameCanal,nameVehiculo);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ANOTHER 1
    @GetMapping("/empresaDTO/listAllVentasByEmpresaColorPorAlmacenAndCategoriaAndCanalAndVehiculo/{nameAlmacen}/{nameCategoria}/{nameCanal}/{nameVehiculo}")
    public ResponseEntity<List<VentasEmpresasAlmacenColores>> listAllVentasByEmpresaColorPorAlmacenAndCategoriaAndCanalAndVehiculo(
            @PathVariable("nameAlmacen") List<String> nameAlmacen,
            @PathVariable("nameCategoria") List<String> nameCategoria,
            @PathVariable("nameCanal") List<String> nameCanal,
            @PathVariable("nameVehiculo") List<String> nameVehiculo
    ){
        try {
            List<VentasEmpresasAlmacenColores> list = empresaService.listAllVentasByEmpresaColorPorAlmacenAndCategoriaAndCanalAndVehiculo(nameAlmacen, nameCategoria,nameCanal,nameVehiculo);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/empresaDTO/listAllVentasByEmpresaYearPorAlmacenAndCategoriaAndCanalAndVehiculo/{nameAlmacen}/{nameCategoria}/{nameCanal}/{nameVehiculo}")
    public ResponseEntity<List<VentasEmpresasAlmacenesYearsDto>> listAllVentasByEmpresaYearPorAlmacenAndCategoriaAndCanalAndVehiculo(
            @PathVariable("nameAlmacen") List<String> nameAlmacen,
            @PathVariable("nameCategoria") List<String> nameCategoria,
            @PathVariable("nameCanal") List<String> nameCanal,
            @PathVariable("nameVehiculo") List<String> nameVehiculo
    ){
        try {
            List<VentasEmpresasAlmacenesYearsDto> list = empresaService.listAllVentasByEmpresaYearPorAlmacenAndCategoriaAndCanalAndVehiculo(nameAlmacen, nameCategoria,nameCanal,nameVehiculo);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/empresaDTO/listAllVentasByEmpresaPorAlmacenAndCategoriaAndCanalAndVehiculo/{nameAlmacen}/{nameCategoria}/{nameCanal}/{nameVehiculo}")
    public ResponseEntity<List<VentasCanalesEmpresaDto>> listAllVentasByEmpresaPorAlmacenAndCategoriaAndCanalAndVehiculo(
            @PathVariable("nameAlmacen") List<String> nameAlmacen,
            @PathVariable("nameCategoria") List<String> nameCategoria,
            @PathVariable("nameCanal") List<String> nameCanal,
            @PathVariable("nameVehiculo") List<String> nameVehiculo
    ){
        try {
            List<VentasCanalesEmpresaDto> list = empresaService.listAllVentasByEmpresaPorAlmacenAndCategoriaAndCanalAndVehiculo(nameAlmacen, nameCategoria,nameCanal,nameVehiculo);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/empresaDTO/listAllVentasByEmpresaPorTipoAndAndCanalAndVehiculo/{nameAlmacen}/{nameCategoria}/{nameCanal}/{nameVehiculo}")
    public ResponseEntity<List<VentasEmpresaAlmacenTipoDateDto>> listAllVentasByEmpresaPorTipoAndAndCanalAndVehiculo(
            @PathVariable("nameAlmacen") List<String> nameAlmacen,
            @PathVariable("nameCategoria") List<String> nameCategoria,
            @PathVariable("nameCanal") List<String> nameCanal,
            @PathVariable("nameVehiculo") List<String> nameVehiculo
    ){
        try {
            List<VentasEmpresaAlmacenTipoDateDto> list = empresaService.listAllVentasByEmpresaPorTipoAndAndCanalAndVehiculo(nameAlmacen, nameCategoria,nameCanal,nameVehiculo);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // ANITHER 2
    @GetMapping("/empresaDTO/listAllVentasByEmpresaColorPorAlmacenMarcaAndCanalAndVehiculo/{nameAlmacen}/{nameMarca}/{nameCanal}/{nameVehiculo}")
    public ResponseEntity<List<VentasEmpresasAlmacenColores>> listAllVentasByEmpresaColorPorAlmacenMarcaAndCanalAndVehiculo(
            @PathVariable("nameAlmacen") List<String> nameAlmacen,
            @PathVariable("nameMarca") List<String> nameMarca,
            @PathVariable("nameCanal") List<String> nameCanal,
            @PathVariable("nameVehiculo") List<String> nameVehiculo
    ){
        try {
            List<VentasEmpresasAlmacenColores> list = empresaService.listAllVentasByEmpresaColorPorAlmacenMarcaAndCanalAndVehiculo(nameAlmacen, nameMarca,nameCanal,nameVehiculo);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/empresaDTO/listAllVentasByEmpresaYearPorAlmacenMarcaAndCanalAndVehiculo/{nameAlmacen}/{nameMarca}/{nameCanal}/{nameVehiculo}")
    public ResponseEntity<List<VentasEmpresasAlmacenesYearsDto>> listAllVentasByEmpresaYearPorAlmacenMarcaAndCanalAndVehiculo(
            @PathVariable("nameAlmacen") List<String> nameAlmacen,
            @PathVariable("nameMarca") List<String> nameMarca,
            @PathVariable("nameCanal") List<String> nameCanal,
            @PathVariable("nameVehiculo") List<String> nameVehiculo
    ){
        try {
            List<VentasEmpresasAlmacenesYearsDto> list = empresaService.listAllVentasByEmpresaYearPorAlmacenMarcaAndCanalAndVehiculo(nameAlmacen, nameMarca,nameCanal,nameVehiculo);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/empresaDTO/listAllVentasByEmpresaPorAlmacenMarcaAndCanalAndVehiculo/{nameAlmacen}/{nameMarca}/{nameCanal}/{nameVehiculo}")
    public ResponseEntity<List<VentasCanalesEmpresaDto>> listAllVentasByEmpresaPorAlmacenMarcaAndCanalAndVehiculo(
            @PathVariable("nameAlmacen") List<String> nameAlmacen,
            @PathVariable("nameMarca") List<String> nameMarca,
            @PathVariable("nameCanal") List<String> nameCanal,
            @PathVariable("nameVehiculo") List<String> nameVehiculo
    ){
        try {
            List<VentasCanalesEmpresaDto> list = empresaService.listAllVentasByEmpresaPorAlmacenMarcaAndCanalAndVehiculo(nameAlmacen, nameMarca,nameCanal,nameVehiculo);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/empresaDTO/listAllVentasByEmpresaPorMarcasAndCanalAndVehiculo/{nameAlmacen}/{nameMarca}/{nameCanal}/{nameVehiculo}")
    public ResponseEntity<List<VentasEmpresaAlmacenTipoDateDto>> listAllVentasByEmpresaPorMarcasAndCanalAndVehiculo(
            @PathVariable("nameAlmacen") List<String> nameAlmacen,
            @PathVariable("nameMarca") List<String> nameMarca,
            @PathVariable("nameCanal") List<String> nameCanal,
            @PathVariable("nameVehiculo") List<String> nameVehiculo
    ){
        try {
            List<VentasEmpresaAlmacenTipoDateDto> list = empresaService.listAllVentasByEmpresaPorMarcasAndCanalAndVehiculo(nameAlmacen, nameMarca,nameCanal,nameVehiculo);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // ANOTHER 3
    @GetMapping("/empresaDTO/listAllVentasByEmpresaColorPorAlmacenAndCategoriaAndMarcaAndVehiculo/{nameAlmacen}/{nameCategoria}/{nameMarca}/{nameVehiculo}")
    public ResponseEntity<List<VentasEmpresasAlmacenColores>> listAllVentasByEmpresaColorPorAlmacenAndCategoriaAndMarcaAndVehiculo(
            @PathVariable("nameAlmacen") List<String> nameAlmacen,
            @PathVariable("nameCategoria") List<String> nameCategoria,
            @PathVariable("nameMarca") List<String> nameMarca,
            @PathVariable("nameVehiculo") List<String> nameVehiculo
    ){
        try {
            List<VentasEmpresasAlmacenColores> list = empresaService.listAllVentasByEmpresaColorPorAlmacenAndCategoriaAndMarcaAndVehiculo(nameAlmacen, nameCategoria,nameMarca,nameVehiculo);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/empresaDTO/listAllVentasByEmpresaYearPorAlmacenAndCategoriaAndMarcaAndVehiculo/{nameAlmacen}/{nameCategoria}/{nameMarca}/{nameVehiculo}")
    public ResponseEntity<List<VentasEmpresasAlmacenesYearsDto>> listAllVentasByEmpresaYearPorAlmacenAndCategoriaAndMarcaAndVehiculo(
            @PathVariable("nameAlmacen") List<String> nameAlmacen,
            @PathVariable("nameCategoria") List<String> nameCategoria,
            @PathVariable("nameMarca") List<String> nameMarca,
            @PathVariable("nameVehiculo") List<String> nameVehiculo
    ){
        try {
            List<VentasEmpresasAlmacenesYearsDto> list = empresaService.listAllVentasByEmpresaYearPorAlmacenAndCategoriaAndMarcaAndVehiculo(nameAlmacen, nameCategoria,nameMarca,nameVehiculo);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/empresaDTO/listAllVentasByEmpresaPorAlmacenAndCategoriaAndMarcaAndVehiculo/{nameAlmacen}/{nameCategoria}/{nameMarca}/{nameVehiculo}")
    public ResponseEntity<List<VentasCanalesEmpresaDto>> listAllVentasByEmpresaPorAlmacenAndCategoriaAndMarcaAndVehiculo(
            @PathVariable("nameAlmacen") List<String> nameAlmacen,
            @PathVariable("nameCategoria") List<String> nameCategoria,
            @PathVariable("nameMarca") List<String> nameMarca,
            @PathVariable("nameVehiculo") List<String> nameVehiculo
    ){
        try {
            List<VentasCanalesEmpresaDto> list = empresaService.listAllVentasByEmpresaPorAlmacenAndCategoriaAndMarcaAndVehiculo(nameAlmacen, nameCategoria,nameMarca,nameVehiculo);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/empresaDTO/listAllVentasByEmpresaPorTipoAndMarcasAndVehiculo/{nameAlmacen}/{nameCategoria}/{nameMarca}/{nameVehiculo}")
    public ResponseEntity<List<VentasEmpresaAlmacenTipoDateDto>> listAllVentasByEmpresaPorTipoAndMarcasAndVehiculo(
            @PathVariable("nameAlmacen") List<String> nameAlmacen,
            @PathVariable("nameCategoria") List<String> nameCategoria,
            @PathVariable("nameMarca") List<String> nameMarca,
            @PathVariable("nameVehiculo") List<String> nameVehiculo
    ){
        try {
            List<VentasEmpresaAlmacenTipoDateDto> list = empresaService.listAllVentasByEmpresaPorTipoAndMarcasAndVehiculo(nameAlmacen, nameCategoria,nameMarca,nameVehiculo);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // ANOTHER 4
    @GetMapping("/empresaDTO/listAllVentasByEmpresaColorPorAlmacenAndCategoriaAndCanal/{nameAlmacen}/{nameCategoria}/{nameCanal}")
    public ResponseEntity<List<VentasEmpresasAlmacenColores>> listAllVentasByEmpresaColorPorAlmacenAndCategoriaAndCanal(
            @PathVariable("nameAlmacen") List<String> nameAlmacen,
            @PathVariable("nameCategoria") List<String> nameCategoria,
            @PathVariable("nameCanal") List<String> nameCanal
    ){
        try {
            List<VentasEmpresasAlmacenColores> list = empresaService.listAllVentasByEmpresaColorPorAlmacenAndCategoriaAndCanal(nameAlmacen, nameCategoria,nameCanal);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/empresaDTO/listAllVentasByEmpresaYearPorAlmacenAndCategoriaAndCanal/{nameAlmacen}/{nameCategoria}/{nameCanal}")
    public ResponseEntity<List<VentasEmpresasAlmacenesYearsDto>> listAllVentasByEmpresaYearPorAlmacenAndCategoriaAndCanal(
            @PathVariable("nameAlmacen") List<String> nameAlmacen,
            @PathVariable("nameCategoria") List<String> nameCategoria,
            @PathVariable("nameCanal") List<String> nameCanal
    ){
        try {
            List<VentasEmpresasAlmacenesYearsDto> list = empresaService.listAllVentasByEmpresaYearPorAlmacenAndCategoriaAndCanal(nameAlmacen, nameCategoria,nameCanal);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/empresaDTO/listAllVentasByEmpresaPorAlmacenAndCategoriaAndCanal/{nameAlmacen}/{nameCategoria}/{nameCanal}")
    public ResponseEntity<List<VentasCanalesEmpresaDto>> listAllVentasByEmpresaPorAlmacenAndCategoriaAndCanal(
            @PathVariable("nameAlmacen") List<String> nameAlmacen,
            @PathVariable("nameCategoria") List<String> nameCategoria,
            @PathVariable("nameCanal") List<String> nameCanal
    ){
        try {
            List<VentasCanalesEmpresaDto> list = empresaService.listAllVentasByEmpresaPorAlmacenAndCategoriaAndCanal(nameAlmacen, nameCategoria,nameCanal);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/empresaDTO/listAllVentasByEmpresaPorTipoAndCanal/{nameAlmacen}/{nameCategoria}/{nameCanal}")
    public ResponseEntity<List<VentasEmpresaAlmacenTipoDateDto>> listAllVentasByEmpresaPorTipoAndCanal(
            @PathVariable("nameAlmacen") List<String> nameAlmacen,
            @PathVariable("nameCategoria") List<String> nameCategoria,
            @PathVariable("nameCanal") List<String> nameCanal
    ){
        try {
            List<VentasEmpresaAlmacenTipoDateDto> list = empresaService.listAllVentasByEmpresaPorTipoAndCanal(nameAlmacen, nameCategoria,nameCanal);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // ANOTHER 5 -------------------------------
    @GetMapping("/empresaDTO/listAllVentasByEmpresaColorPorAlmacenMarcaAndCanal/{nameAlmacen}/{nameMarca}/{nameCanal}")
    public ResponseEntity<List<VentasEmpresasAlmacenColores>> listAllVentasByEmpresaColorPorAlmacenMarcaAndCanal(
            @PathVariable("nameAlmacen") List<String> nameAlmacen,
            @PathVariable("nameMarca") List<String> nameMarca,
            @PathVariable("nameCanal") List<String> nameCanal
    ){
        try {
            System.out.println("CONTROLLER - ENTRO A CANLES Y MARCAS POR COLORES");
            List<VentasEmpresasAlmacenColores> list = empresaService.listAllVentasByEmpresaColorPorAlmacenMarcaAndCanal(nameAlmacen,nameMarca,nameCanal);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/empresaDTO/listAllVentasByEmpresaYearPorAlmacenMarcaAndCanal/{nameAlmacen}/{nameMarca}/{nameCanal}")
    public ResponseEntity<List<VentasEmpresasAlmacenesYearsDto>> listAllVentasByEmpresaYearPorAlmacenMarcaAndCanal(
            @PathVariable("nameAlmacen") List<String> nameAlmacen,
            @PathVariable("nameMarca") List<String> nameMarca,
            @PathVariable("nameCanal") List<String> nameCanal
    ){
        try {
            List<VentasEmpresasAlmacenesYearsDto> list = empresaService.listAllVentasByEmpresaYearPorAlmacenMarcaAndCanal(nameAlmacen,nameMarca,nameCanal);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/empresaDTO/listAllVentasByEmpresaPorAlmacenMarcaAndCanal/{nameAlmacen}/{nameMarca}/{nameCanal}")
    public ResponseEntity<List<VentasCanalesEmpresaDto>> listAllVentasByEmpresaPorAlmacenMarcaAndCanal(
            @PathVariable("nameAlmacen") List<String> nameAlmacen,
            @PathVariable("nameMarca") List<String> nameMarca,
            @PathVariable("nameCanal") List<String> nameCanal
    ){
        try {
            List<VentasCanalesEmpresaDto> list = empresaService.listAllVentasByEmpresaPorAlmacenMarcaAndCanal(nameAlmacen,nameMarca,nameCanal);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/empresaDTO/listAllVentasByEmpresaPorMarcasAndCanal/{nameAlmacen}/{nameMarca}/{nameCanal}")
    public ResponseEntity<List<VentasEmpresaAlmacenTipoDateDto>> listAllVentasByEmpresaPorMarcasAndCanal(
            @PathVariable("nameAlmacen") List<String> nameAlmacen,
            @PathVariable("nameMarca") List<String> nameMarca,
            @PathVariable("nameCanal") List<String> nameCanal
    ){
        try {
            List<VentasEmpresaAlmacenTipoDateDto> list = empresaService.listAllVentasByEmpresaPorMarcasAndCanal(nameAlmacen,nameMarca,nameCanal);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // ANOTHER 6
    @GetMapping("/empresaDTO/listAllVentasByEmpresaColorPorAlmacenCanalAndVehiculo/{nameAlmacen}/{nameCanal}/{nameVehiculo}")
    public ResponseEntity<List<VentasEmpresasAlmacenColores>> listAllVentasByEmpresaColorPorAlmacenCanalAndVehiculo(
            @PathVariable("nameAlmacen") List<String> nameAlmacen,
            @PathVariable("nameCanal") List<String> nameCanal,
            @PathVariable("nameVehiculo") List<String> nameVehiculo
    ){
        try {
            List<VentasEmpresasAlmacenColores> list = empresaService.listAllVentasByEmpresaColorPorAlmacenCanalAndVehiculo(nameAlmacen,nameCanal,nameVehiculo);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/empresaDTO/listAllVentasByEmpresaYearPorAlmacenAndCanalAndVehiculo/{nameAlmacen}/{nameCanal}/{nameVehiculo}")
    public ResponseEntity<List<VentasEmpresasAlmacenesYearsDto>> listAllVentasByEmpresaYearPorAlmacenAndCanalAndVehiculo(
            @PathVariable("nameAlmacen") List<String> nameAlmacen,
            @PathVariable("nameCanal") List<String> nameCanal,
            @PathVariable("nameVehiculo") List<String> nameVehiculo
    ){
        try {
            List<VentasEmpresasAlmacenesYearsDto> list = empresaService.listAllVentasByEmpresaYearPorAlmacenAndCanalAndVehiculo(nameAlmacen,nameCanal,nameVehiculo);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/empresaDTO/listAllVentasByEmpresaPorAlmacenAndCanalAndVehiculo/{nameAlmacen}/{nameCanal}/{nameVehiculo}")
    public ResponseEntity<List<VentasCanalesEmpresaDto>> listAllVentasByEmpresaPorAlmacenAndCanalAndVehiculo(
            @PathVariable("nameAlmacen") List<String> nameAlmacen,
            @PathVariable("nameCanal") List<String> nameCanal,
            @PathVariable("nameVehiculo") List<String> nameVehiculo
    ){
        try {
            List<VentasCanalesEmpresaDto> list = empresaService.listAllVentasByEmpresaPorAlmacenMarcaAndCanal(nameAlmacen,nameCanal,nameVehiculo);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/empresaDTO/listAllVentasByEmpresaPorCanalAndVehiculo/{nameAlmacen}/{nameCanal}/{nameVehiculo}")
    public ResponseEntity<List<VentasEmpresaAlmacenTipoDateDto>> listAllVentasByEmpresaPorCanalAndVehiculo(
            @PathVariable("nameAlmacen") List<String> nameAlmacen,
            @PathVariable("nameCanal") List<String> nameCanal,
            @PathVariable("nameVehiculo") List<String> nameVehiculo
    ){
        try {
            List<VentasEmpresaAlmacenTipoDateDto> list = empresaService.listAllVentasByEmpresaPorCanalAndVehiculo(nameAlmacen,nameCanal,nameVehiculo);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ANOTHER 7
    @GetMapping("/empresaDTO/listAllVentasByEmpresaColorPorAlmacenAndCategoriaAndVehiculo/{nameAlmacen}/{nameCategoria}/{nameVehiculo}")
    public ResponseEntity<List<VentasEmpresasAlmacenColores>> listAllVentasByEmpresaColorPorAlmacenAndCategoriaAndVehiculo(
            @PathVariable("nameAlmacen") List<String> nameAlmacen,
            @PathVariable("nameCategoria") List<String> nameCategoria,
            @PathVariable("nameVehiculo") List<String> nameVehiculo
    ){
        try {
            List<VentasEmpresasAlmacenColores> list = empresaService.listAllVentasByEmpresaColorPorAlmacenAndCategoriaAndVehiculo(nameAlmacen,nameCategoria,nameVehiculo);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/empresaDTO/listAllVentasByEmpresaYearPorAlmacenAndCategoriaAndVehiculo/{nameAlmacen}/{nameCategoria}/{nameVehiculo}")
    public ResponseEntity<List<VentasEmpresasAlmacenesYearsDto>> listAllVentasByEmpresaYearPorAlmacenAndCategoriaAndVehiculo(
            @PathVariable("nameAlmacen") List<String> nameAlmacen,
            @PathVariable("nameCategoria") List<String> nameCategoria,
            @PathVariable("nameVehiculo") List<String> nameVehiculo
    ){
        try {
            List<VentasEmpresasAlmacenesYearsDto> list = empresaService.listAllVentasByEmpresaYearPorAlmacenAndCategoriaAndVehiculo(nameAlmacen,nameCategoria,nameVehiculo);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/empresaDTO/listAllVentasByEmpresaPorAlmacenAndCategoriaAndVehiculo/{nameAlmacen}/{nameCategoria}/{nameVehiculo}")
    public ResponseEntity<List<VentasCanalesEmpresaDto>> listAllVentasByEmpresaPorAlmacenAndCategoriaAndVehiculo(
            @PathVariable("nameAlmacen") List<String> nameAlmacen,
            @PathVariable("nameCategoria") List<String> nameCategoria,
            @PathVariable("nameVehiculo") List<String> nameVehiculo
    ){
        try {
            List<VentasCanalesEmpresaDto> list = empresaService.listAllVentasByEmpresaPorAlmacenAndCategoriaAndVehiculo(nameAlmacen,nameCategoria,nameVehiculo);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/empresaDTO/listAllVentasByEmpresaPorAlamcenesAndCateAndAndVehiculo/{nameAlmacen}/{nameCategoria}/{nameVehiculo}")
    public ResponseEntity<List<VentasEmpresaAlmacenTipoDateDto>> listAllVentasByEmpresaPorAlamcenesAndCateAndAndVehiculo(
            @PathVariable("nameAlmacen") List<String> nameAlmacen,
            @PathVariable("nameCategoria") List<String> nameCategoria,
            @PathVariable("nameVehiculo") List<String> nameVehiculo
    ){
        try {
            List<VentasEmpresaAlmacenTipoDateDto> list = empresaService.listAllVentasByEmpresaPorAlamcenesAndCateAndAndVehiculo(nameAlmacen,nameCategoria,nameVehiculo);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // ANOTHER 8
    @GetMapping("/empresaDTO/listAllVentasByEmpresaColorPorAlmacenAndMarcaAndVehiculo/{nameAlmacen}/{nameMarca}/{nameVehiculo}")
    public ResponseEntity<List<VentasEmpresasAlmacenColores>> listAllVentasByEmpresaColorPorAlmacenAndMarcaAndVehiculo(
            @PathVariable("nameAlmacen") List<String> nameAlmacen,
            @PathVariable("nameMarca") List<String> nameMarca,
            @PathVariable("nameVehiculo") List<String> nameVehiculo
    ){
        try {
            List<VentasEmpresasAlmacenColores> list = empresaService.listAllVentasByEmpresaColorPorAlmacenAndMarcaAndVehiculo(nameAlmacen,nameMarca,nameVehiculo);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/empresaDTO/listAllVentasByEmpresaYearPorAlmacenAndMarcaAndVehiculo/{nameAlmacen}/{nameMarca}/{nameVehiculo}")
    public ResponseEntity<List<VentasEmpresasAlmacenesYearsDto>> listAllVentasByEmpresaYearPorAlmacenAndMarcaAndVehiculo(
            @PathVariable("nameAlmacen") List<String> nameAlmacen,
            @PathVariable("nameMarca") List<String> nameMarca,
            @PathVariable("nameVehiculo") List<String> nameVehiculo
    ){
        try {
            List<VentasEmpresasAlmacenesYearsDto> list = empresaService.listAllVentasByEmpresaYearPorAlmacenAndMarcaAndVehiculo(nameAlmacen,nameMarca,nameVehiculo);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/empresaDTO/listAllVentasByEmpresaPorCanalesdMarcaAndVehiculo/{nameAlmacen}/{nameMarca}/{nameVehiculo}")
    public ResponseEntity<List<VentasCanalesEmpresaDto>> listAllVentasByEmpresaPorCanalesdMarcaAndVehiculo(
            @PathVariable("nameAlmacen") List<String> nameAlmacen,
            @PathVariable("nameMarca") List<String> nameMarca,
            @PathVariable("nameVehiculo") List<String> nameVehiculo
    ){
        try {
            List<VentasCanalesEmpresaDto> list = empresaService.listAllVentasByEmpresaPorCanalesdMarcaAndVehiculo(nameAlmacen,nameMarca,nameVehiculo);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/empresaDTO/listAllVentasByEmpresaPorAlamcenesAndMarcasAndVehiculo/{nameAlmacen}/{nameMarca}/{nameVehiculo}")
    public ResponseEntity<List<VentasEmpresaAlmacenTipoDateDto>> listAllVentasByEmpresaPorAlamcenesAndMarcasAndVehiculo(
            @PathVariable("nameAlmacen") List<String> nameAlmacen,
            @PathVariable("nameMarca") List<String> nameMarca,
            @PathVariable("nameVehiculo") List<String> nameVehiculo
    ){
        try {
            List<VentasEmpresaAlmacenTipoDateDto> list = empresaService.listAllVentasByEmpresaPorAlamcenesAndMarcasAndVehiculo(nameAlmacen,nameMarca,nameVehiculo);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // ANOTHER 9
    @GetMapping("/empresaDTO/listAllVentasByEmpresaColorPorAlmacenCanal/{nameAlmacen}/{nameCanal}")
    public ResponseEntity<List<VentasEmpresasAlmacenColores>> listAllVentasByEmpresaColorPorAlmacenCanal(
            @PathVariable("nameAlmacen") List<String> nameAlmacen,
            @PathVariable("nameCanal") List<String> nameCanal
    ){
        try {
            List<VentasEmpresasAlmacenColores> list = empresaService.listAllVentasByEmpresaColorPorAlmacenCanal(nameAlmacen,nameCanal);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/empresaDTO/listAllVentasByEmpresaYearPorAlmacenAndCanal/{nameAlmacen}/{nameCanal}")
    public ResponseEntity<List<VentasEmpresasAlmacenesYearsDto>> listAllVentasByEmpresaYearPorAlmacenAndCanal(
            @PathVariable("nameAlmacen") List<String> nameAlmacen,
            @PathVariable("nameCanal") List<String> nameCanal
    ){
        try {
            List<VentasEmpresasAlmacenesYearsDto> list = empresaService.listAllVentasByEmpresaYearPorAlmacenAndCanal(nameAlmacen,nameCanal);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/empresaDTO/listAllVentasByEmpresaPorAlmacenAndCanal/{nameAlmacen}/{nameCanal}")
    public ResponseEntity<List<VentasCanalesEmpresaDto>> listAllVentasByEmpresaPorAlmacenAndCanal(
            @PathVariable("nameAlmacen") List<String> nameAlmacen,
            @PathVariable("nameCanal") List<String> nameCanal
    ){
        try {
            List<VentasCanalesEmpresaDto> list = empresaService.listAllVentasByEmpresaPorAlmacenAndCanal(nameAlmacen,nameCanal);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/empresaDTO/listAllVentasByEmpresaPorCanal/{nameAlmacen}/{nameCanal}")
    public ResponseEntity<List<VentasEmpresaAlmacenTipoDateDto>> listAllVentasByEmpresaPorCanal(
            @PathVariable("nameAlmacen") List<String> nameAlmacen,
            @PathVariable("nameCanal") List<String> nameCanal
    ){
        try {
            List<VentasEmpresaAlmacenTipoDateDto> list = empresaService.listAllVentasByEmpresaPorCanal(nameAlmacen,nameCanal);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // ANOTHER 10
    @GetMapping("/empresaDTO/listAllVentasByEmpresaColorPorAlmacenAndVehiculo/{nameAlmacen}/{nameVehiculo}")
    public ResponseEntity<List<VentasEmpresasAlmacenColores>> listAllVentasByEmpresaColorPorAlmacenAndVehiculo(
            @PathVariable("nameAlmacen") List<String> nameAlmacen,
            @PathVariable("nameVehiculo") List<String> nameVehiculo
    ){
        try {
            List<VentasEmpresasAlmacenColores> list = empresaService.listAllVentasByEmpresaColorPorAlmacenAndVehiculo(nameAlmacen,nameVehiculo);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/empresaDTO/listAllVentasByEmpresaYearPorAlmacenAndVehiculo/{nameAlmacen}/{nameVehiculo}")
    public ResponseEntity<List<VentasEmpresasAlmacenesYearsDto>> listAllVentasByEmpresaYearPorAlmacenAndVehiculo(
            @PathVariable("nameAlmacen") List<String> nameAlmacen,
            @PathVariable("nameVehiculo") List<String> nameVehiculo
    ){
        try {
            List<VentasEmpresasAlmacenesYearsDto> list = empresaService.listAllVentasByEmpresaYearPorAlmacenAndVehiculo(nameAlmacen,nameVehiculo);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/empresaDTO/listAllVentasByEmpresaPorCanalesAndVehiculo/{nameAlmacen}/{nameVehiculo}")
    public ResponseEntity<List<VentasCanalesEmpresaDto>> listAllVentasByEmpresaPorCanalesAndVehiculo(
            @PathVariable("nameAlmacen") List<String> nameAlmacen,
            @PathVariable("nameVehiculo") List<String> nameVehiculo
    ){
        try {
            List<VentasCanalesEmpresaDto> list = empresaService.listAllVentasByEmpresaPorCanalesAndVehiculo(nameAlmacen,nameVehiculo);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/empresaDTO/listAllVentasByEmpresaPorAlamcenesAndVehiculo/{nameAlmacen}/{nameVehiculo}")
    public ResponseEntity<List<VentasEmpresaAlmacenTipoDateDto>> listAllVentasByEmpresaPorAlamcenesAndVehiculo(
            @PathVariable("nameAlmacen") List<String> nameAlmacen,
            @PathVariable("nameVehiculo") List<String> nameVehiculo
    ){
        try {
            List<VentasEmpresaAlmacenTipoDateDto> list = empresaService.listAllVentasByEmpresaPorAlamcenesAndVehiculo(nameAlmacen,nameVehiculo);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // FINIHSSSS SIUUUU
    @GetMapping("/empresaDTO/listAllVentasByEmpresaColorPorAlmacenAndMarca/{nameAlmacen}/{nameMarca}")
    public ResponseEntity<List<VentasEmpresasAlmacenColores>> listAllVentasByEmpresaColorPorAlmacenAndMarca(
            @PathVariable("nameAlmacen") List<String> nameAlmacen,
            @PathVariable("nameMarca") List<String> nameMarca
    ){
        try {
            List<VentasEmpresasAlmacenColores> list = empresaService.listAllVentasByEmpresaColorPorAlmacenAndMarca(nameAlmacen,nameMarca);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/empresaDTO/listAllVentasByEmpresaYearPorAlmacenAndMarca/{nameAlmacen}/{nameMarca}")
    public ResponseEntity<List<VentasEmpresasAlmacenesYearsDto>> listAllVentasByEmpresaYearPorAlmacenAndMarca(
            @PathVariable("nameAlmacen") List<String> nameAlmacen,
            @PathVariable("nameMarca") List<String> nameMarca
    ){
        try {
            List<VentasEmpresasAlmacenesYearsDto> list = empresaService.listAllVentasByEmpresaYearPorAlmacenAndMarca(nameAlmacen,nameMarca);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/empresaDTO/listAllVentasByEmpresaPorCanalesdMarca/{nameAlmacen}/{nameMarca}")
    public ResponseEntity<List<VentasCanalesEmpresaDto>> listAllVentasByEmpresaPorCanalesdMarca(
            @PathVariable("nameAlmacen") List<String> nameAlmacen,
            @PathVariable("nameMarca") List<String> nameMarca
    ){
        try {
            List<VentasCanalesEmpresaDto> list = empresaService.listAllVentasByEmpresaPorCanalesdMarca(nameAlmacen,nameMarca);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/empresaDTO/listAllVentasByEmpresaPorAlamcenesAndMarcas/{nameAlmacen}/{nameMarca}")
    public ResponseEntity<List<VentasEmpresaAlmacenTipoDateDto>> listAllVentasByEmpresaPorAlamcenesAndMarcas(
            @PathVariable("nameAlmacen") List<String> nameAlmacen,
            @PathVariable("nameMarca") List<String> nameMarca
    ){
        try {
            List<VentasEmpresaAlmacenTipoDateDto> list = empresaService.listAllVentasByEmpresaPorAlamcenesAndMarcas(nameAlmacen,nameMarca);
            return new ResponseEntity<>(list, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    // SIUUUUUUUUUUUUUUUUU
    // LAS 15 ---------------------------------------------------------------


    @GetMapping("/empresaDTO/findAllVentasByEmpresaByAlmacen/{name}")
    public ResponseEntity<List<VentasAlmacenEmpresaDto>> findAllVentasByEmpresaByAlmacen(@PathVariable("name") String name){
        try {
            return new ResponseEntity<>(empresaService.listAllVentasAlmacenByEmpresa(name), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/empresaDTO/findAllVentasByEmpresaByAlmacenAndTipoCarro/{name}/{tipo}")
    public ResponseEntity<List<VentasAlmacenEmpresaDto>> findAllVentasByEmpresaByAlmacenAndTipoCarro(@PathVariable("name") String name,@PathVariable("tipo") String tipo){
        try {
            return new ResponseEntity<>(empresaService.listAllVentasAlmacenByEmpresaByTipoCarro(name,tipo), HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/empresa/findbyId/{id}")
    public ResponseEntity<?> getAreaById(@PathVariable("id") Integer id){
        try {
            Empresa empresa = empresaService.findById(id);
            if(empresa != null){
                return new ResponseEntity<>(empresa, HttpStatus.OK);
            }
            return new ResponseEntity<>("NO ENCONTRADA",HttpStatus.NOT_FOUND);

        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/empresa/save")
    public ResponseEntity<Empresa> saveArea(@RequestBody Empresa empresa){
        try {
            return new ResponseEntity<>(empresaService.save(empresa), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/empresa/actualizar/{id}")
    public ResponseEntity<Empresa> actualizarArea(@PathVariable Integer id, @RequestBody Empresa empresa) {
        try {
            if (empresaService.findById(id) == null) {
                return ResponseEntity.notFound().build();
            }
            empresa.setNombreEmpresa(empresa.getNombreEmpresa());
            empresa.setObjetivo(empresa.getObjetivo());
            return new ResponseEntity<>(empresaService.save(empresa), HttpStatus.CREATED);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


}
