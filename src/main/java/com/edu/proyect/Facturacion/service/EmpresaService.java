package com.edu.proyect.Facturacion.service;

import com.edu.proyect.Facturacion.Payloads.*;
import com.edu.proyect.Facturacion.models.Empresa;
import com.edu.proyect.Facturacion.models.TipoVenta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;

public interface EmpresaService extends GenericService<Empresa, Integer>{

    List<Empresa> searchByName(String texto);

    List<VentasEmpresasDto> listAllEmpresasAndVentas();

    List<VentasEmpresasDto> listAllEmpresasAndVentasByDates(LocalDate dateOne, LocalDate dateTwo);

    List<VentasCanalesEmpresaDto> listAllCanalesVentasByEmpresa(String nameEmpresa);

    List<VentasAlmacenEmpresaDto> listAllVentasAlmacenByEmpresa(String nameEmpresa);

    List<VentasAlmacenEmpresaDto> listAllVentasAlmacenByEmpresaByTipoCarro( String nameEmpresa,String tipoCarro);

    List<VentasEmpresaAlmacenTipoDateDto> listAllVentasByEmpresaPorTipoAndDate(String nameAlmacen, String tipoCarro);

    public Page<Empresa> findByAll(Pageable pageable);

    // CONSULTAS CATERIAS, TIPOS, VEHICULOS, MARCAS
    List<CategoriaDto> getAllCategoriasPorEmpresas( String nameEmpresa);

    List<TipoVentaDto> getAllTiposVehiculosPorEmpresas(String nameEmpresa2);

    List<MarcasDto> getAllMarcasPorEmpresas( String nameEmpresa3);

    List<VehiculosDto> getAllVehiculosPorEmpresas(String nameEmpresa);

    Page<AlmacenesDto> getAllAlmacenesPorEmpresas(String nameEmpresa, Pageable pageable);

    // LAS 15
    List<VentasEmpresaAlmacenTipoDateDto> listAllVentasByEmpresaPorTipoAndMarcas(
            List<String> nameAlmacen,
            List<String> nameCategoria,
            List<String> nameMarca
    );

    List<VentasEmpresaAlmacenTipoDateDto> listAllVentasByEmpresaPorTipoAndMarcasAndCanal(
            List<String> nameAlmacen,
            List<String> nameCategoria,
            List<String> nameMarca,
            List<String> nameCanal
    );

    List<VentasEmpresaAlmacenTipoDateDto> listAllVentasByEmpresaPorTipoAndMarcasAndCanalAndVehiculo(
            List<String> nameAlmacen,
            List<String> nameCategoria,
            List<String> nameMarca,
            List<String> nameCanal,
            List<String> nameVehiculo
    );

    // CANAL

    List<VentasCanalesEmpresaDto> listAllVentasByEmpresaPorAlmacenAndCategoria(
           List<String> nameAlmacen,
            List<String> nameCategoria
    );

    List<VentasCanalesEmpresaDto> listAllVentasByEmpresaPorAlmacenAndCategoriaAndMarca(
            List<String> nameAlmacen,
            List<String> nameCategoria,
            List<String> nameMarca
    );

    List<VentasCanalesEmpresaDto> listAllVentasByEmpresaPorAlmacenAndCategoriaAndMarcaAndCanal(
            List<String> nameAlmacen,
            List<String> nameCategoria,
            List<String> nameMarca,
            List<String> nameCanal
    );

    List<VentasCanalesEmpresaDto> listAllVentasByEmpresaPorAlmacenAndCategoriaAndMarcaAndCanalAndVehiculo(
            List<String> nameAlmacen,
            List<String> nameCategoria,
            List<String> nameMarca,
            List<String> nameCanal,
            List<String> nameVehiculo
    );

    // COLOR

    List<VentasEmpresasAlmacenColores> listAllVentasByEmpresaColorPorAlmacenAndCategoria(
            List<String> nameAlmacen,
            List<String> nameCategoria
    );

    List<VentasEmpresasAlmacenColores> listAllVentasByEmpresaColorPorAlmacenAndCategoriaAndMarca(
            List<String> nameAlmacen,
            List<String> nameCategoria,
            List<String> nameMarca
    );

