package com.fin.banco.backend.service;

import com.fin.banco.backend.model.MovimientoDTO;
import com.fin.banco.backend.model.ReporteDTO;
import com.fin.banco.backend.model.repository.ReporteRepository;
import com.fin.banco.backend.request.ReporteRequest;
import com.fin.banco.backend.response.InfoRest;
import com.fin.banco.backend.response.Movimiento;
import com.fin.banco.backend.response.Reporte;
import com.fin.banco.backend.response.ResponseRest;
import com.fin.banco.backend.service.mapper.EntityMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class ReporteServiceImpl implements ReporteService{

    private static final Logger log = LoggerFactory.getLogger(ReporteServiceImpl.class);

    @Autowired
    ReporteRepository reporteRepository;


    @Override
    @Transactional(readOnly = true)
    public ResponseEntity generaReporteMovimiento(ReporteRequest reporteRequest) {


        ResponseRest<Reporte> reporteResponse = new ResponseRest();
        List<InfoRest> infoRestList = new ArrayList<>();
        List<Reporte> reporteList = new ArrayList<>();
        try {

            List<Object[]> reporteDTOList= reporteRepository.consultarMovimientos(reporteRequest.getFechaInicial(),reporteRequest.getFechaFinal(), reporteRequest.getClienteId());
            reporteDTOList.stream().forEach(reporteDTO -> {
                reporteList.add(EntityMapper.castReporteDTOtoReporte(new ReporteDTO(reporteDTO)));
            });
            reporteResponse.setDatos(reporteList);
        }catch (Exception e){
            log.error("Error al consutar Estado de Cuenta", e.getMessage());
            e.printStackTrace();
            infoRestList.add(new InfoRest(-1,"Error al consultar Estado de Cuenta","Respuesta NOK"));
            reporteResponse.setInfoRestList(infoRestList);
            return new ResponseEntity<ResponseRest>(reporteResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        infoRestList.add(new InfoRest(00,"Reporte Generado","Respuesta OK"));
        reporteResponse.setInfoRestList(infoRestList);
        return new ResponseEntity<ResponseRest>(reporteResponse, HttpStatus.OK);
    }
}
