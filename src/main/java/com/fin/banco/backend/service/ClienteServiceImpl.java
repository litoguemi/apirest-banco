package com.fin.banco.backend.service;


import com.fin.banco.backend.model.ClienteDTO;
import com.fin.banco.backend.model.PersonaDTO;
import com.fin.banco.backend.model.repository.ClienteRepository;
import com.fin.banco.backend.model.repository.PersonaRepository;
import com.fin.banco.backend.response.Cliente;
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
public class ClienteServiceImpl implements ClienteService{

    private static final Logger log = LoggerFactory.getLogger(ClienteServiceImpl.class);

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    PersonaRepository personaRepository;

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity listar() {
        ResponseRest<Cliente> clienteResponse = new ResponseRest();
        List<InfoRest> infoRestList = new ArrayList<>();
        List<Cliente> clienteList = new ArrayList<>();
        try {
            List<ClienteDTO> clienteDTOList = clienteRepository.findAll();
            clienteDTOList.stream().forEach(clienteDTO -> {
                clienteList.add(EntityMapper.castClienteDTOtoCliente(clienteDTO));
            });
            clienteResponse.setDatos(clienteList);
        }catch (Exception e){
            log.error("Error al consutar Clientes", e.getMessage());
            infoRestList.add(new InfoRest(-1,"Error al consultar Clientes","Respuesta NOK"));
            clienteResponse.setInfoRestList(infoRestList);
            return new ResponseEntity<ResponseRest>(clienteResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        infoRestList.add(new InfoRest(00,"Respuesta exitosa","Respuesta OK"));
        clienteResponse.setInfoRestList(infoRestList);
        return new ResponseEntity<ResponseRest>(clienteResponse, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity crear(Cliente cliente) {
        ResponseRest<Cliente> clienteResponse = new ResponseRest<>();
        List<Cliente> clienteList = new ArrayList<>();
        List<InfoRest> infoRestList = new ArrayList<>();

        try {
            Optional<PersonaDTO> personaGuardado = personaRepository.findById(cliente.getId());
            if(!personaGuardado.isPresent()){
                personaGuardado = Optional.of(personaRepository.save(EntityMapperDTO.castClienteToPersonaDTO(cliente)));
            }
            ClienteDTO clienteGuardado = clienteRepository.save(EntityMapperDTO.castClienteToClienteDTO(personaGuardado.get(),cliente));
            if (clienteGuardado != null){
                clienteList.add(EntityMapper.castClienteDTOtoCliente(clienteGuardado));
                clienteResponse.setDatos(clienteList);
            }else{
                log.error("Error al crear Cliente");
                infoRestList.add(new InfoRest(-1,"Cliente no creado","Respuesta NOK"));
                clienteResponse.setInfoRestList(infoRestList);
                return new ResponseEntity<ResponseRest>(clienteResponse, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            log.error("Error al crear Cliente", e.getMessage());
            infoRestList.add(new InfoRest(-1,"Cliente no creado","Respuesta NOK"));
            clienteResponse.setInfoRestList(infoRestList);
            return new ResponseEntity<ResponseRest>(clienteResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        infoRestList.add(new InfoRest(00,"Cliente creado","Respuesta OK"));
        clienteResponse.setInfoRestList(infoRestList);

        return new ResponseEntity<ResponseRest>(clienteResponse, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity editar(Cliente cliente, Long id) {
        ResponseRest<Cliente> clienteResponse = new ResponseRest();
        List<Cliente> clienteList = new ArrayList<>();
        List<InfoRest> infoRestList = new ArrayList<>();

        try{
            Optional<ClienteDTO> clienteBuscado = clienteRepository.findById(id);
            if(clienteBuscado.isPresent()){
                ClienteDTO clienteActualizar = EntityMapperDTO.castClienteToClienteDTO(clienteBuscado.get(),cliente);
                clienteActualizar = clienteRepository.save(clienteActualizar);
                if(clienteActualizar != null){
                    clienteList.add(EntityMapper.castClienteDTOtoCliente(clienteActualizar));
                    clienteResponse.setDatos(clienteList);
                }else{
                    log.error("Error al editar Cliente");
                    infoRestList.add(new InfoRest(-1,"Cliente no editado","Respuesta NOK"));
                    clienteResponse.setInfoRestList(infoRestList);
                    return new ResponseEntity<ResponseRest>(clienteResponse, HttpStatus.BAD_REQUEST);
                }
            }else{
                log.error("Error al buscar Cliente");
                infoRestList.add(new InfoRest(-1,"Cliente no encontrado","Respuesta NOK"));
                clienteResponse.setInfoRestList(infoRestList);
                return new ResponseEntity<ResponseRest>(clienteResponse, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.error("Error al editar Cliente", e.getMessage());
            infoRestList.add(new InfoRest(-1,"Cliente no editado","Respuesta NOK"));
            clienteResponse.setInfoRestList(infoRestList);
            return new ResponseEntity<ResponseRest>(clienteResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        infoRestList.add(new InfoRest(00,"Cliente editado","Respuesta OK"));
        clienteResponse.setInfoRestList(infoRestList);

        return new ResponseEntity<ResponseRest>(clienteResponse, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity eliminar(Long id) {
        ResponseRest<Cliente> clienteResponse = new ResponseRest<>();
       List<InfoRest> infoRestList = new ArrayList<>();

       try {
           Optional<ClienteDTO> clienteBuscado = clienteRepository.findById(id);
           if(clienteBuscado.isPresent()){
               clienteRepository.deleteById(id);
           }else{
               log.error("Error al buscar Cliente");
               infoRestList.add(new InfoRest(-1,"Cliente no encontrado","Respuesta NOK"));
               clienteResponse.setInfoRestList(infoRestList);
               return new ResponseEntity<ResponseRest>(clienteResponse, HttpStatus.NOT_FOUND);
           }
       }catch (Exception e){
           log.error("Error al eliminar Cliente", e.getMessage());
           infoRestList.add(new InfoRest(-1,"Cliente no eliminado","Respuesta NOK"));
           clienteResponse.setInfoRestList(infoRestList);
           return new ResponseEntity<ResponseRest>(clienteResponse, HttpStatus.INTERNAL_SERVER_ERROR);
       }

        infoRestList.add(new InfoRest(00,"Cliente eliminado","Respuesta OK"));
        clienteResponse.setInfoRestList(infoRestList);

        return new ResponseEntity<ResponseRest>(clienteResponse, HttpStatus.OK);
    }
}