    List<VentasEmpresasAlmacenColores> listAllVentasByEmpresaColorPorAlmacenAndCategoriaAndMarcaAndCanal(
            List<String> nameAlmacen,
            List<String> nameCategoria,
            List<String> nameMarca,
            List<String> nameCanal
    );

    List<VentasEmpresasAlmacenColores> listAllVentasByEmpresaColorPorAlmacenAndCategoriaAndMarcaAndCanalAndVehiculo(
            List<String> nameAlmacen,
            List<String> nameCategoria,
            List<String> nameMarca,
            List<String> nameCanal,
            List<String> nameVehiculo
    );

    // YEARS

    List<VentasEmpresasAlmacenesYearsDto> listAllVentasByEmpresaYearPorAlmacenAndCategoria(
            List<String> nameAlmacen,
            List<String> nameCategoria
    );

    List<VentasEmpresasAlmacenesYearsDto> listAllVentasByEmpresaYearPorAlmacenAndCategoriaAndMarca(
            List<String> nameAlmacen,
            List<String> nameCategoria,
            List<String> nameMarca
    );

    List<VentasEmpresasAlmacenesYearsDto> listAllVentasByEmpresaYearPorAlmacenAndCategoriaAndMarcaAndCanal(
            List<String> nameAlmacen,
            List<String> nameCategoria,
            List<String> nameMarca,
            List<String> nameCanal
    );

    List<VentasEmpresasAlmacenesYearsDto> listAllVentasByEmpresaYearPorAlmacenAndCategoriaAndMarcaAndCanalAndVehiculo(
            List<String> nameAlmacen,
            List<String> nameCategoria,
            List<String> nameMarca,
            List<String> nameCanal,
            List<String> nameVehiculo
    );

    // ANOTHER
    List<VentasEmpresasAlmacenColores> listAllVentasByEmpresaColorPorAlmacenAndCategoriaAndCanalAndVehiculo(
            List<String> nameAlmacen,
            List<String> nameCategoria,
            List<String> nameCanal,
            List<String> nameVehiculo
    );

    List<VentasEmpresasAlmacenesYearsDto> listAllVentasByEmpresaYearPorAlmacenAndCategoriaAndCanalAndVehiculo(
            List<String> nameAlmacen,
            List<String> nameCategoria,
            List<String> nameCanal,
            List<String> nameVehiculo
    );

    List<VentasCanalesEmpresaDto> listAllVentasByEmpresaPorAlmacenAndCategoriaAndCanalAndVehiculo(
            List<String> nameAlmacen,
            List<String> nameCategoria,
            List<String> nameCanal,
            List<String> nameVehiculo
    );

    List<VentasEmpresaAlmacenTipoDateDto> listAllVentasByEmpresaPorTipoAndAndCanalAndVehiculo(
            List<String> nameAlmacen,
            List<String> nameCategoria,
            List<String> nameCanal,
            List<String> nameVehiculo
    );



    // ANOTHER 2

    List<VentasEmpresasAlmacenColores> listAllVentasByEmpresaColorPorAlmacenMarcaAndCanalAndVehiculo(
            List<String> nameAlmacen,
            List<String> nameCanal,
            List<String> nameVehiculo,
            List<String> nameMarca
    );

    List<VentasEmpresasAlmacenesYearsDto> listAllVentasByEmpresaYearPorAlmacenMarcaAndCanalAndVehiculo(
            List<String> nameAlmacen,
            List<String> nameCanal,
            List<String> nameVehiculo,
            List<String> nameMarca
    );

    List<VentasCanalesEmpresaDto> listAllVentasByEmpresaPorAlmacenMarcaAndCanalAndVehiculo(
            List<String> nameAlmacen,
            List<String> nameCanal,
            List<String> nameVehiculo,
            List<String> nameMarca
    );

    List<VentasEmpresaAlmacenTipoDateDto> listAllVentasByEmpresaPorMarcasAndCanalAndVehiculo(
            List<String> nameAlmacen,
            List<String> nameCanal,
            List<String> nameVehiculo,
            List<String> nameMarca
    );


