package com.fin.banco.backend.service;

import com.fin.banco.backend.model.ClienteDTO;
import com.fin.banco.backend.model.CuentaDTO;
import com.fin.banco.backend.model.MovimientoDTO;
import com.fin.banco.backend.model.MovimientoDTO;
import com.fin.banco.backend.model.repository.CuentaRepository;
import com.fin.banco.backend.model.repository.MovimientoRepository;
import com.fin.banco.backend.response.Movimiento;
import com.fin.banco.backend.response.InfoRest;
import com.fin.banco.backend.response.Movimiento;
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
public class MovimientoServiceImpl implements MovimientoService{

    private static final Logger log = LoggerFactory.getLogger(MovimientoServiceImpl.class);
    @Autowired
    MovimientoRepository movimientoRepository;

    @Autowired
    CuentaRepository cuentaRepository;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity listar() {
        ResponseRest<Movimiento> movimientoResponse = new ResponseRest();
        List<InfoRest> infoRestList = new ArrayList<>();
        List<Movimiento> movimientoList = new ArrayList<>();
        try {
            List<MovimientoDTO> movimientoDTOList = movimientoRepository.findAll();
            movimientoDTOList.stream().forEach(movimientoDTO -> {
                movimientoList.add(EntityMapper.castMovimientoDTOtoMovimiento(movimientoDTO));
            });
            movimientoResponse.setDatos(movimientoList);
        }catch (Exception e){
            log.error("Error al consutar Movimientos", e.getMessage());
            infoRestList.add(new InfoRest(-1,"Error al consultar Movimientos","Respuesta NOK"));
            movimientoResponse.setInfoRestList(infoRestList);
            return new ResponseEntity<ResponseRest>(movimientoResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        infoRestList.add(new InfoRest(00,"Respuesta exitosa","Respuesta OK"));
        movimientoResponse.setInfoRestList(infoRestList);
        return new ResponseEntity<ResponseRest>(movimientoResponse, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity crear(Movimiento movimiento) {
        ResponseRest<Movimiento> movimientoResponse = new ResponseRest<>();
        List<Movimiento> movimientoList = new ArrayList<>();
        List<InfoRest> infoRestList = new ArrayList<>();

        try {
            Optional<CuentaDTO> cuentaGuardado = cuentaRepository.findById(movimiento.getCuentaId());
            if(!cuentaGuardado.isPresent()){
                log.error("No se encuentra cuenta");
                infoRestList.add(new InfoRest(-1, "Movimiento no creado", "Respuesta NOK"));
                movimientoResponse.setInfoRestList(infoRestList);
                return new ResponseEntity<ResponseRest>(movimientoResponse, HttpStatus.NOT_FOUND);
            }else {

                if(cuentaGuardado.get().getSaldoInicial() == 0 && "retiro".equalsIgnoreCase(movimiento.getTipoMovimiento())){
                    log.error("Saldo no disponible");
                    infoRestList.add(new InfoRest(-1, "Saldo no disponible", "Respuesta NOK"));
                    movimientoResponse.setInfoRestList(infoRestList);
                    return new ResponseEntity<ResponseRest>(movimientoResponse, HttpStatus.BAD_REQUEST);
                }
                movimiento.setSaldo(cuentaGuardado.get().getSaldoInicial());
                MovimientoDTO movimientoGuardado = EntityMapperDTO.castMovimientoToMovimientoDTO(new MovimientoDTO(), movimiento);
                cuentaGuardado.get().setSaldoInicial(movimientoGuardado.getSaldo());
                movimientoGuardado.setCuenta(cuentaGuardado.get());

                movimientoGuardado = movimientoRepository.save(movimientoGuardado);
                if (movimientoGuardado != null) {
                    movimientoList.add(EntityMapper.castMovimientoDTOtoMovimiento(movimientoGuardado));
                    movimientoResponse.setDatos(movimientoList);
                } else {
                    log.error("Error al crear Movimiento");
                    infoRestList.add(new InfoRest(-1, "Movimiento no creado", "Respuesta NOK"));
                    movimientoResponse.setInfoRestList(infoRestList);
                    return new ResponseEntity<ResponseRest>(movimientoResponse, HttpStatus.BAD_REQUEST);
                }
            }
        }catch (Exception e){
            log.error("Error al crear Movimiento", e.getMessage());
            infoRestList.add(new InfoRest(-1,"Movimiento no creado","Respuesta NOK"));
            movimientoResponse.setInfoRestList(infoRestList);
            return new ResponseEntity<ResponseRest>(movimientoResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        infoRestList.add(new InfoRest(00,"Movimiento creado","Respuesta OK"));
        movimientoResponse.setInfoRestList(infoRestList);

        return new ResponseEntity<ResponseRest>(movimientoResponse, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity editar(Movimiento movimiento, Long id) {
        ResponseRest<Movimiento> movimientoResponse = new ResponseRest();
        List<Movimiento> movimientoList = new ArrayList<>();
        List<InfoRest> infoRestList = new ArrayList<>();

        try{

            Optional<MovimientoDTO> movimientoBuscado = movimientoRepository.findById(id);
            if(movimientoBuscado.isPresent()){
                MovimientoDTO movimientoActualizar = EntityMapperDTO.castMovimientoToMovimientoDTO(movimientoBuscado.get(),movimiento);
                movimientoActualizar = movimientoRepository.save(movimientoActualizar);
                if(movimientoActualizar != null){
                    movimientoList.add(EntityMapper.castMovimientoDTOtoMovimiento(movimientoActualizar));
                    movimientoResponse.setDatos(movimientoList);
                }else{
                    log.error("Error al editar Movimiento");
                    infoRestList.add(new InfoRest(-1,"Movimiento no editado","Respuesta NOK"));
                    movimientoResponse.setInfoRestList(infoRestList);
                    return new ResponseEntity<ResponseRest>(movimientoResponse, HttpStatus.BAD_REQUEST);
                }
            }else{
                log.error("Error al buscar Movimiento");
                infoRestList.add(new InfoRest(-1,"Movimiento no encontrado","Respuesta NOK"));
                movimientoResponse.setInfoRestList(infoRestList);
                return new ResponseEntity<ResponseRest>(movimientoResponse, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.error("Error al editar Movimiento", e.getMessage());
            infoRestList.add(new InfoRest(-1,"Movimiento no editado","Respuesta NOK"));
            movimientoResponse.setInfoRestList(infoRestList);
            return new ResponseEntity<ResponseRest>(movimientoResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        infoRestList.add(new InfoRest(00,"Movimiento editado","Respuesta OK"));
        movimientoResponse.setInfoRestList(infoRestList);

        return new ResponseEntity<ResponseRest>(movimientoResponse, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity eliminar(Long id) {
        ResponseRest<Movimiento> movimientoResponse = new ResponseRest<>();
        List<InfoRest> infoRestList = new ArrayList<>();

        try {
            Optional<MovimientoDTO> movimientoBuscado = movimientoRepository.findById(id);
            if(movimientoBuscado.isPresent()){
                movimientoRepository.deleteById(id);
            }else{
                log.error("Error al buscar Movimiento");
                infoRestList.add(new InfoRest(-1,"Movimiento no encontrado","Respuesta NOK"));
                movimientoResponse.setInfoRestList(infoRestList);
                return new ResponseEntity<ResponseRest>(movimientoResponse, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.error("Error al eliminar Movimiento", e.getMessage());
            infoRestList.add(new InfoRest(-1,"Movimiento no eliminado","Respuesta NOK"));
            movimientoResponse.setInfoRestList(infoRestList);
            return new ResponseEntity<ResponseRest>(movimientoResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        infoRestList.add(new InfoRest(00,"Movimiento eliminado","Respuesta OK"));
        movimientoResponse.setInfoRestList(infoRestList);

        return new ResponseEntity<ResponseRest>(movimientoResponse, HttpStatus.OK);
    }
}
