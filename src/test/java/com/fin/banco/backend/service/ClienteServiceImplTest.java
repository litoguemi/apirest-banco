package com.fin.banco.backend.service;

import com.fin.banco.backend.model.Cliente;
import com.fin.banco.backend.model.repository.ClienteRepository;
import com.fin.banco.backend.response.ClienteResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class ClienteServiceImplTest {

    @InjectMocks
    ClienteServiceImpl service;

    @Mock
    ClienteRepository clienteRepository;

    List<Cliente> list = new ArrayList<>();


    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        this.cargarClientes();
    }


    @Test
    public void listarTest(){
        when(clienteRepository.findAll()).thenReturn(list);
        ResponseEntity<ClienteResponse> response = service.listar();

        Assertions.assertEquals(3, response.getBody().getDatos().size());
        verify(clienteRepository, times(1)).findAll(); //Verificar que se ha llamado al metodo findAll() una sola vez
    }



    public void cargarClientes(){
        Cliente cli1 = new Cliente(Long.valueOf(1),"1234", true);
        Cliente cli2 = new Cliente(Long.valueOf(2),"4567", false);
        Cliente cli3 = new Cliente(Long.valueOf(3),"8901", true);

        list.add(cli1);
        list.add(cli2);
        list.add(cli3);
    }

}
