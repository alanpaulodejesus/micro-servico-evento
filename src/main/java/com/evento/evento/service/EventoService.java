package com.evento.evento.service;

import com.evento.evento.dto.EventoRequestDTO;
import com.evento.evento.dto.EventoResponseDTO;


public interface EventoService {
     EventoResponseDTO criarEvento(EventoRequestDTO dto);
}
