package com.fin.banco.backend.controller;


import com.fin.banco.backend.model.Movimiento;
import com.fin.banco.backend.response.MovimientoResponse;
import com.fin.banco.backend.service.MovimientoService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class MovimientoControllerTest {

    @InjectMocks
    private MovimientoController movimientoController;

    @Mock
    private MovimientoService service;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void crearTest(){
        //Genera contexto para solicitud de http
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Movimiento movimiento = new Movimiento(Long.valueOf(1), LocalDateTime.now(),"Retiro",-100.00,200.00);

        //Cuando encuentra el metodo crear, retorne la entidad de respuesta con estado OK
        when(service.crear(any(Movimiento.class))).thenReturn(new ResponseEntity<MovimientoResponse>(HttpStatus.OK));
        ResponseEntity<MovimientoResponse> response = movimientoController.crear(movimiento);

        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }


    @Test
    public void editarTest(){
        //Genera contexto para solicitud de http
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Movimiento movimientoModificado = new Movimiento(Long.valueOf(1), LocalDateTime.now(),"Retiro",-100.00,200.00);

        //Cuando encuentra el metodo crear, retorne la entidad de respuesta con estado OK
        when(service.editar(any(Movimiento.class),any(Long.class))).thenReturn(new ResponseEntity<MovimientoResponse>(HttpStatus.OK));
        ResponseEntity<MovimientoResponse> response = movimientoController.editar(movimientoModificado,1L);

        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void eliminarTest(){
        //Genera contexto para solicitud de http
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Movimiento movimiento = new Movimiento(Long.valueOf(1), LocalDateTime.now(),"Retiro",-100.00,200.00);

        //Cuando encuentra el metodo crear, retorne la entidad de respuesta con estado OK
        when(service.eliminar(any(Long.class))).thenReturn(new ResponseEntity<MovimientoResponse>(HttpStatus.OK));
        ResponseEntity<MovimientoResponse> response = movimientoController.eliminar(movimiento.getId());

        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }
}
