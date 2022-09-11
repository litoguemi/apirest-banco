package com.fin.banco.backend.service;

import com.fin.banco.backend.model.Cliente;
import com.fin.banco.backend.model.Cuenta;
import com.fin.banco.backend.model.repository.CuentaRepository;
import com.fin.banco.backend.response.ClienteResponse;
import com.fin.banco.backend.response.CuentaResponse;
import com.fin.banco.backend.response.InfoRest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CuentaServiceImpl implements CuentaService{

    private static final Logger log = LoggerFactory.getLogger(CuentaServiceImpl.class);

    @Autowired
    private CuentaRepository cuentaRepository;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity listar() {
        CuentaResponse cuentaResponse = new CuentaResponse();
        List<InfoRest> infoRestList = new ArrayList<>();

        try {
            List<Cuenta> cuentaList = cuentaRepository.findAll();
            cuentaResponse.setDatos(cuentaList);
        }catch (Exception e){
            log.error("Error al consutar Cuentas", e.getMessage());
            infoRestList.add(new InfoRest(-1,"Error al consultar Cuentas","Respuesta NOK"));
            cuentaResponse.setInfoRestList(infoRestList);
            return new ResponseEntity<CuentaResponse>(cuentaResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        infoRestList.add(new InfoRest(00,"Respuesta exitosa","Respuesta OK"));
        cuentaResponse.setInfoRestList(infoRestList);
        return new ResponseEntity<CuentaResponse>(cuentaResponse, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity crear(Cuenta cuenta) {
        CuentaResponse cuentaResponse = new CuentaResponse();
        List<Cuenta> cuentaList= new ArrayList<>();
        List<InfoRest> infoRestList = new ArrayList<>();

        try {
            Cuenta cuentaGuardada = cuentaRepository.save(cuenta);
            if (cuentaGuardada != null){
                cuentaList.add(cuentaGuardada);
                cuentaResponse.setDatos(cuentaList);
            }else{
                log.error("Error al crear Cuenta");
                infoRestList.add(new InfoRest(-1,"Cuenta no creada","Respuesta NOK"));
                cuentaResponse.setInfoRestList(infoRestList);
                return new ResponseEntity<CuentaResponse>(cuentaResponse, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            log.error("Error al crear Cuenta", e.getMessage());
            infoRestList.add(new InfoRest(-1,"Cuenta no creado","Respuesta NOK"));
            cuentaResponse.setInfoRestList(infoRestList);
            return new ResponseEntity<CuentaResponse>(cuentaResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        infoRestList.add(new InfoRest(00,"Cuenta creada","Respuesta OK"));
        cuentaResponse.setInfoRestList(infoRestList);

        return new ResponseEntity<CuentaResponse>(cuentaResponse, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity editar(Cuenta cuenta, Long id) {
        CuentaResponse cuentaResponse = new CuentaResponse();
        List<Cuenta> cuentaList = new ArrayList<>();
        List<InfoRest> infoRestList = new ArrayList<>();

        try{
            Optional<Cuenta> cuentaBuscada = cuentaRepository.findById(id);
            if(cuentaBuscada.isPresent()){
                cuentaBuscada.get().setNumeroCuenta(cuenta.getNumeroCuenta());
                cuentaBuscada.get().setTipoCuenta(cuenta.getTipoCuenta());
                cuentaBuscada.get().setSaldoInicial(cuenta.getSaldoInicial());
                cuentaBuscada.get().setEstado(cuenta.getEstado());
                cuentaBuscada.get().setCliente(cuenta.getCliente());

                Cuenta cuentaActualizar = cuentaRepository.save(cuentaBuscada.get());
                if(cuentaActualizar != null){
                    cuentaList.add(cuentaActualizar);
                    cuentaResponse.setDatos(cuentaList);
                }else{
                    log.error("Error al editar Cuenta");
                    infoRestList.add(new InfoRest(-1,"Cuenta no editada","Respuesta NOK"));
                    cuentaResponse.setInfoRestList(infoRestList);
                    return new ResponseEntity<CuentaResponse>(cuentaResponse, HttpStatus.BAD_REQUEST);
                }
            }else{
                log.error("Error al buscar Cuenta");
                infoRestList.add(new InfoRest(-1,"Cuenta no encontrada","Respuesta NOK"));
                cuentaResponse.setInfoRestList(infoRestList);
                return new ResponseEntity<CuentaResponse>(cuentaResponse, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.error("Error al editar Cuenta", e.getMessage());
            infoRestList.add(new InfoRest(-1,"Cuenta no editada","Respuesta NOK"));
            cuentaResponse.setInfoRestList(infoRestList);
            return new ResponseEntity<CuentaResponse>(cuentaResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        infoRestList.add(new InfoRest(00,"Cuenta editada","Respuesta OK"));
        cuentaResponse.setInfoRestList(infoRestList);

        return new ResponseEntity<CuentaResponse>(cuentaResponse, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity eliminar(Long id) {
        CuentaResponse cuentaResponse = new CuentaResponse();
        List<InfoRest> infoRestList = new ArrayList<>();

        try {
            Optional<Cuenta> cuentaBuscada = cuentaRepository.findById(id);
            if(cuentaBuscada.isPresent()){
                cuentaRepository.deleteById(id);
            }else{
                log.error("Error al buscar Cuenta");
                infoRestList.add(new InfoRest(-1,"Cuenta no encontrada","Respuesta NOK"));
                cuentaResponse.setInfoRestList(infoRestList);
                return new ResponseEntity<CuentaResponse>(cuentaResponse, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.error("Error al eliminar Cuenta", e.getMessage());
            infoRestList.add(new InfoRest(-1,"Cuenta no eliminada","Respuesta NOK"));
            cuentaResponse.setInfoRestList(infoRestList);
            return new ResponseEntity<CuentaResponse>(cuentaResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        infoRestList.add(new InfoRest(00,"Cuenta eliminada","Respuesta OK"));
        cuentaResponse.setInfoRestList(infoRestList);

        return new ResponseEntity<CuentaResponse>(cuentaResponse, HttpStatus.OK);
    }
}
