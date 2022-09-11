package com.fin.banco.backend.service;

import com.fin.banco.backend.model.Movimiento;
import com.fin.banco.backend.model.repository.MovimientoRepository;
import com.fin.banco.backend.response.InfoRest;
import com.fin.banco.backend.response.MovimientoResponse;
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
    private MovimientoRepository movimientoRepository;


    @Override
    @Transactional(readOnly = true)
    public ResponseEntity listar() {
        MovimientoResponse movimientoResponse = new MovimientoResponse();
        List<InfoRest> infoRestList = new ArrayList<>();

        try {
            List<Movimiento> movimientoList = movimientoRepository.findAll();
            movimientoResponse.setDatos(movimientoList);
        }catch (Exception e){
            log.error("Error al consutar Movimientos", e.getMessage());
            infoRestList.add(new InfoRest(-1,"Error al consultar Movimientos","Respuesta NOK"));
            movimientoResponse.setInfoRestList(infoRestList);
            return new ResponseEntity<MovimientoResponse>(movimientoResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        infoRestList.add(new InfoRest(00,"Respuesta exitosa","Respuesta OK"));
        movimientoResponse.setInfoRestList(infoRestList);
        return new ResponseEntity<MovimientoResponse>(movimientoResponse, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity crear(Movimiento movimiento) {
        MovimientoResponse movimientoResponse = new MovimientoResponse();
        List<Movimiento> movimientoList= new ArrayList<>();
        List<InfoRest> infoRestList = new ArrayList<>();

        try {
            Movimiento movimientoGuardado = movimientoRepository.save(movimiento);
            if (movimientoGuardado != null){
                movimientoList.add(movimientoGuardado);
                movimientoResponse.setDatos(movimientoList);
            }else{
                log.error("Error al crear Movimiento");
                infoRestList.add(new InfoRest(-1,"Movimiento no creado","Respuesta NOK"));
                movimientoResponse.setInfoRestList(infoRestList);
                return new ResponseEntity<MovimientoResponse>(movimientoResponse, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            log.error("Error al crear Movimiento", e.getMessage());
            infoRestList.add(new InfoRest(-1,"Movimiento no creado","Respuesta NOK"));
            movimientoResponse.setInfoRestList(infoRestList);
            return new ResponseEntity<MovimientoResponse>(movimientoResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        infoRestList.add(new InfoRest(00,"Movimiento creado","Respuesta OK"));
        movimientoResponse.setInfoRestList(infoRestList);

        return new ResponseEntity<MovimientoResponse>(movimientoResponse, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity editar(Movimiento movimiento, Long id) {
        MovimientoResponse movimientoResponse = new MovimientoResponse();
        List<Movimiento> movimientoList = new ArrayList<>();
        List<InfoRest> infoRestList = new ArrayList<>();

        try{
            Optional<Movimiento> movimientoBuscado = movimientoRepository.findById(id);
            if(movimientoBuscado.isPresent()){
                movimientoBuscado.get().setFecha(movimiento.getFecha());
                movimientoBuscado.get().setTipoMovimiento(movimiento.getTipoMovimiento());
                movimientoBuscado.get().setValor(movimiento.getValor());
                movimientoBuscado.get().setSaldo(movimiento.getSaldo());
                movimientoBuscado.get().setCuenta(movimiento.getCuenta());

                Movimiento movimientoActualizar = movimientoRepository.save(movimientoBuscado.get());
                if(movimientoActualizar != null){
                    movimientoList.add(movimientoActualizar);
                    movimientoResponse.setDatos(movimientoList);
                }else{
                    log.error("Error al editar Movimiento");
                    infoRestList.add(new InfoRest(-1,"Movimiento no editado","Respuesta NOK"));
                    movimientoResponse.setInfoRestList(infoRestList);
                    return new ResponseEntity<MovimientoResponse>(movimientoResponse, HttpStatus.BAD_REQUEST);
                }
            }else{
                log.error("Error al buscar Movimiento");
                infoRestList.add(new InfoRest(-1,"Movimiento no encontrado","Respuesta NOK"));
                movimientoResponse.setInfoRestList(infoRestList);
                return new ResponseEntity<MovimientoResponse>(movimientoResponse, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.error("Error al editar Movimiento", e.getMessage());
            infoRestList.add(new InfoRest(-1,"Movimiento no editado","Respuesta NOK"));
            movimientoResponse.setInfoRestList(infoRestList);
            return new ResponseEntity<MovimientoResponse>(movimientoResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        infoRestList.add(new InfoRest(00,"Movimiento editado","Respuesta OK"));
        movimientoResponse.setInfoRestList(infoRestList);

        return new ResponseEntity<MovimientoResponse>(movimientoResponse, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity eliminar(Long id) {

        MovimientoResponse movimientoResponse = new MovimientoResponse();
        List<InfoRest> infoRestList = new ArrayList<>();

        try {
            Optional<Movimiento> movimientoBuscado = movimientoRepository.findById(id);
            if(movimientoBuscado.isPresent()){
                movimientoRepository.deleteById(id);
            }else{
                log.error("Error al buscar Movimiento");
                infoRestList.add(new InfoRest(-1,"Movimiento no encontrado","Respuesta NOK"));
                movimientoResponse.setInfoRestList(infoRestList);
                return new ResponseEntity<MovimientoResponse>(movimientoResponse, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.error("Error al eliminar Movimiento", e.getMessage());
            infoRestList.add(new InfoRest(-1,"Movimiento no eliminado","Respuesta NOK"));
            movimientoResponse.setInfoRestList(infoRestList);
            return new ResponseEntity<MovimientoResponse>(movimientoResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        infoRestList.add(new InfoRest(00,"Movimiento eliminado","Respuesta OK"));
        movimientoResponse.setInfoRestList(infoRestList);

        return new ResponseEntity<MovimientoResponse>(movimientoResponse, HttpStatus.OK);
    }
}
