package com.edu.proyect.Facturacion.repository;

import com.edu.proyect.Facturacion.Payloads.*;
import com.edu.proyect.Facturacion.models.Categoria;
import com.edu.proyect.Facturacion.models.Empresa;
import com.edu.proyect.Facturacion.models.TipoVenta;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {

    // FOUND EMPRESA BY NAME
    @Query(value = "SELECT * FROM empreas WHERE nombre_empresa LIKE %?1%", nativeQuery = true)
    List<Empresa> searchByName(String texto);

    @Query(value = "SELECT e.nombre_empresa as nombreEmpresa, COUNT(DISTINCT v.id_venta) AS numVentas FROM ventas v JOIN detalles_ventas dv ON v.id_venta = dv.id_venta JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo JOIN almacenes a ON vh.id_almacen = a.id_almacen JOIN empreas e ON a.id_empresa = e.id_empresa GROUP BY e.nombre_empresa", nativeQuery = true)
    List<VentasEmpresasDto> listAllEmpresasAndVentas();

    @Query(value = "SELECT e.nombre_empresa as nombreEmpresa, COUNT(DISTINCT v.id_venta) AS numVentas FROM ventas v JOIN detalles_ventas dv ON v.id_venta = dv.id_venta JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo JOIN almacenes a ON vh.id_almacen = a.id_almacen JOIN empreas e ON a.id_empresa = e.id_empresa WHERE v.fecha_venta BETWEEN :dateOne AND :dateTwo GROUP BY e.nombre_empresa", nativeQuery = true)
    List<VentasEmpresasDto> listAllEmpresasAndVentasByDates(@PathVariable("dateOne") LocalDate dateOne , @PathVariable("dateTwo") LocalDate dateTwo);

    @Query( value = "SELECT tv.tipo_venta  as tipoVentas, COUNT(DISTINCT v.id_venta) AS numVentas FROM ventas v JOIN detalles_ventas dv ON v.id_venta = dv.id_venta JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo JOIN almacenes a ON vh.id_almacen = a.id_almacen JOIN empreas e ON a.id_empresa = e.id_empresa JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta WHERE e.nombre_empresa = :nameEmpresa GROUP BY tv.tipo_venta", nativeQuery = true)
    List<VentasCanalesEmpresaDto> listAllCanalesVentasByEmpresa(@PathVariable("nameEmpresa") String nameEmpresa);

    @Query(value = "SELECT a.nombre_almacen  as nomAlmacen, COUNT(DISTINCT v.id_venta) AS numVentas FROM ventas v JOIN detalles_ventas dv ON v.id_venta = dv.id_venta JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo join categorias c on vh.id_categoria = c.id_categoria JOIN almacenes a ON vh.id_almacen = a.id_almacen JOIN empreas e ON a.id_empresa = e.id_empresa JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta WHERE e.nombre_empresa = :nameEmpresa GROUP BY a.nombre_almacen", nativeQuery = true)
    List<VentasAlmacenEmpresaDto> listAllVentasAlmacenByEmpresa(@PathVariable("nameEmpresa") String nameEmpresa);

    @Query(value = "SELECT a.nombre_almacen  as nomAlmacen, COUNT(DISTINCT v.id_venta) AS numVentas FROM ventas v JOIN detalles_ventas dv ON v.id_venta = dv.id_venta JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo join categorias c on vh.id_categoria = c.id_categoria JOIN almacenes a ON vh.id_almacen = a.id_almacen JOIN empreas e ON a.id_empresa = e.id_empresa JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta WHERE e.nombre_empresa = :nameEmpresa AND c.nombre_categoria = :tipoCarro GROUP BY a.nombre_almacen", nativeQuery = true)
    List<VentasAlmacenEmpresaDto> listAllVentasAlmacenByEmpresaByTipoCarro(@PathVariable("nameEmpresa") String nameEmpresa, @PathVariable("tipoCarro") String tipoCarro);


//    @Query(value = "SELECT a.nombre_almacen as nomAlmacen, COUNT(DISTINCT v.id_venta) AS numVentas " +
//            "FROM ventas v " +
//            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta " +
//            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo " +
//            "JOIN categorias c ON vh.id_categoria = c.id_categoria " +
//            "JOIN almacenes a ON vh.id_almacen = a.id_almacen " +
//            "JOIN empreas e ON a.id_empresa = e.id_empresa " +
//            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta " +
//            "WHERE a.nombre_almacen = :nameAlmacen " +
//            "AND c.nombre_categoria = :tipoCarro " +
//            "AND (:dateOne IS NULL OR :dateTwo IS NULL OR v.fecha_venta BETWEEN :dateOne AND :dateTwo) " +
//            "GROUP BY a.nombre_almacen", nativeQuery = true)
//    List<VentasEmpresaAlmacenTipoDateDto> listAllVentasByEmpresaPorTipoAndDate(
//            @Param("nameAlmacen") String nameAlmacen,
//            @Param("tipoCarro") String tipoCarro,
//            @Param("dateOne") LocalDate dateOne ,
//            @Param("dateTwo") LocalDate dateTwo
//    );


    // CONSULTAS CATERIAS, TIPOS, VEHICULOS, MARCAS

    @Query(value = "SELECT DISTINCT c.nombre_categoria FROM categorias c JOIN vehiculos v ON c.id_categoria = v.id_categoria JOIN almacenes a ON v.id_almacen = a.id_almacen JOIN empreas e ON a.id_empresa = e.id_empresa JOIN detalles_ventas dv ON v.id_vehiculo = dv.id_vehiculo WHERE a.nombre_almacen = :nameEmpresa", nativeQuery = true)
    List<CategoriaDto> getAllCategoriasPorEmpresas(@PathVariable("nameEmpresa") String nameEmpresa);

    @Query(value = "SELECT DISTINCT tv.tipo_venta FROM tipos_ventas tv JOIN detalles_ventas dv ON tv.id_tipo_venta = dv.id_tipo_venta JOIN ventas v2 ON dv.id_venta = v2.id_venta JOIN clientes c ON v2.id_cliente = c.id_cliente JOIN vehiculos v ON dv.id_vehiculo = v.id_vehiculo JOIN almacenes a ON v.id_almacen = a.id_almacen JOIN empreas e ON a.id_empresa = e.id_empresa WHERE a.nombre_almacen = :nameEmpresa2", nativeQuery = true)
    List<TipoVentaDto> getAllTiposVehiculosPorEmpresas(@PathVariable("nameEmpresa2") String nameEmpresa2);

    @Query(value = "SELECT DISTINCT m.nombre_marca \n" +
            "FROM marcas m \n" +
            "JOIN vehiculos v ON m.id_marca = v.id_marca \n" +
            "JOIN detalles_ventas dv ON v.id_vehiculo = dv.id_vehiculo \n" +
            "JOIN ventas v2 ON dv.id_venta = v2.id_venta \n" +
            "JOIN clientes c ON v2.id_cliente = c.id_cliente \n" +
            "JOIN almacenes a ON v.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "WHERE a.nombre_almacen = :nameEmpresa3", nativeQuery = true)
    List<MarcasDto> getAllMarcasPorEmpresas(@PathVariable("nameEmpresa3") String nameEmpresa3);

    @Query(value = "SELECT DISTINCT v.nombre_vehiculo \n" +
            "FROM vehiculos v \n" +
            "JOIN detalles_ventas dv ON v.id_vehiculo = dv.id_vehiculo \n" +
            "JOIN ventas v2 ON dv.id_venta = v2.id_venta \n" +
            "JOIN clientes c ON v2.id_cliente = c.id_cliente \n" +
            "JOIN almacenes a ON v.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "WHERE a.nombre_almacen = :nameEmpresa", nativeQuery = true)
    List<VehiculosDto> getAllVehiculosPorEmpresas(@PathVariable("nameEmpresa") String nameEmpresa);

    @Query(value = "SELECT DISTINCT a.nombre_almacen  \n" +
            "FROM almacenes a \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN vehiculos v ON a.id_almacen  = v.id_almacen  \n" +
            "JOIN detalles_ventas dv ON dv.id_vehiculo  = v.id_vehiculo \n" +
            "JOIN ventas v2 ON dv.id_venta  = v2.id_venta \n" +
            "JOIN clientes c ON v2.id_cliente = c.id_cliente \n" +
            "WHERE e.nombre_empresa = :nameEmpresa",nativeQuery = true)
    Page<AlmacenesDto> getAllAlmacenesPorEmpresas(@PathVariable("nameEmpresa") String nameEmpresa,Pageable pageable);


    // GENERAL EMPRESAS
    @Query(value = "SELECT EXTRACT(YEAR FROM v.fecha_venta) as yearVenta, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "JOIN marcas m ON vh.id_marca  = m.id_marca \n" +
            "JOIN categorias c ON vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE e.nombre_empresa  IN :nameEmpresa\n" +
            "GROUP BY EXTRACT(YEAR FROM v.fecha_venta)", nativeQuery = true)
    List<VentasEmpresasAlmacenesYearsDto> listAllVentasByEmpresaYear(
            @Param("nameEmpresa") List<String> nameEmpresa
    );

    @Query(value = "SELECT vh.color  as nomColor, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "join marcas m on vh.id_marca  = m.id_marca \n" +
            "join categorias c on vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE e.nombre_empresa  in :nameEmpresa\n" +
            "GROUP BY vh.color", nativeQuery = true)
    List<VentasEmpresasAlmacenColores> listAllVentasColorByEmpresa(
            @Param("nameEmpresa") List<String> nameEmpresa
    );

    @Query(value = "SELECT tv.tipo_venta  as tipoVentas, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "join marcas m on vh.id_marca  = m.id_marca \n" +
            "join categorias c on vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE e.nombre_empresa  in :nameEmpresa\n" +
            "GROUP BY tv.tipo_venta", nativeQuery = true)
    List<VentasCanalesEmpresaDto> listAllVentasCanalesByEmpresa(
            @Param("nameEmpresa") List<String> nameEmpresa
    );

    @Query(value = "SELECT a.nombre_almacen  as nomAlmacen, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "join marcas m on vh.id_marca  = m.id_marca \n" +
            "join categorias c on vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE e.nombre_empresa  in :nameEmpresa\n" +
            "GROUP BY a.nombre_almacen", nativeQuery = true)
    List<VentasEmpresaAlmacenTipoDateDto> listAllVentasByEmpresa(
            @Param("nameEmpresa") List<String> nameEmpresa
    );


    // POR ALAMACENES

    @Query(value = "SELECT EXTRACT(YEAR FROM v.fecha_venta) as yearVenta, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "JOIN marcas m ON vh.id_marca  = m.id_marca \n" +
            "JOIN categorias c ON vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE a.nombre_almacen IN :nameAlmacen\n" +
            "GROUP BY EXTRACT(YEAR FROM v.fecha_venta)", nativeQuery = true)
    List<VentasEmpresasAlmacenesYearsDto> listAllVentasByEmpresaYearPorAlmacen(
            @Param("nameAlmacen") List<String> nameAlmacen
    );

    @Query(value = "SELECT vh.color  as nomColor, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "join marcas m on vh.id_marca  = m.id_marca \n" +
            "join categorias c on vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE a.nombre_almacen in :nameAlmacen\n" +
            "GROUP BY vh.color", nativeQuery = true)
    List<VentasEmpresasAlmacenColores> listAllVentasByEmpresaColorPorAlmacen(
            @Param("nameAlmacen") List<String> nameAlmacen
    );

    @Query(value = "SELECT tv.tipo_venta  as tipoVentas, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "join marcas m on vh.id_marca  = m.id_marca \n" +
            "join categorias c on vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE a.nombre_almacen in :nameAlmacen\n" +
            "GROUP BY tv.tipo_venta", nativeQuery = true)
    List<VentasCanalesEmpresaDto> listAllVentasCanalesByEmpresaPorAlmacen(
            @Param("nameAlmacen") List<String> nameAlmacen
    );

    @Query(value = "SELECT a.nombre_almacen  as nomAlmacen, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "join marcas m on vh.id_marca  = m.id_marca \n" +
            "join categorias c on vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE a.nombre_almacen in :nameAlmacen\n" +
            "GROUP BY a.nombre_almacen", nativeQuery = true)
            List<VentasEmpresaAlmacenTipoDateDto> listAllVentasByEmpresaPorAlmacen(
            @Param("nameAlmacen") List<String> nameAlmacen
    );

    // LAS 15 CONSULTAS
    @Query(value = "SELECT a.nombre_almacen as nomAlmacen, COUNT(DISTINCT v.id_venta) AS numVentas " +
            "FROM ventas v " +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta " +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo " +
            "JOIN categorias c ON vh.id_categoria = c.id_categoria " +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen " +
            "JOIN empreas e ON a.id_empresa = e.id_empresa " +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta " +
            "WHERE a.nombre_almacen = :nameAlmacen " +
            "AND c.nombre_categoria = :tipoCarro " +
            "GROUP BY a.nombre_almacen", nativeQuery = true)
    List<VentasEmpresaAlmacenTipoDateDto> listAllVentasByEmpresaPorTipoAndDate(
            @Param("nameAlmacen") String nameAlmacen,
            @Param("tipoCarro") String tipoCarro
    );

    @Query(value = "SELECT a.nombre_almacen  as nomAlmacen, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "join marcas m on vh.id_marca  = m.id_marca \n" +
            "join categorias c on vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE a.nombre_almacen in :nameAlmacen \n" +
            "AND c.nombre_categoria in :nameCategoria\n" +
            "and m.nombre_marca in :nameMarca\n" +
            "GROUP BY a.nombre_almacen", nativeQuery = true)
    List<VentasEmpresaAlmacenTipoDateDto> listAllVentasByEmpresaPorTipoAndMarcas(
            @Param("nameAlmacen") List<String> nameAlmacen,
            @Param("nameCategoria") List<String> nameCategoria,
            @Param("nameMarca") List<String> nameMarca
    );

    @Query(value = "SELECT a.nombre_almacen  as nomAlmacen, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "join marcas m on vh.id_marca  = m.id_marca \n" +
            "join categorias c on vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE a.nombre_almacen in :nameAlmacen\n" +
            "AND c.nombre_categoria in :nameCategoria\n" +
            "and m.nombre_marca in :nameMarca\n" +
            "and tv.tipo_venta in :nameCanal\n" +
            "GROUP BY a.nombre_almacen", nativeQuery = true)
    List<VentasEmpresaAlmacenTipoDateDto> listAllVentasByEmpresaPorTipoAndMarcasAndCanal(
            @Param("nameAlmacen") List<String> nameAlmacen,
            @Param("nameCategoria") List<String> nameCategoria,
            @Param("nameMarca") List<String> nameMarca,
            @Param("nameCanal") List<String> nameCanal
    );


    @Query(value = "SELECT a.nombre_almacen  as nomAlmacen, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "join marcas m on vh.id_marca  = m.id_marca \n" +
            "join categorias c on vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE a.nombre_almacen in :nameAlmacen\n" +
            "AND c.nombre_categoria in :nameCategoria\n" +
            "and m.nombre_marca in :nameMarca\n" +
            "and tv.tipo_venta in :nameCanal\n" +
            "and vh.nombre_vehiculo in :nameVehiculo\n" +
            "GROUP BY a.nombre_almacen", nativeQuery = true)
    List<VentasEmpresaAlmacenTipoDateDto> listAllVentasByEmpresaPorTipoAndMarcasAndCanalAndVehiculo(
            @Param("nameAlmacen") List<String> nameAlmacen,
            @Param("nameCategoria") List<String> nameCategoria,
            @Param("nameMarca") List<String> nameMarca,
            @Param("nameCanal") List<String> nameCanal,
            @Param("nameVehiculo") List<String> nameVehiculo
            );

    //CANALES
    @Query(value = "SELECT tv.tipo_venta  as tipoVentas, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "join marcas m on vh.id_marca  = m.id_marca \n" +
            "join categorias c on vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE a.nombre_almacen in :nameAlmacen\n" +
            "and c.nombre_categoria in :nameCategoria\n" +
            "GROUP BY tv.tipo_venta", nativeQuery = true)
    List<VentasCanalesEmpresaDto> listAllVentasByEmpresaPorAlmacenAndCategoria(
            @Param("nameAlmacen") List<String> nameAlmacen,
            @Param("nameCategoria") List<String> nameCategoria
    );

    @Query(value = "SELECT tv.tipo_venta  as tipoVentas, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "join marcas m on vh.id_marca  = m.id_marca \n" +
            "join categorias c on vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE a.nombre_almacen in :nameAlmacen\n" +
            "and c.nombre_categoria in :nameCategoria\n" +
            "and m.nombre_marca in :nameMarca\n" +
            "GROUP BY tv.tipo_venta", nativeQuery = true)
    List<VentasCanalesEmpresaDto> listAllVentasByEmpresaPorAlmacenAndCategoriaAndMarca(
            @Param("nameAlmacen") List<String> nameAlmacen,
            @Param("nameCategoria") List<String> nameCategoria,
            @Param("nameMarca") List<String> nameMarca
    );

    @Query(value = "SELECT tv.tipo_venta  as tipoVentas, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "join marcas m on vh.id_marca  = m.id_marca \n" +
            "join categorias c on vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE a.nombre_almacen in :nameAlmacen\n" +
            "and c.nombre_categoria in :nameCategoria\n" +
            "and m.nombre_marca in :nameMarca\n" +
            "and tv.tipo_venta in :nameCanal\n" +
            "GROUP BY tv.tipo_venta", nativeQuery = true)
    List<VentasCanalesEmpresaDto> listAllVentasByEmpresaPorAlmacenAndCategoriaAndMarcaAndCanal(
            @Param("nameAlmacen") List<String> nameAlmacen,
            @Param("nameCategoria") List<String> nameCategoria,
            @Param("nameMarca") List<String> nameMarca,
            @Param("nameCanal") List<String> nameCanal

    );

    @Query(value = "SELECT tv.tipo_venta  as tipoVentas, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "join marcas m on vh.id_marca  = m.id_marca \n" +
            "join categorias c on vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE a.nombre_almacen in :nameAlmacen\n" +
            "AND c.nombre_categoria in :nameCategoria\n" +
            "and m.nombre_marca in :nameMarca\n" +
            "and tv.tipo_venta in :nameCanal\n" +
            "and vh.nombre_vehiculo in :nameVehiculo\n" +
            "GROUP BY tv.tipo_venta", nativeQuery = true)
    List<VentasCanalesEmpresaDto> listAllVentasByEmpresaPorAlmacenAndCategoriaAndMarcaAndCanalAndVehiculo(
            @Param("nameAlmacen") List<String> nameAlmacen,
            @Param("nameCategoria") List<String> nameCategoria,
            @Param("nameMarca") List<String> nameMarca,
            @Param("nameCanal") List<String> nameCanal,
            @Param("nameVehiculo") List<String> nameVehiculo
    );

    //COLORES
    @Query(value = "SELECT vh.color  as nomColor, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "join marcas m on vh.id_marca  = m.id_marca \n" +
            "join categorias c on vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE a.nombre_almacen in :nameAlmacen\n" +
            "AND c.nombre_categoria in :nameCategoria\n" +
            "GROUP BY vh.color", nativeQuery = true)
    List<VentasEmpresasAlmacenColores> listAllVentasByEmpresaColorPorAlmacenAndCategoria(
            @Param("nameAlmacen") List<String> nameAlmacen,
            @Param("nameCategoria") List<String> nameCategoria
    );

    @Query(value = "SELECT vh.color  as nomColor, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "join marcas m on vh.id_marca  = m.id_marca \n" +
            "join categorias c on vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE a.nombre_almacen in :nameAlmacen\n" +
            "AND c.nombre_categoria in :nameCategoria\n" +
            "and m.nombre_marca in :nameMarca\n" +
            "GROUP BY vh.color", nativeQuery = true)
    List<VentasEmpresasAlmacenColores> listAllVentasByEmpresaColorPorAlmacenAndCategoriaAndMarca(
            @Param("nameAlmacen") List<String> nameAlmacen,
            @Param("nameCategoria") List<String> nameCategoria,
            @Param("nameMarca") List<String> nameMarca
    );

    @Query(value = "SELECT vh.color  as nomColor, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "join marcas m on vh.id_marca  = m.id_marca \n" +
            "join categorias c on vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE a.nombre_almacen in :nameAlmacen\n" +
            "AND c.nombre_categoria in :nameCategoria\n" +
            "and m.nombre_marca in :nameMarca\n" +
            "and tv.tipo_venta in :nameCanal\n" +
            "GROUP BY vh.color", nativeQuery = true)
    List<VentasEmpresasAlmacenColores> listAllVentasByEmpresaColorPorAlmacenAndCategoriaAndMarcaAndCanal(
            @Param("nameAlmacen") List<String> nameAlmacen,
            @Param("nameCategoria") List<String> nameCategoria,
            @Param("nameMarca") List<String> nameMarca,
            @Param("nameCanal") List<String> nameCanal

    );

    @Query(value = "SELECT vh.color  as nomColor, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "join marcas m on vh.id_marca  = m.id_marca \n" +
            "join categorias c on vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE a.nombre_almacen in :nameAlmacen\n" +
            "AND c.nombre_categoria in :nameCategoria\n" +
            "and m.nombre_marca in :nameMarca\n" +
            "and tv.tipo_venta in :nameCanal\n" +
            "and vh.nombre_vehiculo in :nameVehiculo\n" +
            "GROUP BY vh.color", nativeQuery = true)
    List<VentasEmpresasAlmacenColores> listAllVentasByEmpresaColorPorAlmacenAndCategoriaAndMarcaAndCanalAndVehiculo(
            @Param("nameAlmacen") List<String> nameAlmacen,
            @Param("nameCategoria") List<String> nameCategoria,
            @Param("nameMarca") List<String> nameMarca,
            @Param("nameCanal") List<String> nameCanal,
            @Param("nameVehiculo") List<String> nameVehiculo
    );

    // YEARS
    @Query(value = "SELECT EXTRACT(YEAR FROM v.fecha_venta) as yearVenta, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "JOIN marcas m ON vh.id_marca  = m.id_marca \n" +
            "JOIN categorias c ON vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE a.nombre_almacen IN :nameAlmacen\n" +
            "AND c.nombre_categoria IN :nameCategoria\n" +
            "GROUP BY EXTRACT(YEAR FROM v.fecha_venta)", nativeQuery = true)
    List<VentasEmpresasAlmacenesYearsDto> listAllVentasByEmpresaYearPorAlmacenAndCategoria(
            @Param("nameAlmacen") List<String> nameAlmacen,
            @Param("nameCategoria") List<String> nameCategoria
    );

    @Query(value = "SELECT EXTRACT(YEAR FROM v.fecha_venta) as yearVenta, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "JOIN marcas m ON vh.id_marca  = m.id_marca \n" +
            "JOIN categorias c ON vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE a.nombre_almacen IN :nameAlmacen\n" +
            "AND c.nombre_categoria IN :nameCategoria\n" +
            "and m.nombre_marca in :nameMarca\n" +
            "GROUP BY EXTRACT(YEAR FROM v.fecha_venta)", nativeQuery = true)
    List<VentasEmpresasAlmacenesYearsDto> listAllVentasByEmpresaYearPorAlmacenAndCategoriaAndMarca(
            @Param("nameAlmacen") List<String> nameAlmacen,
            @Param("nameCategoria") List<String> nameCategoria,
            @Param("nameMarca") List<String> nameMarca
    );

    @Query(value = "SELECT EXTRACT(YEAR FROM v.fecha_venta) as yearVenta, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "JOIN marcas m ON vh.id_marca  = m.id_marca \n" +
            "JOIN categorias c ON vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE a.nombre_almacen IN :nameAlmacen\n" +
            "AND c.nombre_categoria IN :nameCategoria\n" +
            "and m.nombre_marca in :nameMarca\n" +
            "and tv.tipo_venta in :nameCanal\n" +
            "GROUP BY EXTRACT(YEAR FROM v.fecha_venta)", nativeQuery = true)
    List<VentasEmpresasAlmacenesYearsDto> listAllVentasByEmpresaYearPorAlmacenAndCategoriaAndMarcaAndCanal(
            @Param("nameAlmacen") List<String> nameAlmacen,
            @Param("nameCategoria") List<String> nameCategoria,
            @Param("nameMarca") List<String> nameMarca,
            @Param("nameCanal") List<String> nameCanal

    );

    @Query(value = "SELECT EXTRACT(YEAR FROM v.fecha_venta) as yearVenta, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "JOIN marcas m ON vh.id_marca  = m.id_marca \n" +
            "JOIN categorias c ON vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE a.nombre_almacen IN :nameAlmacen\n" +
            "AND c.nombre_categoria IN :nameCategoria\n" +
            "and m.nombre_marca in :nameMarca\n" +
            "and tv.tipo_venta in :nameCanal\n" +
            "and vh.nombre_vehiculo in :nameVehiculo\n" +
            "GROUP BY EXTRACT(YEAR FROM v.fecha_venta)", nativeQuery = true)
    List<VentasEmpresasAlmacenesYearsDto> listAllVentasByEmpresaYearPorAlmacenAndCategoriaAndMarcaAndCanalAndVehiculo(
            @Param("nameAlmacen") List<String> nameAlmacen,
            @Param("nameCategoria") List<String> nameCategoria,
            @Param("nameMarca") List<String> nameMarca,
            @Param("nameCanal") List<String> nameCanal,
            @Param("nameVehiculo") List<String> nameVehiculo
    );

    // ANOTHER

    @Query(value = "SELECT EXTRACT(YEAR FROM v.fecha_venta) as yearVenta, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "JOIN marcas m ON vh.id_marca  = m.id_marca \n" +
            "JOIN categorias c ON vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE a.nombre_almacen IN :nameAlmacen\n" +
            "AND c.nombre_categoria IN :nameCategoria\n" +
            "and tv.tipo_venta in :nameCanal\n" +
            "and vh.nombre_vehiculo in :nameVehiculo\n" +
            "GROUP BY EXTRACT(YEAR FROM v.fecha_venta)", nativeQuery = true)
    List<VentasEmpresasAlmacenesYearsDto> listAllVentasByEmpresaYearPorAlmacenAndCategoriaAndCanalAndVehiculo(
            @Param("nameAlmacen") List<String> nameAlmacen,
            @Param("nameCategoria") List<String> nameCategoria,
            @Param("nameCanal") List<String> nameCanal,
            @Param("nameVehiculo") List<String> nameVehiculo
    );

    @Query(value = "SELECT vh.color  as nomColor, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "join marcas m on vh.id_marca  = m.id_marca \n" +
            "join categorias c on vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE a.nombre_almacen in :nameAlmacen\n" +
            "AND c.nombre_categoria in :nameCategoria\n" +
            "and tv.tipo_venta in :nameCanal\n" +
            "and vh.nombre_vehiculo in :nameVehiculo\n" +
            "GROUP BY vh.color", nativeQuery = true)
    List<VentasEmpresasAlmacenColores> listAllVentasByEmpresaColorPorAlmacenAndCategoriaAndCanalAndVehiculo(
            @Param("nameAlmacen") List<String> nameAlmacen,
            @Param("nameCategoria") List<String> nameCategoria,
            @Param("nameCanal") List<String> nameCanal,
            @Param("nameVehiculo") List<String> nameVehiculo
    );

    @Query(value = "SELECT tv.tipo_venta  as tipoVentas, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "join marcas m on vh.id_marca  = m.id_marca \n" +
            "join categorias c on vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE a.nombre_almacen in :nameAlmacen\n" +
            "AND c.nombre_categoria in :nameCategoria\n" +
            "and tv.tipo_venta in :nameCanal\n" +
            "and vh.nombre_vehiculo in :nameVehiculo\n" +
            "GROUP BY tv.tipo_venta", nativeQuery = true)
    List<VentasCanalesEmpresaDto> listAllVentasByEmpresaPorAlmacenAndCategoriaAndCanalAndVehiculo(
            @Param("nameAlmacen") List<String> nameAlmacen,
            @Param("nameCategoria") List<String> nameCategoria,
            @Param("nameCanal") List<String> nameCanal,
            @Param("nameVehiculo") List<String> nameVehiculo
    );

    @Query(value = "SELECT a.nombre_almacen  as nomAlmacen, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "join marcas m on vh.id_marca  = m.id_marca \n" +
            "join categorias c on vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE a.nombre_almacen in :nameAlmacen\n" +
            "AND c.nombre_categoria in :nameCategoria\n" +
            "and tv.tipo_venta in :nameCanal\n" +
            "and vh.nombre_vehiculo in :nameVehiculo\n" +
            "GROUP BY a.nombre_almacen", nativeQuery = true)
    List<VentasEmpresaAlmacenTipoDateDto> listAllVentasByEmpresaPorTipoAndAndCanalAndVehiculo(
            @Param("nameAlmacen") List<String> nameAlmacen,
            @Param("nameCategoria") List<String> nameCategoria,
            @Param("nameCanal") List<String> nameCanal,
            @Param("nameVehiculo") List<String> nameVehiculo
    );


    // ANOTHER 2
    @Query(value = "SELECT EXTRACT(YEAR FROM v.fecha_venta) as yearVenta, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "JOIN marcas m ON vh.id_marca  = m.id_marca \n" +
            "JOIN categorias c ON vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE a.nombre_almacen IN :nameAlmacen\n" +
            "and m.nombre_marca in :nameMarca\n" +
            "and tv.tipo_venta in :nameCanal\n" +
            "and vh.nombre_vehiculo in :nameVehiculo\n" +
            "GROUP BY EXTRACT(YEAR FROM v.fecha_venta)", nativeQuery = true)
    List<VentasEmpresasAlmacenesYearsDto> listAllVentasByEmpresaYearPorAlmacenMarcaAndCanalAndVehiculo(
            @Param("nameAlmacen") List<String> nameAlmacen,
            @Param("nameMarca") List<String> nameMarca,
            @Param("nameCanal") List<String> nameCanal,
            @Param("nameVehiculo") List<String> nameVehiculo
    );

    @Query(value = "SELECT a.nombre_almacen  as nomAlmacen, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "join marcas m on vh.id_marca  = m.id_marca \n" +
            "join categorias c on vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE a.nombre_almacen in :nameAlmacen\n" +
            "and m.nombre_marca in :nameMarca\n" +
            "and tv.tipo_venta in :nameCanal\n" +
            "and vh.nombre_vehiculo in :nameVehiculo\n" +
            "GROUP BY a.nombre_almacen", nativeQuery = true)
    List<VentasEmpresaAlmacenTipoDateDto> listAllVentasByEmpresaPorMarcasAndCanalAndVehiculo(
            @Param("nameAlmacen") List<String> nameAlmacen,
            @Param("nameMarca") List<String> nameMarca,
            @Param("nameCanal") List<String> nameCanal,
            @Param("nameVehiculo") List<String> nameVehiculo
    );

    @Query(value = "SELECT vh.color  as nomColor, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "join marcas m on vh.id_marca  = m.id_marca \n" +
            "join categorias c on vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE a.nombre_almacen in :nameAlmacen\n" +
            "and m.nombre_marca in :nameMarca\n" +
            "and tv.tipo_venta in :nameCanal\n" +
            "and vh.nombre_vehiculo in :nameVehiculo\n" +
            "GROUP BY vh.color", nativeQuery = true)
    List<VentasEmpresasAlmacenColores> listAllVentasByEmpresaColorPorAlmacenMarcaAndCanalAndVehiculo(
            @Param("nameAlmacen") List<String> nameAlmacen,
            @Param("nameMarca") List<String> nameMarca,
            @Param("nameCanal") List<String> nameCanal,
            @Param("nameVehiculo") List<String> nameVehiculo
    );

    @Query(value = "SELECT tv.tipo_venta  as tipoVentas, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "join marcas m on vh.id_marca  = m.id_marca \n" +
            "join categorias c on vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE a.nombre_almacen in :nameAlmacen\n" +
            "and m.nombre_marca in :nameMarca\n" +
            "and tv.tipo_venta in :nameCanal\n" +
            "and vh.nombre_vehiculo in :nameVehiculo\n" +
            "GROUP BY tv.tipo_venta", nativeQuery = true)
    List<VentasCanalesEmpresaDto> listAllVentasByEmpresaPorAlmacenMarcaAndCanalAndVehiculo(
            @Param("nameAlmacen") List<String> nameAlmacen,
            @Param("nameMarca") List<String> nameMarca,
            @Param("nameCanal") List<String> nameCanal,
            @Param("nameVehiculo") List<String> nameVehiculo
    );
    // ANOTHER 3
    @Query(value = "SELECT vh.color  as nomColor, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "join marcas m on vh.id_marca  = m.id_marca \n" +
            "join categorias c on vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE a.nombre_almacen in :nameAlmacen\n" +
            "AND c.nombre_categoria in :nameCategoria\n" +
            "and m.nombre_marca in :nameMarca\n" +
            "and vh.nombre_vehiculo in :nameVehiculo\n" +
            "GROUP BY vh.color", nativeQuery = true)
    List<VentasEmpresasAlmacenColores> listAllVentasByEmpresaColorPorAlmacenAndCategoriaAndMarcaAndVehiculo(
            @Param("nameAlmacen") List<String> nameAlmacen,
            @Param("nameCategoria") List<String> nameCategoria,
            @Param("nameMarca") List<String> nameMarca,
            @Param("nameVehiculo") List<String> nameVehiculo
    );

    @Query(value = "SELECT tv.tipo_venta  as tipoVentas, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "join marcas m on vh.id_marca  = m.id_marca \n" +
            "join categorias c on vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE a.nombre_almacen in :nameAlmacen\n" +
            "AND c.nombre_categoria in :nameCategoria\n" +
            "and m.nombre_marca in :nameMarca\n" +
            "and vh.nombre_vehiculo in :nameVehiculo\n" +
            "GROUP BY tv.tipo_venta", nativeQuery = true)
    List<VentasCanalesEmpresaDto> listAllVentasByEmpresaPorAlmacenAndCategoriaAndMarcaAndVehiculo(
            @Param("nameAlmacen") List<String> nameAlmacen,
            @Param("nameCategoria") List<String> nameCategoria,
            @Param("nameMarca") List<String> nameMarca,
            @Param("nameVehiculo") List<String> nameVehiculo
    );

    @Query(value = "SELECT a.nombre_almacen  as nomAlmacen, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "join marcas m on vh.id_marca  = m.id_marca \n" +
            "join categorias c on vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE a.nombre_almacen in :nameAlmacen\n" +
            "AND c.nombre_categoria in :nameCategoria\n" +
            "and m.nombre_marca in :nameMarca\n" +
            "and vh.nombre_vehiculo in :nameVehiculo\n" +
            "GROUP BY a.nombre_almacen", nativeQuery = true)
    List<VentasEmpresaAlmacenTipoDateDto> listAllVentasByEmpresaPorTipoAndMarcasAndVehiculo(
            @Param("nameAlmacen") List<String> nameAlmacen,
            @Param("nameCategoria") List<String> nameCategoria,
            @Param("nameMarca") List<String> nameMarca,
            @Param("nameVehiculo") List<String> nameVehiculo
    );


    @Query(value = "SELECT EXTRACT(YEAR FROM v.fecha_venta) as yearVenta, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "JOIN marcas m ON vh.id_marca  = m.id_marca \n" +
            "JOIN categorias c ON vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE a.nombre_almacen IN :nameAlmacen\n" +
            "AND c.nombre_categoria IN :nameCategoria\n" +
            "and m.nombre_marca in :nameMarca\n" +
            "and vh.nombre_vehiculo in :nameVehiculo\n" +
            "GROUP BY EXTRACT(YEAR FROM v.fecha_venta)", nativeQuery = true)
    List<VentasEmpresasAlmacenesYearsDto> listAllVentasByEmpresaYearPorAlmacenAndCategoriaAndMarcaAndVehiculo(
            @Param("nameAlmacen") List<String> nameAlmacen,
            @Param("nameCategoria") List<String> nameCategoria,
            @Param("nameMarca") List<String> nameMarca,
            @Param("nameVehiculo") List<String> nameVehiculo
    );

    // ANOTHER 4

    @Query(value = "SELECT a.nombre_almacen  as nomAlmacen, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "join marcas m on vh.id_marca  = m.id_marca \n" +
            "join categorias c on vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE a.nombre_almacen in :nameAlmacen\n" +
            "AND c.nombre_categoria in :nameCategoria\n" +
            "and tv.tipo_venta in :nameCanal\n" +
            "GROUP BY a.nombre_almacen", nativeQuery = true)
    List<VentasEmpresaAlmacenTipoDateDto> listAllVentasByEmpresaPorTipoAndCanal(
            @Param("nameAlmacen") List<String> nameAlmacen,
            @Param("nameCategoria") List<String> nameCategoria,
            @Param("nameCanal") List<String> nameCanal
    );


    @Query(value = "SELECT EXTRACT(YEAR FROM v.fecha_venta) as yearVenta, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "JOIN marcas m ON vh.id_marca  = m.id_marca \n" +
            "JOIN categorias c ON vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE a.nombre_almacen IN :nameAlmacen\n" +
            "AND c.nombre_categoria IN :nameCategoria\n" +
            "and tv.tipo_venta in :nameCanal\n" +
            "GROUP BY EXTRACT(YEAR FROM v.fecha_venta)", nativeQuery = true)
    List<VentasEmpresasAlmacenesYearsDto> listAllVentasByEmpresaYearPorAlmacenAndCategoriaAndCanal(
            @Param("nameAlmacen") List<String> nameAlmacen,
            @Param("nameCategoria") List<String> nameCategoria,
            @Param("nameCanal") List<String> nameCanal
    );

    @Query(value = "SELECT tv.tipo_venta  as tipoVentas, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "join marcas m on vh.id_marca  = m.id_marca \n" +
            "join categorias c on vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE a.nombre_almacen in :nameAlmacen\n" +
            "AND c.nombre_categoria in :nameCategoria\n" +
            "and tv.tipo_venta in :nameCanal\n" +
            "GROUP BY tv.tipo_venta", nativeQuery = true)
    List<VentasCanalesEmpresaDto> listAllVentasByEmpresaPorAlmacenAndCategoriaAndCanal(
            @Param("nameAlmacen") List<String> nameAlmacen,
            @Param("nameCategoria") List<String> nameCategoria,
            @Param("nameCanal") List<String> nameCanal
    );

    @Query(value = "SELECT vh.color  as nomColor, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "join marcas m on vh.id_marca  = m.id_marca \n" +
            "join categorias c on vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE a.nombre_almacen in :nameAlmacen\n" +
            "AND c.nombre_categoria in :nameCategoria\n" +
            "and tv.tipo_venta in :nameCanal\n" +
            "GROUP BY vh.color", nativeQuery = true)
    List<VentasEmpresasAlmacenColores> listAllVentasByEmpresaColorPorAlmacenAndCategoriaAndCanal(
            @Param("nameAlmacen") List<String> nameAlmacen,
            @Param("nameCategoria") List<String> nameCategoria,
            @Param("nameCanal") List<String> nameCanal
    );

    // ANOTHER 5
    @Query(value = "SELECT EXTRACT(YEAR FROM v.fecha_venta) as yearVenta, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "JOIN marcas m ON vh.id_marca  = m.id_marca \n" +
            "JOIN categorias c ON vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE a.nombre_almacen IN :nameAlmacen\n" +
            "and m.nombre_marca in :nameMarca\n" +
            "and tv.tipo_venta in :nameCanal\n" +
            "GROUP BY EXTRACT(YEAR FROM v.fecha_venta)", nativeQuery = true)
    List<VentasEmpresasAlmacenesYearsDto> listAllVentasByEmpresaYearPorAlmacenMarcaAndCanal(
            @Param("nameAlmacen") List<String> nameAlmacen,
            @Param("nameMarca") List<String> nameMarca,
            @Param("nameCanal") List<String> nameCanal
    );

    @Query(value = "SELECT a.nombre_almacen  as nomAlmacen, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "join marcas m on vh.id_marca  = m.id_marca \n" +
            "join categorias c on vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE a.nombre_almacen in :nameAlmacen\n" +
            "and m.nombre_marca in :nameMarca\n" +
            "and tv.tipo_venta in :nameCanal\n" +
            "GROUP BY a.nombre_almacen", nativeQuery = true)
    List<VentasEmpresaAlmacenTipoDateDto> listAllVentasByEmpresaPorMarcasAndCanal(
            @Param("nameAlmacen") List<String> nameAlmacen,
            @Param("nameMarca") List<String> nameMarca,
            @Param("nameCanal") List<String> nameCanal
    );

    @Query(value = "SELECT vh.color  as nomColor, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "join marcas m on vh.id_marca  = m.id_marca \n" +
            "join categorias c on vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE a.nombre_almacen in :nameAlmacen\n" +
            "and m.nombre_marca in :nameMarca\n" +
            "and tv.tipo_venta in :nameCanal\n" +
            "GROUP BY vh.color", nativeQuery = true)
    List<VentasEmpresasAlmacenColores> listAllVentasByEmpresaColorPorAlmacenMarcaAndCanal(
            @Param("nameAlmacen") List<String> nameAlmacen,
            @Param("nameMarca") List<String> nameMarca,
            @Param("nameCanal") List<String> nameCanal
    );

    @Query(value = "SELECT tv.tipo_venta  as tipoVentas, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "join marcas m on vh.id_marca  = m.id_marca \n" +
            "join categorias c on vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE a.nombre_almacen in :nameAlmacen\n" +
            "and m.nombre_marca in :nameMarca\n" +
            "and tv.tipo_venta in :nameCanal\n" +
            "GROUP BY tv.tipo_venta", nativeQuery = true)
    List<VentasCanalesEmpresaDto> listAllVentasByEmpresaPorAlmacenMarcaAndCanal(
            @Param("nameAlmacen") List<String> nameAlmacen,
            @Param("nameMarca") List<String> nameMarca,
            @Param("nameCanal") List<String> nameCanal
    );
    // ANOTHER 6
    @Query(value = "SELECT EXTRACT(YEAR FROM v.fecha_venta) as yearVenta, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "JOIN marcas m ON vh.id_marca  = m.id_marca \n" +
            "JOIN categorias c ON vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE a.nombre_almacen IN :nameAlmacen\n" +
            "and tv.tipo_venta in :nameCanal\n" +
            "and vh.nombre_vehiculo in :nameVehiculo\n" +
            "GROUP BY EXTRACT(YEAR FROM v.fecha_venta)", nativeQuery = true)
    List<VentasEmpresasAlmacenesYearsDto> listAllVentasByEmpresaYearPorAlmacenAndCanalAndVehiculo(
            @Param("nameAlmacen") List<String> nameAlmacen,
            @Param("nameCanal") List<String> nameCanal,
            @Param("nameVehiculo") List<String> nameVehiculo
    );

    @Query(value = "SELECT a.nombre_almacen  as nomAlmacen, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "join marcas m on vh.id_marca  = m.id_marca \n" +
            "join categorias c on vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE a.nombre_almacen in :nameAlmacen\n" +
            "and tv.tipo_venta in :nameCanal\n" +
            "and vh.nombre_vehiculo in :nameVehiculo\n" +
            "GROUP BY a.nombre_almacen", nativeQuery = true)
    List<VentasEmpresaAlmacenTipoDateDto> listAllVentasByEmpresaPorCanalAndVehiculo(
            @Param("nameAlmacen") List<String> nameAlmacen,
            @Param("nameCanal") List<String> nameCanal,
            @Param("nameVehiculo") List<String> nameVehiculo
    );

    @Query(value = "SELECT vh.color  as nomColor, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "join marcas m on vh.id_marca  = m.id_marca \n" +
            "join categorias c on vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE a.nombre_almacen in :nameAlmacen\n" +
            "and tv.tipo_venta in :nameCanal\n" +
            "and vh.nombre_vehiculo in :nameVehiculo\n" +
            "GROUP BY vh.color", nativeQuery = true)
    List<VentasEmpresasAlmacenColores> listAllVentasByEmpresaColorPorAlmacenCanalAndVehiculo(
            @Param("nameAlmacen") List<String> nameAlmacen,
            @Param("nameCanal") List<String> nameCanal,
            @Param("nameVehiculo") List<String> nameVehiculo
    );

    @Query(value = "SELECT tv.tipo_venta  as tipoVentas, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "join marcas m on vh.id_marca  = m.id_marca \n" +
            "join categorias c on vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE a.nombre_almacen in :nameAlmacen\n" +
            "and tv.tipo_venta in :nameCanal\n" +
            "and vh.nombre_vehiculo in :nameVehiculo\n" +
            "GROUP BY tv.tipo_venta", nativeQuery = true)
    List<VentasCanalesEmpresaDto> listAllVentasByEmpresaPorAlmacenAndCanalAndVehiculo(
            @Param("nameAlmacen") List<String> nameAlmacen,
            @Param("nameCanal") List<String> nameCanal,
            @Param("nameVehiculo") List<String> nameVehiculo
    );
    // ANOTHER 7
    @Query(value = "SELECT vh.color  as nomColor, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "join marcas m on vh.id_marca  = m.id_marca \n" +
            "join categorias c on vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE a.nombre_almacen in :nameAlmacen\n" +
            "AND c.nombre_categoria in :nameCategoria\n" +
            "and vh.nombre_vehiculo in :nameVehiculo\n" +
            "GROUP BY vh.color", nativeQuery = true)
    List<VentasEmpresasAlmacenColores> listAllVentasByEmpresaColorPorAlmacenAndCategoriaAndVehiculo(
            @Param("nameAlmacen") List<String> nameAlmacen,
            @Param("nameCategoria") List<String> nameCategoria,
            @Param("nameVehiculo") List<String> nameVehiculo
    );

    @Query(value = "SELECT tv.tipo_venta  as tipoVentas, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "join marcas m on vh.id_marca  = m.id_marca \n" +
            "join categorias c on vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE a.nombre_almacen in :nameAlmacen\n" +
            "AND c.nombre_categoria in :nameCategoria\n" +
            "and vh.nombre_vehiculo in :nameVehiculo\n" +
            "GROUP BY tv.tipo_venta", nativeQuery = true)
    List<VentasCanalesEmpresaDto> listAllVentasByEmpresaPorAlmacenAndCategoriaAndVehiculo(
            @Param("nameAlmacen") List<String> nameAlmacen,
            @Param("nameCategoria") List<String> nameCategoria,
            @Param("nameVehiculo") List<String> nameVehiculo
    );

    @Query(value = "SELECT a.nombre_almacen  as nomAlmacen, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "join marcas m on vh.id_marca  = m.id_marca \n" +
            "join categorias c on vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE a.nombre_almacen in :nameAlmacen\n" +
            "AND c.nombre_categoria in :nameCategoria\n" +
            "and vh.nombre_vehiculo in :nameVehiculo\n" +
            "GROUP BY a.nombre_almacen", nativeQuery = true)
    List<VentasEmpresaAlmacenTipoDateDto> listAllVentasByEmpresaPorAlamcenesAndCateAndAndVehiculo(
            @Param("nameAlmacen") List<String> nameAlmacen,
            @Param("nameCategoria") List<String> nameCategoria,
            @Param("nameVehiculo") List<String> nameVehiculo
    );


    @Query(value = "SELECT EXTRACT(YEAR FROM v.fecha_venta) as yearVenta, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "JOIN marcas m ON vh.id_marca  = m.id_marca \n" +
            "JOIN categorias c ON vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE a.nombre_almacen IN :nameAlmacen\n" +
            "AND c.nombre_categoria IN :nameCategoria\n" +
            "and vh.nombre_vehiculo in :nameVehiculo\n" +
            "GROUP BY EXTRACT(YEAR FROM v.fecha_venta)", nativeQuery = true)
    List<VentasEmpresasAlmacenesYearsDto> listAllVentasByEmpresaYearPorAlmacenAndCategoriaAndVehiculo(
            @Param("nameAlmacen") List<String> nameAlmacen,
            @Param("nameCategoria") List<String> nameCategoria,
            @Param("nameVehiculo") List<String> nameVehiculo
    );
    // ANOTHER 8
    @Query(value = "SELECT vh.color  as nomColor, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "join marcas m on vh.id_marca  = m.id_marca \n" +
            "join categorias c on vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE a.nombre_almacen in :nameAlmacen\n" +
            "and m.nombre_marca in :nameMarca\n" +
            "and vh.nombre_vehiculo in :nameVehiculo\n" +
            "GROUP BY vh.color", nativeQuery = true)
    List<VentasEmpresasAlmacenColores> listAllVentasByEmpresaColorPorAlmacenAndMarcaAndVehiculo(
            @Param("nameAlmacen") List<String> nameAlmacen,
            @Param("nameMarca") List<String> nameMarca,
            @Param("nameVehiculo") List<String> nameVehiculo
    );

    @Query(value = "SELECT tv.tipo_venta  as tipoVentas, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "join marcas m on vh.id_marca  = m.id_marca \n" +
            "join categorias c on vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE a.nombre_almacen in :nameAlmacen\n" +
            "and m.nombre_marca in :nameMarca\n" +
            "and vh.nombre_vehiculo in :nameVehiculo\n" +
            "GROUP BY tv.tipo_venta", nativeQuery = true)
    List<VentasCanalesEmpresaDto> listAllVentasByEmpresaPorCanalesdMarcaAndVehiculo(
            @Param("nameAlmacen") List<String> nameAlmacen,
            @Param("nameMarca") List<String> nameMarca,
            @Param("nameVehiculo") List<String> nameVehiculo
    );

    @Query(value = "SELECT a.nombre_almacen  as nomAlmacen, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "join marcas m on vh.id_marca  = m.id_marca \n" +
            "join categorias c on vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE a.nombre_almacen in :nameAlmacen\n" +
            "and m.nombre_marca in :nameMarca\n" +
            "and vh.nombre_vehiculo in :nameVehiculo\n" +
            "GROUP BY a.nombre_almacen", nativeQuery = true)
    List<VentasEmpresaAlmacenTipoDateDto> listAllVentasByEmpresaPorAlamcenesAndMarcasAndVehiculo(
            @Param("nameAlmacen") List<String> nameAlmacen,
            @Param("nameMarca") List<String> nameMarca,
            @Param("nameVehiculo") List<String> nameVehiculo
    );


    @Query(value = "SELECT EXTRACT(YEAR FROM v.fecha_venta) as yearVenta, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "JOIN marcas m ON vh.id_marca  = m.id_marca \n" +
            "JOIN categorias c ON vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE a.nombre_almacen IN :nameAlmacen\n" +
            "and m.nombre_marca in :nameMarca\n" +
            "and vh.nombre_vehiculo in :nameVehiculo\n" +
            "GROUP BY EXTRACT(YEAR FROM v.fecha_venta)", nativeQuery = true)
    List<VentasEmpresasAlmacenesYearsDto> listAllVentasByEmpresaYearPorAlmacenAndMarcaAndVehiculo(
            @Param("nameAlmacen") List<String> nameAlmacen,
            @Param("nameMarca") List<String> nameMarca,
            @Param("nameVehiculo") List<String> nameVehiculo
    );
    // ANOTHER 9 CANLES
    @Query(value = "SELECT EXTRACT(YEAR FROM v.fecha_venta) as yearVenta, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "JOIN marcas m ON vh.id_marca  = m.id_marca \n" +
            "JOIN categorias c ON vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE a.nombre_almacen IN :nameAlmacen\n" +
            "and tv.tipo_venta in :nameCanal\n" +
            "GROUP BY EXTRACT(YEAR FROM v.fecha_venta)", nativeQuery = true)
    List<VentasEmpresasAlmacenesYearsDto> listAllVentasByEmpresaYearPorAlmacenAndCanal(
            @Param("nameAlmacen") List<String> nameAlmacen,
            @Param("nameCanal") List<String> nameCanal
    );

    @Query(value = "SELECT a.nombre_almacen  as nomAlmacen, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "join marcas m on vh.id_marca  = m.id_marca \n" +
            "join categorias c on vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE a.nombre_almacen in :nameAlmacen\n" +
            "and tv.tipo_venta in :nameCanal\n" +
            "GROUP BY a.nombre_almacen", nativeQuery = true)
    List<VentasEmpresaAlmacenTipoDateDto> listAllVentasByEmpresaPorCanal(
            @Param("nameAlmacen") List<String> nameAlmacen,
            @Param("nameCanal") List<String> nameCanal
    );

    @Query(value = "SELECT vh.color  as nomColor, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "join marcas m on vh.id_marca  = m.id_marca \n" +
            "join categorias c on vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE a.nombre_almacen in :nameAlmacen\n" +
            "and tv.tipo_venta in :nameCanal\n" +
            "GROUP BY vh.color", nativeQuery = true)
    List<VentasEmpresasAlmacenColores> listAllVentasByEmpresaColorPorAlmacenCanal(
            @Param("nameAlmacen") List<String> nameAlmacen,
            @Param("nameCanal") List<String> nameCanal
    );

    @Query(value = "SELECT tv.tipo_venta  as tipoVentas, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "join marcas m on vh.id_marca  = m.id_marca \n" +
            "join categorias c on vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE a.nombre_almacen in :nameAlmacen\n" +
            "and tv.tipo_venta in :nameCanal\n" +
            "GROUP BY tv.tipo_venta", nativeQuery = true)
    List<VentasCanalesEmpresaDto> listAllVentasByEmpresaPorAlmacenAndCanal(
            @Param("nameAlmacen") List<String> nameAlmacen,
            @Param("nameCanal") List<String> nameCanal
    );
    // ANOTHER 10 VEHICULOS
    @Query(value = "SELECT vh.color  as nomColor, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "join marcas m on vh.id_marca  = m.id_marca \n" +
            "join categorias c on vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE a.nombre_almacen in :nameAlmacen\n" +
            "and vh.nombre_vehiculo in :nameVehiculo\n" +
            "GROUP BY vh.color", nativeQuery = true)
    List<VentasEmpresasAlmacenColores> listAllVentasByEmpresaColorPorAlmacenAndVehiculo(
            @Param("nameAlmacen") List<String> nameAlmacen,
            @Param("nameVehiculo") List<String> nameVehiculo
    );

    @Query(value = "SELECT tv.tipo_venta  as tipoVentas, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "join marcas m on vh.id_marca  = m.id_marca \n" +
            "join categorias c on vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE a.nombre_almacen in :nameAlmacen\n" +
            "and vh.nombre_vehiculo in :nameVehiculo\n" +
            "GROUP BY tv.tipo_venta", nativeQuery = true)
    List<VentasCanalesEmpresaDto> listAllVentasByEmpresaPorCanalesAndVehiculo(
            @Param("nameAlmacen") List<String> nameAlmacen,
            @Param("nameVehiculo") List<String> nameVehiculo
    );

    @Query(value = "SELECT a.nombre_almacen  as nomAlmacen, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "join marcas m on vh.id_marca  = m.id_marca \n" +
            "join categorias c on vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE a.nombre_almacen in :nameAlmacen\n" +
            "and vh.nombre_vehiculo in :nameVehiculo\n" +
            "GROUP BY a.nombre_almacen", nativeQuery = true)
    List<VentasEmpresaAlmacenTipoDateDto> listAllVentasByEmpresaPorAlamcenesAndVehiculo(
            @Param("nameAlmacen") List<String> nameAlmacen,
            @Param("nameVehiculo") List<String> nameVehiculo
    );


    @Query(value = "SELECT EXTRACT(YEAR FROM v.fecha_venta) as yearVenta, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "JOIN marcas m ON vh.id_marca  = m.id_marca \n" +
            "JOIN categorias c ON vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE a.nombre_almacen IN :nameAlmacen\n" +
            "and vh.nombre_vehiculo in :nameVehiculo\n" +
            "GROUP BY EXTRACT(YEAR FROM v.fecha_venta)", nativeQuery = true)
    List<VentasEmpresasAlmacenesYearsDto> listAllVentasByEmpresaYearPorAlmacenAndVehiculo(
            @Param("nameAlmacen") List<String> nameAlmacen,
            @Param("nameVehiculo") List<String> nameVehiculo
    );
    // ANOTHER 11 MARCAS
    @Query(value = "SELECT vh.color  as nomColor, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "join marcas m on vh.id_marca  = m.id_marca \n" +
            "join categorias c on vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE a.nombre_almacen in :nameAlmacen\n" +
            "and m.nombre_marca in :nameMarca\n" +
            "GROUP BY vh.color", nativeQuery = true)
    List<VentasEmpresasAlmacenColores> listAllVentasByEmpresaColorPorAlmacenAndMarca(
            @Param("nameAlmacen") List<String> nameAlmacen,
            @Param("nameMarca") List<String> nameMarca
    );

    @Query(value = "SELECT tv.tipo_venta  as tipoVentas, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "join marcas m on vh.id_marca  = m.id_marca \n" +
            "join categorias c on vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE a.nombre_almacen in :nameAlmacen\n" +
            "and m.nombre_marca in :nameMarca\n" +
            "GROUP BY tv.tipo_venta", nativeQuery = true)
    List<VentasCanalesEmpresaDto> listAllVentasByEmpresaPorCanalesdMarca(
            @Param("nameAlmacen") List<String> nameAlmacen,
            @Param("nameMarca") List<String> nameMarca
    );

    @Query(value = "SELECT a.nombre_almacen  as nomAlmacen, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "join marcas m on vh.id_marca  = m.id_marca \n" +
            "join categorias c on vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE a.nombre_almacen in :nameAlmacen\n" +
            "and m.nombre_marca in :nameMarca\n" +
            "GROUP BY a.nombre_almacen", nativeQuery = true)
    List<VentasEmpresaAlmacenTipoDateDto> listAllVentasByEmpresaPorAlamcenesAndMarcas(
            @Param("nameAlmacen") List<String> nameAlmacen,
            @Param("nameMarca") List<String> nameMarca
    );


    @Query(value = "SELECT EXTRACT(YEAR FROM v.fecha_venta) as yearVenta, COUNT(DISTINCT v.id_venta) AS numVentas\n" +
            "FROM ventas v \n" +
            "JOIN detalles_ventas dv ON v.id_venta = dv.id_venta \n" +
            "JOIN vehiculos vh ON dv.id_vehiculo = vh.id_vehiculo \n" +
            "JOIN marcas m ON vh.id_marca  = m.id_marca \n" +
            "JOIN categorias c ON vh.id_categoria = c.id_categoria \n" +
            "JOIN almacenes a ON vh.id_almacen = a.id_almacen \n" +
            "JOIN empreas e ON a.id_empresa = e.id_empresa \n" +
            "JOIN tipos_ventas tv ON dv.id_tipo_venta = tv.id_tipo_venta \n" +
            "WHERE a.nombre_almacen IN :nameAlmacen\n" +
            "and m.nombre_marca in :nameMarca\n" +
            "GROUP BY EXTRACT(YEAR FROM v.fecha_venta)", nativeQuery = true)
    List<VentasEmpresasAlmacenesYearsDto> listAllVentasByEmpresaYearPorAlmacenAndMarca(
            @Param("nameAlmacen") List<String> nameAlmacen,
            @Param("nameMarca") List<String> nameMarca
    );
}
