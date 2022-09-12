package com.fin.banco.backend.controller;

import com.fin.banco.backend.model.ClienteDTO;
import com.fin.banco.backend.response.Cliente;
import com.fin.banco.backend.service.ClienteService;
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

public class ClienteControllerTest {


    @InjectMocks
    private ClienteController clienteController;

    @Mock
    private ClienteService service;

    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void crearTest(){
        //Genera contexto para solicitud de http
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Cliente cliente = new Cliente(Long.valueOf(1),"Marianela Montalvo","Femenino",52,"0202020202","Amazonas y  NNUU","097548965",1L,"1234",true);

        //Cuando encuentra el metodo crear, retorne la entidad de respuesta con estado OK
        when(service.crear(any(Cliente.class))).thenReturn(new ResponseEntity<Cliente>(HttpStatus.OK));
        ResponseEntity<Cliente> response = clienteController.crear(cliente);

        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void editarTest(){
        //Genera contexto para solicitud de http
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Cliente cliente = new Cliente(Long.valueOf(1),"Marianela Montalvo","Femenino",52,"0202020202","Amazonas y  NNUU","097548965",1L,"1234",false);

        //Cuando encuentra el metodo crear, retorne la entidad de respuesta con estado OK
        when(service.editar(any(Cliente.class),any(Long.class))).thenReturn(new ResponseEntity<Cliente>(HttpStatus.OK));
        ResponseEntity<Cliente> response = clienteController.editar(cliente,1L);

        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

    @Test
    public void eliminarTest(){
        //Genera contexto para solicitud de http
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

        Cliente cliente = new Cliente(Long.valueOf(1),"Marianela Montalvo","Femenino",52,"0202020202","Amazonas y  NNUU","097548965",1L,"1234",true);

        //Cuando encuentra el metodo crear, retorne la entidad de respuesta con estado OK
        when(service.eliminar(any(Long.class))).thenReturn(new ResponseEntity<Cliente>(HttpStatus.OK));
        ResponseEntity<Cliente> response = clienteController.eliminar(cliente.getId());

        Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
    }

}
