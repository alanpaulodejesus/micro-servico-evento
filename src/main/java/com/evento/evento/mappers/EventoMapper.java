package com.evento.evento.mappers;

import com.evento.evento.dto.EventoRequestDTO;
import com.evento.evento.dto.EventoResponseDTO;
import com.evento.evento.entities.Evento;

public class EventoMapper {

    public static Evento toEventoEntity(EventoRequestDTO dto) {
        Evento evento = new Evento();
        evento.setId(dto.id());
        evento.setNomeEvento(dto.nomeEvento());
        evento.setDescricaoEvento(dto.descricaoEvento());
        evento.setDataEvento(dto.dataEvento());
        return evento;
    }

    public static EventoResponseDTO toEventoResponseDTO(Evento evento) {
        return new EventoResponseDTO(
                evento.getId(),
                evento.getNomeEvento(),
                evento.getDescricaoEvento(),
                evento.getDataEvento()
        );
    }
}
