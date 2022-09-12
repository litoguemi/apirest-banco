package com.fin.banco.backend.model.repository;

import com.fin.banco.backend.model.ReporteDTO;
import com.fin.banco.backend.request.ReporteRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReporteRepository extends JpaRepository<ReporteDTO, Long> {

    /*@Query(value = " select mo.fecha, per.nombre, cu.numero_cuenta, cu.tipo_cuenta, cu.saldo_inicial, cu.estado, mo.valor, mo.saldo" +
                   " from movimiento mo, cuenta cu, cliente cl, persona per " +
                   " where mo.fecha between :fechaInicio and :fechaFin " +
                   "     and cl.cliente_id = :clienteId" +
                   "     and mo.cuenta_id = cu.cuenta_id" +
                   "    and cu.cliente_id = cl.cliente_id" +
                   "    and cl.persona_id = per.persona_id" +
                   " order by mo.fecha desc",nativeQuery = true)*/
    @Query(value = " select mv.fecha, per.nombre, cu.numeroCuenta, cu.tipoCuenta, cu.saldoInicial, cu.estado, mv.valor, mv.saldo " +
                   " from PersonaDTO per inner join ClienteDTO cl on cl.personaDTO.id = per.id" +
                   "      inner join CuentaDTO cu on cu.cliente.id = cl.id" +
                   "      inner join MovimientoDTO mv on mv.cuenta.id = cu.id" +
                   " where trunc(mv.fecha) between cast(:fechaInicio as timestamp)  and cast(:fechaFin as timestamp )" +
                   "       and cl.id = :clienteId" +
                   " order by mv.fecha DESC")
    List<Object[]> consultarMovimientos(@Param("fechaInicio")LocalDate fechaInicio, @Param("fechaFin") LocalDate fechaFin, @Param("clienteId") Long clienteId);
}
