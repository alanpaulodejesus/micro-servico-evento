package com.evento.evento.service;

import com.evento.evento.dto.EventoRequestDTO;
import com.evento.evento.entities.Evento;
import org.springframework.stereotype.Service;

@Service
public interface EventoService {
     Evento criarEvento(EventoRequestDTO dto);

}
