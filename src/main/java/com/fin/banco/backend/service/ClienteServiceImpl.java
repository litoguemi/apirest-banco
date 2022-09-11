package com.fin.banco.backend.service;

import com.fin.banco.backend.model.Cliente;
import com.fin.banco.backend.model.repository.ClienteRepository;
import com.fin.banco.backend.response.ClienteResponse;
import com.fin.banco.backend.response.InfoRest;
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

    @Override
    @Transactional(readOnly = true)
    public ResponseEntity listar() {
        ClienteResponse clienteResponse = new ClienteResponse();
        List<InfoRest> infoRestList = new ArrayList<>();

        try {
            List<Cliente> clienteList = clienteRepository.findAll();
            clienteResponse.setDatos(clienteList);
        }catch (Exception e){
            log.error("Error al consutar Clientes", e.getMessage());
            infoRestList.add(new InfoRest(-1,"Error al consultar Clientes","Respuesta NOK"));
            clienteResponse.setInfoRestList(infoRestList);
            return new ResponseEntity<ClienteResponse>(clienteResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        infoRestList.add(new InfoRest(00,"Respuesta exitosa","Respuesta OK"));
        clienteResponse.setInfoRestList(infoRestList);
        return new ResponseEntity<ClienteResponse>(clienteResponse, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<ClienteResponse> crear(Cliente cliente) {
        ClienteResponse clienteResponse = new ClienteResponse();
        List<Cliente> clienteList = new ArrayList<>();
        List<InfoRest> infoRestList = new ArrayList<>();

        try {
            Cliente clienteGuardado = clienteRepository.save(cliente);
            if (clienteGuardado != null){
                clienteList.add(clienteGuardado);
                clienteResponse.setDatos(clienteList);
            }else{
                log.error("Error al crear Cliente");
                infoRestList.add(new InfoRest(-1,"Cliente no creado","Respuesta NOK"));
                clienteResponse.setInfoRestList(infoRestList);
                return new ResponseEntity<ClienteResponse>(clienteResponse, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            log.error("Error al crear Cliente", e.getMessage());
            infoRestList.add(new InfoRest(-1,"Cliente no creado","Respuesta NOK"));
            clienteResponse.setInfoRestList(infoRestList);
            return new ResponseEntity<ClienteResponse>(clienteResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        infoRestList.add(new InfoRest(00,"Cliente creado","Respuesta OK"));
        clienteResponse.setInfoRestList(infoRestList);

        return new ResponseEntity<ClienteResponse>(clienteResponse, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<ClienteResponse> editar(Cliente cliente, Long id) {
        ClienteResponse clienteResponse = new ClienteResponse();
        List<Cliente> clienteList = new ArrayList<>();
        List<InfoRest> infoRestList = new ArrayList<>();

        try{
            Optional<Cliente> clienteBuscado = clienteRepository.findById(id);
            if(clienteBuscado.isPresent()){
                clienteBuscado.get().setNombre(cliente.getNombre());
                clienteBuscado.get().setGenero(cliente.getGenero());
                clienteBuscado.get().setEdad(cliente.getEdad());
                clienteBuscado.get().setIdentificacion(cliente.getIdentificacion());
                clienteBuscado.get().setDireccion(cliente.getDireccion());
                clienteBuscado.get().setTelefono(cliente.getTelefono());
                clienteBuscado.get().setContrasena(cliente.getContrasena());
                clienteBuscado.get().setEstado(cliente.getEstado());

                Cliente clienteActualizar = clienteRepository.save(clienteBuscado.get());
                if(clienteActualizar != null){
                    clienteList.add(clienteActualizar);
                    clienteResponse.setDatos(clienteList);
                }else{
                    log.error("Error al editar Cliente");
                    infoRestList.add(new InfoRest(-1,"Cliente no editado","Respuesta NOK"));
                    clienteResponse.setInfoRestList(infoRestList);
                    return new ResponseEntity<ClienteResponse>(clienteResponse, HttpStatus.BAD_REQUEST);
                }
            }else{
                log.error("Error al buscar Cliente");
                infoRestList.add(new InfoRest(-1,"Cliente no encontrado","Respuesta NOK"));
                clienteResponse.setInfoRestList(infoRestList);
                return new ResponseEntity<ClienteResponse>(clienteResponse, HttpStatus.NOT_FOUND);
            }
        }catch (Exception e){
            log.error("Error al editar Cliente", e.getMessage());
            infoRestList.add(new InfoRest(-1,"Cliente no editado","Respuesta NOK"));
            clienteResponse.setInfoRestList(infoRestList);
            return new ResponseEntity<ClienteResponse>(clienteResponse, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        infoRestList.add(new InfoRest(00,"Cliente editado","Respuesta OK"));
        clienteResponse.setInfoRestList(infoRestList);

        return new ResponseEntity<ClienteResponse>(clienteResponse, HttpStatus.OK);
    }

    @Override
    @Transactional
    public ResponseEntity<ClienteResponse> eliminar(Long id) {
       ClienteResponse clienteResponse = new ClienteResponse();
       List<InfoRest> infoRestList = new ArrayList<>();

       try {
           Optional<Cliente> clienteBuscado = clienteRepository.findById(id);
           if(clienteBuscado.isPresent()){
               clienteRepository.deleteById(id);
           }else{
               log.error("Error al buscar Cliente");
               infoRestList.add(new InfoRest(-1,"Cliente no encontrado","Respuesta NOK"));
               clienteResponse.setInfoRestList(infoRestList);
               return new ResponseEntity<ClienteResponse>(clienteResponse, HttpStatus.NOT_FOUND);
           }
       }catch (Exception e){
           log.error("Error al eliminar Cliente", e.getMessage());
           infoRestList.add(new InfoRest(-1,"Cliente no eliminado","Respuesta NOK"));
           clienteResponse.setInfoRestList(infoRestList);
           return new ResponseEntity<ClienteResponse>(clienteResponse, HttpStatus.INTERNAL_SERVER_ERROR);
       }

        infoRestList.add(new InfoRest(00,"Cliente eliminado","Respuesta OK"));
        clienteResponse.setInfoRestList(infoRestList);

        return new ResponseEntity<ClienteResponse>(clienteResponse, HttpStatus.OK);
    }
}
