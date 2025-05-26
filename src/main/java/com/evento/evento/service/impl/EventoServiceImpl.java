package com.evento.evento.service.impl;

import com.evento.evento.dto.EventoRequestDTO;
import com.evento.evento.dto.EventoResponseDTO;
import com.evento.evento.entities.Evento;
import com.evento.evento.exception.DataEventoInvalidaException;
import com.evento.evento.exception.KeyMessages;
import com.evento.evento.mappers.EventoMapper;
import com.evento.evento.repositories.EventoRepository;
import com.evento.evento.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class EventoServiceImpl implements EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Override
    public EventoResponseDTO criarEvento(EventoRequestDTO dto) {
        if (dto.dataEvento() == null ) {
            throw new DataEventoInvalidaException(KeyMessages.DATA_OBRIGATORIA );
        }if (dto.dataEvento().isBefore( LocalDate.now())) {
            throw new DataEventoInvalidaException(KeyMessages.DATA_INVALIDA_MENOR_ATUAL );
        } if (dto.nomeEvento() == null || !StringUtils.hasText(dto.nomeEvento())) {
            throw new DataEventoInvalidaException(KeyMessages.NOME_EVENTO_OBRIGATORIO);
        } if (dto.descricaoEvento() == null || !StringUtils.hasText(dto.descricaoEvento())) {
            throw new DataEventoInvalidaException(KeyMessages.DESCRICAO_EVENTO_OBRIGATORIO);
        }
        Evento evento = EventoMapper.toEventoEntity(dto);
        Evento salvo = eventoRepository.save(evento);
        return EventoMapper.toEventoResponseDTO(salvo);
    }

    @Override
    public List <EventoResponseDTO> listarEventos() {
        return eventoRepository.findAll()
                .stream()
                .map(EventoMapper::toEventoResponseDTO)
                .collect( Collectors.toList());
    }
    @Override
    public void deletarEvento(Integer id) {
        if (!eventoRepository.existsById(id)) {
            throw new DataEventoInvalidaException("Evento com ID " + id + " não encontrado.");
        }
        eventoRepository.deleteById(id);
    }

}
