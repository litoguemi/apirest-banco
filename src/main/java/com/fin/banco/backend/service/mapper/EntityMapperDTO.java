package com.fin.banco.backend.service.mapper;

import com.fin.banco.backend.model.ClienteDTO;
import com.fin.banco.backend.model.CuentaDTO;
import com.fin.banco.backend.model.MovimientoDTO;
import com.fin.banco.backend.model.PersonaDTO;
import com.fin.banco.backend.response.Cliente;
import com.fin.banco.backend.response.Cuenta;
import com.fin.banco.backend.response.Movimiento;
import com.fin.banco.backend.response.Persona;

import java.util.Objects;

public class EntityMapperDTO {

    public static PersonaDTO castClienteToPersonaDTO(Cliente cliente){
        PersonaDTO personaDTO = new PersonaDTO();
        personaDTO.setId(cliente.getId());
        personaDTO.setNombre(cliente.getNombre());
        personaDTO.setGenero(cliente.getGenero());
        personaDTO.setEdad(cliente.getEdad());
        personaDTO.setIdentificacion(cliente.getIdentificacion());
        personaDTO.setDireccion(cliente.getDireccion());
        personaDTO.setTelefono(cliente.getTelefono());
        return personaDTO;
    }

    public static ClienteDTO castClienteToClienteDTO(PersonaDTO personaGuardado,Cliente cliente){
        ClienteDTO clienteDTO = new ClienteDTO();
        clienteDTO.setId(cliente.getClienteId());
        clienteDTO.setContrasena(cliente.getContrasena());
        clienteDTO.setEstado(cliente.getEstado());
        clienteDTO.setPersonaDTO(personaGuardado);

        return clienteDTO;
    }

    public static ClienteDTO castClienteToClienteDTO(ClienteDTO clienteDTO, Cliente cliente){
        if (Objects.isNull(clienteDTO)) {
            clienteDTO = new ClienteDTO();
            clienteDTO.setId(cliente.getClienteId());
            clienteDTO.setPersonaDTO(new PersonaDTO());
            clienteDTO.getPersonaDTO().setId(cliente.getId());
        } else {
            clienteDTO.setPersonaDTO(clienteDTO.getPersonaDTO());
        }
        clienteDTO.setContrasena(cliente.getContrasena());
        clienteDTO.setEstado(cliente.getEstado());
        clienteDTO.getPersonaDTO().setNombre(cliente.getNombre());
        clienteDTO.getPersonaDTO().setGenero(cliente.getGenero());
        clienteDTO.getPersonaDTO().setEdad(cliente.getEdad());
        clienteDTO.getPersonaDTO().setIdentificacion(cliente.getIdentificacion());
        clienteDTO.getPersonaDTO().setDireccion(cliente.getDireccion());
        clienteDTO.getPersonaDTO().setTelefono(cliente.getTelefono());

        return clienteDTO;
    }

    public static CuentaDTO castCuentaToCuentaDTO(CuentaDTO cuentaDTO, Cuenta cuenta){
        if (Objects.isNull(cuentaDTO)) {
            cuentaDTO = new CuentaDTO();
            cuentaDTO.setId(cuenta.getId());
        }
        cuentaDTO.setNumeroCuenta(cuenta.getNumeroCuenta());
        cuentaDTO.setTipoCuenta(cuenta.getTipoCuenta());
        cuentaDTO.setSaldoInicial(cuenta.getSaldoInicial());
        cuentaDTO.setEstado(cuenta.getEstado());

        return cuentaDTO;
    }

    public static MovimientoDTO castMovimientoToMovimientoDTO(MovimientoDTO movimientoDTO,Movimiento movimiento){
        if (Objects.isNull(movimientoDTO)) {
            movimientoDTO = new MovimientoDTO();
            movimientoDTO.setId(movimientoDTO.getId());
        }
        if("retiro".equalsIgnoreCase(movimiento.getTipoMovimiento())){
            movimientoDTO.setValor(movimiento.getValor() * -1);
        } else{
            movimientoDTO.setValor(movimiento.getValor());
        }
        movimientoDTO.setFecha(movimiento.getFecha());
        movimientoDTO.setTipoMovimiento(movimiento.getTipoMovimiento());
        movimientoDTO.setSaldo(movimiento.getSaldo() + movimiento.getValor());

        return movimientoDTO;
    }
}
