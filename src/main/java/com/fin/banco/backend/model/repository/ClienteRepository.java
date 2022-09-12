package com.fin.banco.backend.model.repository;

import com.fin.banco.backend.model.ClienteDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<ClienteDTO, Long> {

}
