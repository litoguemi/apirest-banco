package com.fin.banco.backend.controller;

import com.fin.banco.backend.model.CuentaDTO;
import com.fin.banco.backend.response.Cuenta;
import com.fin.banco.backend.service.CuentaService;
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

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class CuentaControllerTest {

    @InjectMocks
    private CuentaController cuentaController;

    @Mock
    private CuentaService service;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void crearTest(){
        //Genera contexto para solicitud de http
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Cuenta cuenta = new Cuenta(Long.valueOf(1),"478758","ahorro",200.00,true,1L);

        //Cuando encuentra el metodo crear, retorne la entidad de respuesta con estado OK
        when(service.crear(any(Cuenta.class))).thenReturn(new ResponseEntity<Cuenta>(HttpStatus.OK));
        ResponseEntity<Cuenta> response = cuentaController.crear(cuenta);

        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }


    @Test
    public void editarTest(){
        //Genera contexto para solicitud de http
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Cuenta cuenta = new Cuenta(Long.valueOf(1),"478758","ahorro",200.00,true,1L);

        //Cuando encuentra el metodo crear, retorne la entidad de respuesta con estado OK
        when(service.editar(any(Cuenta.class),any(Long.class))).thenReturn(new ResponseEntity<Cuenta>(HttpStatus.OK));
        ResponseEntity<Cuenta> response = cuentaController.editar(cuenta,1L);

        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void eliminarTest(){
        //Genera contexto para solicitud de http
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Cuenta cuenta = new Cuenta(Long.valueOf(1),"478758","ahorro",200.00,true,1L);

        //Cuando encuentra el metodo crear, retorne la entidad de respuesta con estado OK
        when(service.eliminar(any(Long.class))).thenReturn(new ResponseEntity<Cuenta>(HttpStatus.OK));
        ResponseEntity<Cuenta> response = cuentaController.eliminar(cuenta.getId());

        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

}