    // ANOTHER 3
    List<VentasEmpresasAlmacenColores> listAllVentasByEmpresaColorPorAlmacenAndCategoriaAndMarcaAndVehiculo(
            List<String> nameAlmacen,
            List<String> nameCategoria,
            List<String> nameMarca,
            List<String> nameVehiculo
    );

    List<VentasEmpresasAlmacenesYearsDto> listAllVentasByEmpresaYearPorAlmacenAndCategoriaAndMarcaAndVehiculo(
            List<String> nameAlmacen,
            List<String> nameCategoria,
            List<String> nameMarca,
            List<String> nameVehiculo
    );

    List<VentasCanalesEmpresaDto> listAllVentasByEmpresaPorAlmacenAndCategoriaAndMarcaAndVehiculo(
            List<String> nameAlmacen,
            List<String> nameCategoria,
            List<String> nameMarca,
            List<String> nameVehiculo
    );

    List<VentasEmpresaAlmacenTipoDateDto> listAllVentasByEmpresaPorTipoAndMarcasAndVehiculo(
            List<String> nameAlmacen,
            List<String> nameCategoria,
            List<String> nameMarca,
            List<String> nameVehiculo
    );

    // ANOTHER 4

    List<VentasEmpresasAlmacenColores> listAllVentasByEmpresaColorPorAlmacenAndCategoriaAndCanal(
            List<String> nameAlmacen,
            List<String> nameCategoria,
            List<String> nameCanal
    );

    List<VentasEmpresasAlmacenesYearsDto> listAllVentasByEmpresaYearPorAlmacenAndCategoriaAndCanal(
            List<String> nameAlmacen,
            List<String> nameCategoria,
            List<String> nameCanal
    );

    List<VentasCanalesEmpresaDto> listAllVentasByEmpresaPorAlmacenAndCategoriaAndCanal(
            List<String> nameAlmacen,
            List<String> nameCategoria,
            List<String> nameCanal
    );

    List<VentasEmpresaAlmacenTipoDateDto> listAllVentasByEmpresaPorTipoAndCanal(
            List<String> nameAlmacen,
            List<String> nameCategoria,
            List<String> nameCanal
    );

    // ANONTHER 5
    List<VentasEmpresasAlmacenColores> listAllVentasByEmpresaColorPorAlmacenMarcaAndCanal(
            List<String> nameAlmacen,
            List<String> nameMarca,
            List<String> nameCanal
    );

    List<VentasEmpresasAlmacenesYearsDto> listAllVentasByEmpresaYearPorAlmacenMarcaAndCanal(
            List<String> nameAlmacen,
            List<String> nameMarca,
            List<String> nameCanal
    );

    List<VentasCanalesEmpresaDto> listAllVentasByEmpresaPorAlmacenMarcaAndCanal(
            List<String> nameAlmacen,
            List<String> nameMarca,
            List<String> nameCanal
    );

    List<VentasEmpresaAlmacenTipoDateDto> listAllVentasByEmpresaPorMarcasAndCanal(
            List<String> nameAlmacen,
            List<String> nameMarca,
            List<String> nameCanal
    );
    // ANOTHER 6
    List<VentasEmpresasAlmacenColores> listAllVentasByEmpresaColorPorAlmacenCanalAndVehiculo(
            List<String> nameAlmacen,
            List<String> nameCanal,
            List<String> nameVehiculo
    );

    List<VentasEmpresasAlmacenesYearsDto> listAllVentasByEmpresaYearPorAlmacenAndCanalAndVehiculo(
            List<String> nameAlmacen,
            List<String> nameCanal,
            List<String> nameVehiculo
    );

    List<VentasCanalesEmpresaDto> listAllVentasByEmpresaPorAlmacenAndCanalAndVehiculo(
            List<String> nameAlmacen,
            List<String> nameCanal,
            List<String> nameVehiculo
    );

    List<VentasEmpresaAlmacenTipoDateDto> listAllVentasByEmpresaPorCanalAndVehiculo(
            List<String> nameAlmacen,
            List<String> nameCanal,
            List<String> nameVehiculo
    );
    // ANOTHER 7
    List<VentasEmpresasAlmacenColores> listAllVentasByEmpresaColorPorAlmacenAndCategoriaAndVehiculo(
            List<String> nameAlmacen,
            List<String> nameCategoria,
            List<String> nameVehiculo
    );

