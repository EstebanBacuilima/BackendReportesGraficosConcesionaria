package com.edu.proyect.Facturacion.service;

import com.edu.proyect.Facturacion.Payloads.*;
import com.edu.proyect.Facturacion.models.Empresa;
import com.edu.proyect.Facturacion.models.TipoVenta;
import com.edu.proyect.Facturacion.repository.EmpresaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class EmpresaServiceImpl extends GenericServiceImpl<Empresa, Integer> implements EmpresaService {

    @Autowired
    EmpresaRepository empresaRepository;

    @Override
    public CrudRepository<Empresa, Integer> getDao() {
        return empresaRepository;
    }

    @Override
    public List<Empresa> searchByName(String texto) {
        return empresaRepository.searchByName(texto);
    }

    @Override
    public List<VentasEmpresasDto> listAllEmpresasAndVentas() {
        return empresaRepository.listAllEmpresasAndVentas();
    }

    @Override
    public List<VentasEmpresasDto> listAllEmpresasAndVentasByDates(LocalDate dateOne, LocalDate dateTwo) {
        return empresaRepository.listAllEmpresasAndVentasByDates(dateOne,dateTwo);
    }

    @Override
    public List<VentasCanalesEmpresaDto> listAllCanalesVentasByEmpresa(String nameEmpresa) {
        return empresaRepository.listAllCanalesVentasByEmpresa(nameEmpresa);
    }

    @Override
    public List<VentasAlmacenEmpresaDto> listAllVentasAlmacenByEmpresa(String nameEmpresa) {
        return empresaRepository.listAllVentasAlmacenByEmpresa(nameEmpresa);
    }

    @Override
    public List<VentasAlmacenEmpresaDto> listAllVentasAlmacenByEmpresaByTipoCarro(String nameEmpresa,String tipoCarro) {
        return empresaRepository.listAllVentasAlmacenByEmpresaByTipoCarro(nameEmpresa,tipoCarro);
    }

    @Override
    public List<VentasEmpresaAlmacenTipoDateDto> listAllVentasByEmpresaPorTipoAndDate(String nameAlmacen, String tipoCarro) {
        return empresaRepository.listAllVentasByEmpresaPorTipoAndDate(nameAlmacen,tipoCarro);
    }

    @Override
    public Page<Empresa> findByAll(Pageable pageable) {
        return empresaRepository.findAll(pageable);
    }

    // EMPRESA

    @Override
    public List<VentasEmpresasAlmacenColores> listAllVentasColorByEmpresa(List<String> nameEmpresa) {
        return empresaRepository.listAllVentasColorByEmpresa(nameEmpresa);
    }

    @Override
    public List<VentasEmpresasAlmacenesYearsDto> listAllVentasByEmpresaYear(List<String> nameEmpresa) {
        return empresaRepository.listAllVentasByEmpresaYear(nameEmpresa);
    }

    @Override
    public List<VentasCanalesEmpresaDto> listAllVentasCanalesByEmpresa(List<String> nameEmpresa) {
        return empresaRepository.listAllVentasCanalesByEmpresa(nameEmpresa);
    }

    @Override
    public List<VentasEmpresaAlmacenTipoDateDto> listAllVentasByEmpresa(List<String> nameEmpresa) {
        return empresaRepository.listAllVentasByEmpresa(nameEmpresa);
    }

    // ALMACEN

    @Override
    public List<VentasEmpresasAlmacenColores> listAllVentasByEmpresaColorPorAlmacen(List<String> nameAlmacen) {
        return empresaRepository.listAllVentasByEmpresaColorPorAlmacen(nameAlmacen);
    }

    @Override
    public List<VentasEmpresasAlmacenesYearsDto> listAllVentasByEmpresaYearPorAlmacen(List<String> nameAlmacen) {
        return empresaRepository.listAllVentasByEmpresaYearPorAlmacen(nameAlmacen);
    }

    @Override
    public List<VentasCanalesEmpresaDto> listAllVentasCanalesByEmpresaPorAlmacen(List<String> nameAlmacen) {
        return empresaRepository.listAllVentasCanalesByEmpresaPorAlmacen(nameAlmacen);
    }

    @Override
    public List<VentasEmpresaAlmacenTipoDateDto> listAllVentasByEmpresaPorAlmacen(List<String> nameAlmacen) {
        return empresaRepository.listAllVentasByEmpresaPorAlmacen(nameAlmacen);
    }


    // CONSULTAS CATERIAS, TIPOS, VEHICULOS, MARCAS


    @Override
    public List<CategoriaDto> getAllCategoriasPorEmpresas(String nameEmpresa) {
        return empresaRepository.getAllCategoriasPorEmpresas(nameEmpresa);
    }

    @Override
    public List<TipoVentaDto> getAllTiposVehiculosPorEmpresas(String nameEmpresa2) {
        return empresaRepository.getAllTiposVehiculosPorEmpresas(nameEmpresa2);
    }

    @Override
    public List<MarcasDto> getAllMarcasPorEmpresas(String nameEmpresa3) {
        return empresaRepository.getAllMarcasPorEmpresas(nameEmpresa3);
    }

    @Override
    public List<VehiculosDto> getAllVehiculosPorEmpresas(String nameEmpresa) {
        return empresaRepository.getAllVehiculosPorEmpresas(nameEmpresa);
    }

    @Override
    public Page<AlmacenesDto> getAllAlmacenesPorEmpresas(String nameEmpresa,Pageable pageable) {
        return empresaRepository.getAllAlmacenesPorEmpresas(nameEmpresa,pageable);
    }

    @Override
    public List<VentasEmpresaAlmacenTipoDateDto> listAllVentasByEmpresaPorTipoAndMarcas(List<String> nameAlmacen, List<String> nameCategoria, List<String> nameMarca) {
        return empresaRepository.listAllVentasByEmpresaPorTipoAndMarcas(nameAlmacen,nameCategoria,nameMarca);
    }

    @Override
    public List<VentasEmpresaAlmacenTipoDateDto> listAllVentasByEmpresaPorTipoAndMarcasAndCanal(List<String> nameAlmacen, List<String> nameCategoria, List<String> nameMarca, List<String> nameCanal) {
        return empresaRepository.listAllVentasByEmpresaPorTipoAndMarcasAndCanal(nameAlmacen,nameCategoria,nameMarca,nameCanal);
    }

    @Override
    public List<VentasEmpresaAlmacenTipoDateDto> listAllVentasByEmpresaPorTipoAndMarcasAndCanalAndVehiculo(List<String> nameAlmacen, List<String> nameCategoria, List<String> nameMarca, List<String> nameCanal,List<String> nameVehiculo) {
        return empresaRepository.listAllVentasByEmpresaPorTipoAndMarcasAndCanalAndVehiculo(nameAlmacen,nameCategoria,nameMarca,nameCanal,nameVehiculo);
    }

    // CANALES

    @Override
    public List<VentasCanalesEmpresaDto> listAllVentasByEmpresaPorAlmacenAndCategoria(List<String> nameAlmacen, List<String> nameCategoria) {
        return empresaRepository.listAllVentasByEmpresaPorAlmacenAndCategoria(nameAlmacen,nameCategoria);
    }

    @Override
    public List<VentasCanalesEmpresaDto> listAllVentasByEmpresaPorAlmacenAndCategoriaAndMarca(List<String> nameAlmacen, List<String> nameCategoria, List<String> nameMarca) {
        return empresaRepository.listAllVentasByEmpresaPorAlmacenAndCategoriaAndMarca(nameAlmacen,nameCategoria,nameMarca);
    }

    @Override
    public List<VentasCanalesEmpresaDto> listAllVentasByEmpresaPorAlmacenAndCategoriaAndMarcaAndCanal(List<String> nameAlmacen, List<String> nameCategoria, List<String> nameMarca, List<String> nameCanal) {
        return empresaRepository.listAllVentasByEmpresaPorAlmacenAndCategoriaAndMarcaAndCanal(nameAlmacen,nameCategoria,nameMarca,nameCanal);
    }

    @Override
    public List<VentasCanalesEmpresaDto> listAllVentasByEmpresaPorAlmacenAndCategoriaAndMarcaAndCanalAndVehiculo(List<String> nameAlmacen, List<String> nameCategoria, List<String> nameMarca, List<String> nameCanal,List<String> nameVehiculo) {
        return empresaRepository.listAllVentasByEmpresaPorAlmacenAndCategoriaAndMarcaAndCanalAndVehiculo(nameAlmacen,nameCategoria,nameMarca,nameCanal,nameVehiculo);
    }

    // COLOR

    @Override
    public List<VentasEmpresasAlmacenColores> listAllVentasByEmpresaColorPorAlmacenAndCategoria(List<String> nameAlmacen, List<String> nameCategoria) {
        return empresaRepository.listAllVentasByEmpresaColorPorAlmacenAndCategoria(nameAlmacen,nameCategoria);
    }

    @Override
    public List<VentasEmpresasAlmacenColores> listAllVentasByEmpresaColorPorAlmacenAndCategoriaAndMarca(List<String> nameAlmacen, List<String> nameCategoria, List<String> nameMarca) {
        return empresaRepository.listAllVentasByEmpresaColorPorAlmacenAndCategoriaAndMarca(nameAlmacen,nameCategoria,nameMarca);
    }

    @Override
    public List<VentasEmpresasAlmacenColores> listAllVentasByEmpresaColorPorAlmacenAndCategoriaAndMarcaAndCanal(List<String> nameAlmacen, List<String> nameCategoria, List<String> nameMarca, List<String> nameCanal) {
        return empresaRepository.listAllVentasByEmpresaColorPorAlmacenAndCategoriaAndMarcaAndCanal(nameAlmacen,nameCategoria,nameMarca,nameCanal);
    }

    @Override
    public List<VentasEmpresasAlmacenColores> listAllVentasByEmpresaColorPorAlmacenAndCategoriaAndMarcaAndCanalAndVehiculo(List<String> nameAlmacen, List<String> nameCategoria, List<String> nameMarca, List<String> nameCanal,List<String> nameVehiculo) {
        return empresaRepository.listAllVentasByEmpresaColorPorAlmacenAndCategoriaAndMarcaAndCanalAndVehiculo(nameAlmacen,nameCategoria,nameMarca,nameCanal,nameVehiculo);
    }


    // YEARS

    @Override
    public List<VentasEmpresasAlmacenesYearsDto> listAllVentasByEmpresaYearPorAlmacenAndCategoria(List<String> nameAlmacen, List<String> nameCategoria) {
        return empresaRepository.listAllVentasByEmpresaYearPorAlmacenAndCategoria(nameAlmacen,nameCategoria);
    }

    @Override
    public List<VentasEmpresasAlmacenesYearsDto> listAllVentasByEmpresaYearPorAlmacenAndCategoriaAndMarca(List<String> nameAlmacen, List<String> nameCategoria, List<String> nameMarca) {
        return empresaRepository.listAllVentasByEmpresaYearPorAlmacenAndCategoriaAndMarca(nameAlmacen,nameCategoria,nameMarca);
    }

    @Override
    public List<VentasEmpresasAlmacenesYearsDto> listAllVentasByEmpresaYearPorAlmacenAndCategoriaAndMarcaAndCanal(List<String> nameAlmacen, List<String> nameCategoria, List<String> nameMarca, List<String> nameCanal) {
        return empresaRepository.listAllVentasByEmpresaYearPorAlmacenAndCategoriaAndMarcaAndCanal(nameAlmacen,nameCategoria,nameMarca,nameCanal);
    }

    @Override
    public List<VentasEmpresasAlmacenesYearsDto> listAllVentasByEmpresaYearPorAlmacenAndCategoriaAndMarcaAndCanalAndVehiculo(List<String> nameAlmacen, List<String> nameCategoria, List<String> nameMarca, List<String> nameCanal,List<String> nameVehiculo) {
        return empresaRepository.listAllVentasByEmpresaYearPorAlmacenAndCategoriaAndMarcaAndCanalAndVehiculo(nameAlmacen,nameCategoria,nameMarca,nameCanal,nameVehiculo);
    }

    // AMOTHER ONE
    @Override
    public List<VentasEmpresasAlmacenColores> listAllVentasByEmpresaColorPorAlmacenAndCategoriaAndCanalAndVehiculo(List<String> nameAlmacen, List<String> nameCategoria, List<String> nameCanal, List<String> nameVehiculo) {
        return empresaRepository.listAllVentasByEmpresaColorPorAlmacenAndCategoriaAndCanalAndVehiculo(nameAlmacen,nameCategoria,nameCanal,nameVehiculo);
    }

    @Override
    public List<VentasEmpresasAlmacenesYearsDto> listAllVentasByEmpresaYearPorAlmacenAndCategoriaAndCanalAndVehiculo(List<String> nameAlmacen, List<String> nameCategoria, List<String> nameCanal, List<String> nameVehiculo) {
        return empresaRepository.listAllVentasByEmpresaYearPorAlmacenAndCategoriaAndCanalAndVehiculo(nameAlmacen,nameCategoria,nameCanal,nameVehiculo);
    }

    @Override
    public List<VentasCanalesEmpresaDto> listAllVentasByEmpresaPorAlmacenAndCategoriaAndCanalAndVehiculo(List<String> nameAlmacen, List<String> nameCategoria, List<String> nameCanal, List<String> nameVehiculo) {
        return empresaRepository.listAllVentasByEmpresaPorAlmacenAndCategoriaAndCanalAndVehiculo(nameAlmacen,nameCategoria,nameCanal,nameVehiculo);
    }

    @Override
    public List<VentasEmpresaAlmacenTipoDateDto> listAllVentasByEmpresaPorTipoAndAndCanalAndVehiculo(List<String> nameAlmacen, List<String> nameCategoria, List<String> nameCanal, List<String> nameVehiculo) {
        return empresaRepository.listAllVentasByEmpresaPorTipoAndAndCanalAndVehiculo(nameAlmacen,nameCategoria,nameCanal,nameVehiculo);
    }

    // ANOTHER 2
    @Override
    public List<VentasEmpresasAlmacenColores> listAllVentasByEmpresaColorPorAlmacenMarcaAndCanalAndVehiculo(List<String> nameAlmacen, List<String> nameCanal, List<String> nameVehiculo, List<String> nameMarca) {
        return empresaRepository.listAllVentasByEmpresaColorPorAlmacenMarcaAndCanalAndVehiculo(nameAlmacen,nameCanal,nameVehiculo,nameMarca);
    }

    @Override
    public List<VentasEmpresasAlmacenesYearsDto> listAllVentasByEmpresaYearPorAlmacenMarcaAndCanalAndVehiculo(List<String> nameAlmacen, List<String> nameCanal, List<String> nameVehiculo, List<String> nameMarca) {
        return empresaRepository.listAllVentasByEmpresaYearPorAlmacenMarcaAndCanalAndVehiculo(nameAlmacen,nameCanal,nameVehiculo,nameMarca);
    }

    @Override
    public List<VentasCanalesEmpresaDto> listAllVentasByEmpresaPorAlmacenMarcaAndCanalAndVehiculo(List<String> nameAlmacen, List<String> nameCanal, List<String> nameVehiculo, List<String> nameMarca) {
        return empresaRepository.listAllVentasByEmpresaPorAlmacenMarcaAndCanalAndVehiculo(nameAlmacen,nameCanal,nameVehiculo,nameMarca);
    }

    @Override
    public List<VentasEmpresaAlmacenTipoDateDto> listAllVentasByEmpresaPorMarcasAndCanalAndVehiculo(List<String> nameAlmacen, List<String> nameCanal, List<String> nameVehiculo, List<String> nameMarca) {
        return empresaRepository.listAllVentasByEmpresaPorMarcasAndCanalAndVehiculo(nameAlmacen,nameCanal,nameVehiculo,nameMarca);
    }

    // ANOTHER 3

    @Override
    public List<VentasEmpresasAlmacenColores> listAllVentasByEmpresaColorPorAlmacenAndCategoriaAndMarcaAndVehiculo(List<String> nameAlmacen, List<String> nameCategoria, List<String> nameMarca, List<String> nameVehiculo) {
        return empresaRepository.listAllVentasByEmpresaColorPorAlmacenAndCategoriaAndMarcaAndVehiculo(nameAlmacen,nameCategoria,nameMarca,nameVehiculo);
    }

    @Override
    public List<VentasEmpresasAlmacenesYearsDto> listAllVentasByEmpresaYearPorAlmacenAndCategoriaAndMarcaAndVehiculo(List<String> nameAlmacen, List<String> nameCategoria, List<String> nameMarca, List<String> nameVehiculo) {
        return empresaRepository.listAllVentasByEmpresaYearPorAlmacenAndCategoriaAndMarcaAndVehiculo(nameAlmacen,nameCategoria,nameMarca,nameVehiculo);
    }

    @Override
    public List<VentasCanalesEmpresaDto> listAllVentasByEmpresaPorAlmacenAndCategoriaAndMarcaAndVehiculo(List<String> nameAlmacen, List<String> nameCategoria, List<String> nameMarca, List<String> nameVehiculo) {
        return empresaRepository.listAllVentasByEmpresaPorAlmacenAndCategoriaAndMarcaAndVehiculo(nameAlmacen,nameCategoria,nameMarca,nameVehiculo);
    }

    @Override
    public List<VentasEmpresaAlmacenTipoDateDto> listAllVentasByEmpresaPorTipoAndMarcasAndVehiculo(List<String> nameAlmacen, List<String> nameCategoria, List<String> nameMarca, List<String> nameVehiculo) {
        return empresaRepository.listAllVentasByEmpresaPorTipoAndMarcasAndVehiculo(nameAlmacen,nameCategoria,nameMarca,nameVehiculo);
    }

    // ANOTHER 5

    @Override
    public List<VentasEmpresasAlmacenColores> listAllVentasByEmpresaColorPorAlmacenAndCategoriaAndCanal(List<String> nameAlmacen, List<String> nameCategoria, List<String> nameCanal) {
        return empresaRepository.listAllVentasByEmpresaColorPorAlmacenAndCategoriaAndCanal(nameAlmacen,nameCategoria,nameCanal);
    }

    @Override
    public List<VentasEmpresasAlmacenesYearsDto> listAllVentasByEmpresaYearPorAlmacenAndCategoriaAndCanal(List<String> nameAlmacen, List<String> nameCategoria, List<String> nameCanal) {
        return empresaRepository.listAllVentasByEmpresaYearPorAlmacenAndCategoriaAndCanal(nameAlmacen,nameCategoria,nameCanal);
    }

    @Override
    public List<VentasCanalesEmpresaDto> listAllVentasByEmpresaPorAlmacenAndCategoriaAndCanal(List<String> nameAlmacen, List<String> nameCategoria, List<String> nameCanal) {
        return empresaRepository.listAllVentasByEmpresaPorAlmacenAndCategoriaAndCanal(nameAlmacen,nameCategoria,nameCanal);
    }

    @Override
    public List<VentasEmpresaAlmacenTipoDateDto> listAllVentasByEmpresaPorTipoAndCanal(List<String> nameAlmacen, List<String> nameCategoria, List<String> nameCanal) {
        return empresaRepository.listAllVentasByEmpresaPorTipoAndCanal(nameAlmacen,nameCategoria,nameCanal);
    }

    // ANOTHER 6 --------------------------------------------------------

    @Override
    public List<VentasEmpresasAlmacenColores> listAllVentasByEmpresaColorPorAlmacenMarcaAndCanal(List<String> nameAlmacen,List<String> nameMarca, List<String> nameCanal) {
        System.out.println("SERVICE - ENTRO A CANLES Y MARCAS POR COLORES");
        return empresaRepository.listAllVentasByEmpresaColorPorAlmacenMarcaAndCanal(nameAlmacen,nameMarca,nameCanal);
    }

    @Override
    public List<VentasEmpresasAlmacenesYearsDto> listAllVentasByEmpresaYearPorAlmacenMarcaAndCanal(List<String> nameAlmacen,List<String> nameMarca, List<String> nameCanal) {
        return empresaRepository.listAllVentasByEmpresaYearPorAlmacenMarcaAndCanal(nameAlmacen,nameMarca,nameCanal);
    }

    @Override
    public List<VentasCanalesEmpresaDto> listAllVentasByEmpresaPorAlmacenMarcaAndCanal(List<String> nameAlmacen,List<String> nameMarca, List<String> nameCanal) {
        return empresaRepository.listAllVentasByEmpresaPorAlmacenMarcaAndCanal(nameAlmacen,nameMarca,nameCanal);
    }

    @Override
    public List<VentasEmpresaAlmacenTipoDateDto> listAllVentasByEmpresaPorMarcasAndCanal(List<String> nameAlmacen,List<String> nameMarca, List<String> nameCanal) {
        return empresaRepository.listAllVentasByEmpresaPorMarcasAndCanal(nameAlmacen,nameMarca,nameCanal);
    }

    // ANOTHER 7

    @Override
    public List<VentasEmpresasAlmacenColores> listAllVentasByEmpresaColorPorAlmacenCanalAndVehiculo(List<String> nameAlmacen,List<String> nameCanal, List<String> nameVehiculo) {
        return empresaRepository.listAllVentasByEmpresaColorPorAlmacenCanalAndVehiculo(nameAlmacen,nameCanal,nameVehiculo);
    }

    @Override
    public List<VentasEmpresasAlmacenesYearsDto> listAllVentasByEmpresaYearPorAlmacenAndCanalAndVehiculo(List<String> nameAlmacen,List<String> nameCanal, List<String> nameVehiculo) {
        return empresaRepository.listAllVentasByEmpresaYearPorAlmacenAndCanalAndVehiculo(nameAlmacen,nameCanal,nameVehiculo);
    }

    @Override
    public List<VentasCanalesEmpresaDto> listAllVentasByEmpresaPorAlmacenAndCanalAndVehiculo(List<String> nameAlmacen,List<String> nameCanal, List<String> nameVehiculo) {
        return empresaRepository.listAllVentasByEmpresaPorAlmacenAndCanalAndVehiculo(nameAlmacen,nameCanal,nameVehiculo);
    }

    @Override
    public List<VentasEmpresaAlmacenTipoDateDto> listAllVentasByEmpresaPorCanalAndVehiculo(List<String> nameAlmacen,List<String> nameCanal, List<String> nameVehiculo) {
        return empresaRepository.listAllVentasByEmpresaPorCanalAndVehiculo(nameAlmacen,nameCanal,nameVehiculo);
    }

    // ANOTHER 8

    @Override
    public List<VentasEmpresasAlmacenColores> listAllVentasByEmpresaColorPorAlmacenAndCategoriaAndVehiculo(List<String> nameAlmacen,List<String> nameCategoria, List<String> nameVehiculo) {
        return empresaRepository.listAllVentasByEmpresaColorPorAlmacenAndCategoriaAndVehiculo(nameAlmacen,nameCategoria,nameVehiculo);
    }

    @Override
    public List<VentasEmpresasAlmacenesYearsDto> listAllVentasByEmpresaYearPorAlmacenAndCategoriaAndVehiculo(List<String> nameAlmacen,List<String> nameCategoria, List<String> nameVehiculo) {
        return empresaRepository.listAllVentasByEmpresaYearPorAlmacenAndCategoriaAndVehiculo(nameAlmacen,nameCategoria,nameVehiculo);
    }

    @Override
    public List<VentasCanalesEmpresaDto> listAllVentasByEmpresaPorAlmacenAndCategoriaAndVehiculo(List<String> nameAlmacen,List<String> nameCategoria, List<String> nameVehiculo) {
        return empresaRepository.listAllVentasByEmpresaPorAlmacenAndCategoriaAndVehiculo(nameAlmacen,nameCategoria,nameVehiculo);
    }

    @Override
    public List<VentasEmpresaAlmacenTipoDateDto> listAllVentasByEmpresaPorAlamcenesAndCateAndAndVehiculo(List<String> nameAlmacen,List<String> nameCategoria, List<String> nameVehiculo) {
        return empresaRepository.listAllVentasByEmpresaPorAlamcenesAndCateAndAndVehiculo(nameAlmacen,nameCategoria,nameVehiculo);
    }

    // ANOTHER 9
    @Override
    public List<VentasEmpresasAlmacenColores> listAllVentasByEmpresaColorPorAlmacenAndMarcaAndVehiculo(List<String> nameAlmacen,List<String> nameMarca, List<String> nameVehiculo) {
        return empresaRepository.listAllVentasByEmpresaColorPorAlmacenAndMarcaAndVehiculo(nameAlmacen,nameMarca,nameVehiculo);
    }

    @Override
    public List<VentasEmpresasAlmacenesYearsDto> listAllVentasByEmpresaYearPorAlmacenAndMarcaAndVehiculo(List<String> nameAlmacen,List<String> nameMarca, List<String> nameVehiculo) {
        return empresaRepository.listAllVentasByEmpresaYearPorAlmacenAndMarcaAndVehiculo(nameAlmacen,nameMarca,nameVehiculo);
    }

    @Override
    public List<VentasCanalesEmpresaDto> listAllVentasByEmpresaPorCanalesdMarcaAndVehiculo(List<String> nameAlmacen,List<String> nameMarca, List<String> nameVehiculo) {
        return empresaRepository.listAllVentasByEmpresaPorCanalesdMarcaAndVehiculo(nameAlmacen,nameMarca,nameVehiculo);
    }

    @Override
    public List<VentasEmpresaAlmacenTipoDateDto> listAllVentasByEmpresaPorAlamcenesAndMarcasAndVehiculo(List<String> nameAlmacen,List<String> nameMarca, List<String> nameVehiculo) {
        return empresaRepository.listAllVentasByEmpresaPorAlamcenesAndMarcasAndVehiculo(nameAlmacen,nameMarca,nameVehiculo);
    }
    // ANOTHER 10

    @Override
    public List<VentasEmpresasAlmacenColores> listAllVentasByEmpresaColorPorAlmacenCanal(List<String> nameAlmacen,List<String> nameCanal) {
        return empresaRepository.listAllVentasByEmpresaColorPorAlmacenCanal(nameAlmacen,nameCanal);
    }


    @Override
    public List<VentasEmpresasAlmacenesYearsDto> listAllVentasByEmpresaYearPorAlmacenAndCanal(List<String> nameAlmacen,List<String> nameCanal) {
        return empresaRepository.listAllVentasByEmpresaYearPorAlmacenAndCanal(nameAlmacen,nameCanal);
    }

    @Override
    public List<VentasCanalesEmpresaDto> listAllVentasByEmpresaPorAlmacenAndCanal(List<String> nameAlmacen,List<String> nameCanal) {
        return empresaRepository.listAllVentasByEmpresaPorAlmacenAndCanal(nameAlmacen,nameCanal);
    }

    @Override
    public List<VentasEmpresaAlmacenTipoDateDto> listAllVentasByEmpresaPorCanal(List<String> nameAlmacen,List<String> nameCanal) {
        return empresaRepository.listAllVentasByEmpresaPorCanal(nameAlmacen,nameCanal);
    }

    // ANOTHER 11
    @Override
    public List<VentasEmpresasAlmacenColores> listAllVentasByEmpresaColorPorAlmacenAndVehiculo(List<String> nameAlmacen,List<String> nameVehiculo) {
        return empresaRepository.listAllVentasByEmpresaColorPorAlmacenAndVehiculo(nameAlmacen,nameVehiculo);
    }

    @Override
    public List<VentasEmpresasAlmacenesYearsDto> listAllVentasByEmpresaYearPorAlmacenAndVehiculo(List<String> nameAlmacen,List<String> nameVehiculo) {
        return empresaRepository.listAllVentasByEmpresaYearPorAlmacenAndVehiculo(nameAlmacen,nameVehiculo);
    }

    @Override
    public List<VentasCanalesEmpresaDto> listAllVentasByEmpresaPorCanalesAndVehiculo(List<String> nameAlmacen,List<String> nameVehiculo) {
        return empresaRepository.listAllVentasByEmpresaPorCanalesAndVehiculo(nameAlmacen,nameVehiculo);
    }

    @Override
    public List<VentasEmpresaAlmacenTipoDateDto> listAllVentasByEmpresaPorAlamcenesAndVehiculo(List<String> nameAlmacen,List<String> nameVehiculo) {
        return empresaRepository.listAllVentasByEmpresaPorAlamcenesAndVehiculo(nameAlmacen,nameVehiculo);
    }

    // ANOTHER 12

    @Override
    public List<VentasEmpresasAlmacenColores> listAllVentasByEmpresaColorPorAlmacenAndMarca(List<String> nameAlmacen,List<String> nameMarca) {
        return empresaRepository.listAllVentasByEmpresaColorPorAlmacenAndMarca(nameAlmacen,nameMarca);
    }

    @Override
    public List<VentasEmpresasAlmacenesYearsDto> listAllVentasByEmpresaYearPorAlmacenAndMarca(List<String> nameAlmacen,List<String> nameMarca) {
        return empresaRepository.listAllVentasByEmpresaYearPorAlmacenAndMarca(nameAlmacen,nameMarca);
    }

    @Override
    public List<VentasCanalesEmpresaDto> listAllVentasByEmpresaPorCanalesdMarca(List<String> nameAlmacen,List<String> nameMarca) {
        return empresaRepository.listAllVentasByEmpresaPorCanalesdMarca(nameAlmacen,nameMarca);
    }

    @Override
    public List<VentasEmpresaAlmacenTipoDateDto> listAllVentasByEmpresaPorAlamcenesAndMarcas(List<String> nameAlmacen,List<String> nameMarca) {
        return empresaRepository.listAllVentasByEmpresaPorAlamcenesAndMarcas(nameAlmacen,nameMarca);
    }


}
