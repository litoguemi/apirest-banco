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

@Service
public class ClienteServiceImpl implements ClienteService{

    private static final Logger log = LoggerFactory.getLogger(ClienteServiceImpl.class);

    @Autowired
    private ClienteRepository clienteRepository;

    @Override
    @Transactional
    public ResponseEntity<ClienteResponse> crear(Cliente cliente) {
        ClienteResponse clienteResponse = new ClienteResponse();
        List<Cliente> datos = new ArrayList<>();
        List<InfoRest> infoRestList = new ArrayList<>();

        try {
            Cliente clienteGuardado = clienteRepository.save(cliente);
            if (clienteGuardado != null){
                datos.add(clienteGuardado);
                clienteResponse.setDatos(datos);
            }else{
                log.error("Error al crear Cliente");
                infoRestList.add(new InfoRest(-1,"Cliente no creado","Respuesta NOK"));
                clienteResponse.setInfoRestList(infoRestList);
                return new ResponseEntity<ClienteResponse>(clienteResponse, HttpStatus.BAD_REQUEST);
            }
        }catch (Exception e){
            log.error("Error al crear categoria", e.getMessage());
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
        return null;
    }

    @Override
    @Transactional
    public ResponseEntity<ClienteResponse> eliminar(Long id) {
        return null;
    }
}
