package com.evento.evento.service;

import com.evento.evento.dto.EventoRequestDTO;
import com.evento.evento.dto.EventoResponseDTO;

import java.util.List;


public interface EventoService {
     EventoResponseDTO criarEvento(EventoRequestDTO dto);
     List <EventoResponseDTO> listarEventos();
     void deletarEvento(Integer id);
     void deletarEventos();
}