    List<VentasEmpresasAlmacenesYearsDto> listAllVentasByEmpresaYearPorAlmacenAndCategoriaAndVehiculo(
            List<String> nameAlmacen,
            List<String> nameCategoria,
            List<String> nameVehiculo
    );

    List<VentasCanalesEmpresaDto> listAllVentasByEmpresaPorAlmacenAndCategoriaAndVehiculo(
            List<String> nameAlmacen,
            List<String> nameCategoria,
            List<String> nameVehiculo
    );

    List<VentasEmpresaAlmacenTipoDateDto> listAllVentasByEmpresaPorAlamcenesAndCateAndAndVehiculo(
            List<String> nameAlmacen,
            List<String> nameCategoria,
            List<String> nameVehiculo
    );
    // ANOTHER 8
    List<VentasEmpresasAlmacenColores> listAllVentasByEmpresaColorPorAlmacenAndMarcaAndVehiculo(
            List<String> nameAlmacen,
            List<String> nameMarca,
            List<String> nameVehiculo
    );

    List<VentasEmpresasAlmacenesYearsDto> listAllVentasByEmpresaYearPorAlmacenAndMarcaAndVehiculo(
            List<String> nameAlmacen,
            List<String> nameMarca,
            List<String> nameVehiculo
    );

    List<VentasCanalesEmpresaDto> listAllVentasByEmpresaPorCanalesdMarcaAndVehiculo(
            List<String> nameAlmacen,
            List<String> nameMarca,
            List<String> nameVehiculo
    );

    List<VentasEmpresaAlmacenTipoDateDto> listAllVentasByEmpresaPorAlamcenesAndMarcasAndVehiculo(
            List<String> nameAlmacen,
            List<String> nameMarca,
            List<String> nameVehiculo
    );
    // ANOTHER 9
    List<VentasEmpresasAlmacenColores> listAllVentasByEmpresaColorPorAlmacenCanal(
            List<String> nameAlmacen,
            List<String> nameCanal

    );

    List<VentasEmpresasAlmacenesYearsDto> listAllVentasByEmpresaYearPorAlmacenAndCanal(
            List<String> nameAlmacen,
            List<String> nameCanal

    );

    List<VentasCanalesEmpresaDto> listAllVentasByEmpresaPorAlmacenAndCanal(
            List<String> nameAlmacen,
            List<String> nameCanal

    );

    List<VentasEmpresaAlmacenTipoDateDto> listAllVentasByEmpresaPorCanal(
            List<String> nameAlmacen,
            List<String> nameCanal
    );
    // ANOTHER 10
    List<VentasEmpresasAlmacenColores> listAllVentasByEmpresaColorPorAlmacenAndVehiculo(
            List<String> nameAlmacen,
            List<String> nameVehiculo
    );

    List<VentasEmpresasAlmacenesYearsDto> listAllVentasByEmpresaYearPorAlmacenAndVehiculo(
            List<String> nameAlmacen,
            List<String> nameVehiculo
    );

    List<VentasCanalesEmpresaDto> listAllVentasByEmpresaPorCanalesAndVehiculo(
            List<String> nameAlmacen,
            List<String> nameVehiculo
    );

    List<VentasEmpresaAlmacenTipoDateDto> listAllVentasByEmpresaPorAlamcenesAndVehiculo(
            List<String> nameAlmacen,
            List<String> nameVehiculo
    );
    // ANOTHER 11
    List<VentasEmpresasAlmacenColores> listAllVentasByEmpresaColorPorAlmacenAndMarca(
            List<String> nameAlmacen,
            List<String> nameMarca
    );

    List<VentasEmpresasAlmacenesYearsDto> listAllVentasByEmpresaYearPorAlmacenAndMarca(
            List<String> nameAlmacen,
            List<String> nameMarca
    );

    List<VentasCanalesEmpresaDto> listAllVentasByEmpresaPorCanalesdMarca(
            List<String> nameAlmacen,
            List<String> nameMarca
    );

    List<VentasEmpresaAlmacenTipoDateDto> listAllVentasByEmpresaPorAlamcenesAndMarcas(
            List<String> nameAlmacen,
            List<String> nameMarca
    );
}
