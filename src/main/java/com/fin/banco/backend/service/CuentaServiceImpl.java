package com.fin.banco.backend.service;

import com.fin.banco.backend.model.ClienteDTO;
import com.fin.banco.backend.model.CuentaDTO;
import com.fin.banco.backend.model.CuentaDTO;
import com.fin.banco.backend.model.CuentaDTO;
import com.fin.banco.backend.model.repository.ClienteRepository;
import com.fin.banco.backend.model.repository.CuentaRepository;
import com.fin.banco.backend.response.Cuenta;
import com.fin.banco.backend.response.Cuenta;
import com.fin.banco.backend.response.InfoRest;
import com.fin.banco.backend.response.ResponseRest;
import com.fin.banco.backend.service.mapper.EntityMapper;
import com.fin.banco.backend.service.mapper.EntityMapperDTO;
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
    CuentaRepository cuentaRepository;

    @Autowired
    ClienteRepository clienteRepository;


    @Override
    @Transactional(readOnly = true)
    public ResponseEntity listar() {
        ResponseRest<Cuenta> cuentaResponse = new ResponseRest();
        List<InfoRest> infoRestList = new ArrayList<>();
        List<Cuenta> cuentaList = new ArrayList<>();
        try {
            List<CuentaDTO> cuentaDTOList = cuentaRepository.findAll();
            cuentaDTOList.stream().forEach(cuentaDTO -> {
                cuentaList.add(EntityMapper.castCuentaDTOtoCuenta(cuentaDTO));
            });
            cuentaResponse.setDatos(cuentaList);
        }catch (Exception e){
            log.error("Error al consutar Cuentas", e.getMessage());
            infoRestList.add(new InfoRest(-1,"Error al consultar Cuentas","Respuesta NOK"));
            cuentaResponse.setInfoRestList(infoRestList);
            return new ResponseEntity<ResponseRest>(cuentaResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        infoRestList.add(new InfoRest(00,"Respuesta exitosa","Respuesta OK"));
        cuentaResponse.setInfoRestList(infoRestList);
        return new ResponseEntity<ResponseRest>(cuentaResponse, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity crear(Cuenta cuenta) {
        ResponseRest<Cuenta> cuentaResponse = new ResponseRest<>();
        List<Cuenta> cuentaList = new ArrayList<>();
        List<InfoRest> infoRestList = new ArrayList<>();

        try {
            Optional<ClienteDTO> clienteGuardado = clienteRepository.findById(cuenta.getClienteId());
            if(clienteGuardado==null){
                ///error no se encuentra cliente
                log.error("No se encuentra el cliente");
                infoRestList.add(new InfoRest(-1, "Cuenta no creado", "Respuesta NOK"));
                cuentaResponse.setInfoRestList(infoRestList);
                return new ResponseEntity<ResponseRest>(cuentaResponse, HttpStatus.NOT_FOUND);
            }else {
                CuentaDTO cuentaGuardado =EntityMapperDTO.castCuentaToCuentaDTO(new CuentaDTO(), cuenta);
                cuentaGuardado.setCliente(clienteGuardado.get());
                cuentaGuardado = cuentaRepository.save(cuentaGuardado);
                if (cuentaGuardado != null) {
                    cuentaList.add(EntityMapper.castCuentaDTOtoCuenta(cuentaGuardado));
                    cuentaResponse.setDatos(cuentaList);
                } else {
                    log.error("Error al crear Cuenta");
                    infoRestList.add(new InfoRest(-1, "Cuenta no creado", "Respuesta NOK"));
                    cuentaResponse.setInfoRestList(infoRestList);
                    return new ResponseEntity<ResponseRest>(cuentaResponse, HttpStatus.BAD_REQUEST);
                }
            }
        }catch (Exception e){
            log.error("Error al crear Cuenta", e.getMessage());
            infoRestList.add(new InfoRest(-1,"Cuenta no creado","Respuesta NOK"));
            cuentaResponse.setInfoRestList(infoRestList);
            return new ResponseEntity<ResponseRest>(cuentaResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        infoRestList.add(new InfoRest(00,"Cuenta creado","Respuesta OK"));
        cuentaResponse.setInfoRestList(infoRestList);

        return new ResponseEntity<ResponseRest>(cuentaResponse, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity editar(Cuenta cuenta, Long id) {
        ResponseRest<Cuenta> cuentaResponse = new ResponseRest();
        List<Cuenta> cuentaList = new ArrayList<>();
        List<InfoRest> infoRestList = new ArrayList<>();

        try{

            Optional<CuentaDTO> cuentaBuscado = cuentaRepository.findById(id);
            if(cuentaBuscado.isPresent()){
                CuentaDTO cuentaActualizar = EntityMapperDTO.castCuentaToCuentaDTO(cuentaBuscado.get(),cuenta);
                cuentaActualizar = cuentaRepository.save(cuentaActualizar);
                if(cuentaActualizar != null){
                    cuentaList.add(EntityMapper.castCuentaDTOtoCuenta(cuentaActualizar));
                    cuentaResponse.setDatos(cuentaList);
                }else{
                    log.error("Error al editar Cuenta");
                    infoRestList.add(new InfoRest(-1,"Cuenta no editado","Respuesta NOK"));
                    cuentaResponse.setInfoRestList(infoRestList);
                    return new ResponseEntity<ResponseRest>(cuentaResponse, HttpStatus.BAD_REQUEST);
                }
            }else{
                log.error("Error al buscar Cuenta");
                infoRestList.add(new InfoRest(-1,"Cuenta no encontrado","Respuesta NOK"));
                cuentaResponse.setInfoRestList(infoRestList);
                return new ResponseEntity<ResponseRest>(cuentaResponse, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.error("Error al editar Cuenta", e.getMessage());
            infoRestList.add(new InfoRest(-1,"Cuenta no editado","Respuesta NOK"));
            cuentaResponse.setInfoRestList(infoRestList);
            return new ResponseEntity<ResponseRest>(cuentaResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        infoRestList.add(new InfoRest(00,"Cuenta editado","Respuesta OK"));
        cuentaResponse.setInfoRestList(infoRestList);

        return new ResponseEntity<ResponseRest>(cuentaResponse, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity eliminar(Long id) {
        ResponseRest<Cuenta> cuentaResponse = new ResponseRest<>();
        List<InfoRest> infoRestList = new ArrayList<>();

        try {
            Optional<CuentaDTO> cuentaBuscado = cuentaRepository.findById(id);
            if(cuentaBuscado.isPresent()){
                cuentaRepository.deleteById(id);
            }else{
                log.error("Error al buscar Cuenta");
                infoRestList.add(new InfoRest(-1,"Cuenta no encontrado","Respuesta NOK"));
                cuentaResponse.setInfoRestList(infoRestList);
                return new ResponseEntity<ResponseRest>(cuentaResponse, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.error("Error al eliminar Cuenta", e.getMessage());
            infoRestList.add(new InfoRest(-1,"Cuenta no eliminado","Respuesta NOK"));
            cuentaResponse.setInfoRestList(infoRestList);
            return new ResponseEntity<ResponseRest>(cuentaResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        infoRestList.add(new InfoRest(00,"Cuenta eliminado","Respuesta OK"));
        cuentaResponse.setInfoRestList(infoRestList);

        return new ResponseEntity<ResponseRest>(cuentaResponse, HttpStatus.OK);
    }
}
