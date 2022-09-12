package com.fin.banco.backend.model.repository;

import com.fin.banco.backend.model.ClienteDTO;
import com.fin.banco.backend.model.PersonaDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonaRepository extends JpaRepository<PersonaDTO, Long> {

}
