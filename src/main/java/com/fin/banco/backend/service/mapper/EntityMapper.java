package com.fin.banco.backend.service.mapper;

import com.fin.banco.backend.model.*;
import com.fin.banco.backend.response.*;

public class EntityMapper{

       public static Cliente castPersonaDTOtoCliente(PersonaDTO personaDTO){
              Cliente cliente = new Cliente();
              cliente.setId(personaDTO.getId());
              cliente.setNombre(personaDTO.getNombre());
              cliente.setGenero(personaDTO.getGenero());
              cliente.setEdad(personaDTO.getEdad());
              cliente.setIdentificacion(personaDTO.getIdentificacion());
              cliente.setDireccion(personaDTO.getDireccion());
              cliente.setTelefono(personaDTO.getTelefono());
              return cliente;
       }

       public static Cliente castClienteDTOtoCliente(ClienteDTO clienteDTO){
              Cliente cliente = castPersonaDTOtoCliente(clienteDTO.getPersonaDTO());
              cliente.setClienteId(clienteDTO.getId());
              cliente.setContrasena(clienteDTO.getContrasena());
              cliente.setEstado(clienteDTO.getEstado());
              return cliente;
       }

       public static Cuenta castCuentaDTOtoCuenta(CuentaDTO cuentaDTO){
              Cuenta cuenta = new Cuenta();
              cuenta.setId(cuentaDTO.getId());
              cuenta.setNumeroCuenta(cuentaDTO.getNumeroCuenta());
              cuenta.setTipoCuenta(cuentaDTO.getTipoCuenta());
              cuenta.setSaldoInicial(cuentaDTO.getSaldoInicial());
              cuenta.setEstado(cuentaDTO.getEstado());
              cuenta.setCliente(castClienteDTOtoCliente(cuentaDTO.getCliente()));
              cuenta.setClienteId(cuentaDTO.getCliente().getId());
              return cuenta;
       }

       public static Movimiento castMovimientoDTOtoMovimiento(MovimientoDTO movimientoDTO){
              Movimiento movimiento = new Movimiento();
              movimiento.setId(movimientoDTO.getId());
              movimiento.setFecha(movimientoDTO.getFecha());
              movimiento.setTipoMovimiento(movimientoDTO.getTipoMovimiento());
              movimiento.setValor(movimientoDTO.getValor());
              movimiento.setSaldo(movimientoDTO.getSaldo());
              movimiento.setCuenta(castCuentaDTOtoCuenta(movimientoDTO.getCuenta()));
              movimiento.setCuentaId(movimientoDTO.getCuenta().getId());
              return movimiento;
       }

       public static Reporte castReporteDTOtoReporte(ReporteDTO reporteDTO){
              Reporte reporte = new Reporte();
              reporte.setFecha(reporteDTO.getFecha());
              reporte.setCliente(reporteDTO.getCliente());
              reporte.setNumeroCuenta(reporteDTO.getNumeroCuenta());
              reporte.setTipoCuenta(reporteDTO.getTipoCuenta());
              reporte.setSaldoInicial(reporteDTO.getSaldoInicial());
              reporte.setEstadoCuenta(reporteDTO.getEstadoCuenta());
              reporte.setValorMovimiento(reporteDTO.getValorMovimiento());
              reporte.setSaldoInicial(reporteDTO.getSaldoInicial());
              reporte.setSaldoDisponible(reporteDTO.getSaldoDisponible());
              return reporte;
       }

}
