package com.fin.banco.backend.service;

import com.fin.banco.backend.model.ClienteDTO;
import com.fin.banco.backend.model.PersonaDTO;
import com.fin.banco.backend.model.repository.ClienteRepository;
import com.fin.banco.backend.model.repository.PersonaRepository;
import com.fin.banco.backend.response.Cliente;
import com.fin.banco.backend.response.ResponseRest;
import com.fin.banco.backend.service.mapper.EntityMapper;
import com.fin.banco.backend.service.mapper.EntityMapperDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

public class ClienteServiceImplTest {

    @InjectMocks//mock que se usara cuando se llame dentro de @Mock
    ClienteServiceImpl service;

    @Mock
    ClienteRepository clienteRepository;

    @Mock
    PersonaRepository personaRepository;

    List<ClienteDTO> list = new ArrayList<>();


    @BeforeEach
    public void init(){
        MockitoAnnotations.openMocks(this);
        this.cargarClientes();
    }

    @Test
    public void buscarClientesTest(){
        when(clienteRepository.findAll()).thenReturn(list);
        ResponseEntity<ResponseRest<Cliente>> response = service.listar();
        Assertions.assertEquals(4, response.getBody().getDatos().size());
        verify(clienteRepository, times(1)).findAll(); //Verificar que se ha llamado al metodo findAll() una sola vez
    }

    @Test
    public void crearTest(){
        PersonaDTO personaDTO = new PersonaDTO( 1L,"Jose Lema","Masculino",28,"0101001","France","123456789");
        ClienteDTO clienteDTO = new ClienteDTO(1L,"1234", true);
        clienteDTO.setPersonaDTO(personaDTO);

        Cliente cliente = new Cliente(1L,"Jose Lema","Masculino",28,"0101001","France","123456789",1L,"1234",true);

        when(clienteRepository.save(ArgumentMatchers.any(ClienteDTO.class))).thenReturn(clienteDTO);
        when(personaRepository.findById(ArgumentMatchers.any(Long.class))).thenReturn(Optional.of(personaDTO));

        ResponseEntity<ResponseRest<Cliente>> response = service.crear(cliente);
        Assertions.assertEquals(response.getBody().getDatos().get(0).getId(),cliente.getId());
    }


    public void cargarClientes(){
        PersonaDTO personaDTO = new PersonaDTO( 1L,"Jose Lema","Masculino",28,"0101001","France","123456789");

        ClienteDTO cli1 = new ClienteDTO(Long.valueOf(1),"Alimentos", true);
        cli1.setPersonaDTO(personaDTO);

        ClienteDTO cli2 = new ClienteDTO(Long.valueOf(2),"Plantas", true);
        cli2.setPersonaDTO(personaDTO);

        ClienteDTO cli3 = new ClienteDTO(Long.valueOf(3),"Quesos", false);
        cli3.setPersonaDTO(personaDTO);

        ClienteDTO cli4 = new ClienteDTO(Long.valueOf(4),"Aceitunas", true);
        cli4.setPersonaDTO(personaDTO);

        list.add(cli1);
        list.add(cli2);
        list.add(cli3);
        list.add(cli4);
    }
}
