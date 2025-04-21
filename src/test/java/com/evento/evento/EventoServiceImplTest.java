package com.evento.evento;

import com.evento.evento.dto.EventoRequestDTO;
import com.evento.evento.dto.EventoResponseDTO;
import com.evento.evento.entities.Evento;
import com.evento.evento.exception.DataEventoInvalidaException;
import com.evento.evento.exception.KeyMessages;
import com.evento.evento.repositories.EventoRepository;
import com.evento.evento.service.impl.EventoServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EventoServiceImplTest {

    @InjectMocks
    private EventoServiceImpl eventoService;

    @Mock
    private EventoRepository eventoRepository;

    @Test
    void deveCriarEventoComDataValidaComSucesso() {
        EventoRequestDTO dto = new EventoRequestDTO(
                "Tech Conference",
                "Evento sobre tecnologia",
                LocalDate.now().plusDays(1)
        );

        Evento eventoSalvo = new Evento();
        when(eventoRepository.save(any())).thenReturn(eventoSalvo);

        EventoResponseDTO response = eventoService.criarEvento(dto);

        assertNotNull(response);
        verify(eventoRepository).save(any());
    }

    @Test
    void deveLancarExcecaoQuandoDataForNula() {
        EventoRequestDTO dto = new EventoRequestDTO(
                "Tech Conference",
                "Evento sobre tecnologia",
                null
        );
        var excecao = assertThrows( DataEventoInvalidaException.class, () -> {
            eventoService.criarEvento(dto);
        });
        assertEquals( KeyMessages.DATA_OBRIGATORIA, excecao.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoDataForPassada() {
        EventoRequestDTO dto = new EventoRequestDTO(
                "Tech Conference",
                "Evento sobre tecnologia",
                LocalDate.now().minusDays(1)
        );

        var excecao = assertThrows(DataEventoInvalidaException.class, () -> {
            eventoService.criarEvento(dto);
        });
        assertEquals(KeyMessages.DATA_INVALIDA_MENOR_ATUAL, excecao.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoNomeForNulo() {
        EventoRequestDTO dto = new EventoRequestDTO(
                null,
                "Evento sobre tecnologia",
                LocalDate.now().plusDays(1)
        );

        var excecao = assertThrows(DataEventoInvalidaException.class, () -> {
            eventoService.criarEvento(dto);
        });
        assertEquals(KeyMessages.NOME_EVENTO_OBRIGATORIO, excecao.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoNomeForVazio() {
        EventoRequestDTO dto = new EventoRequestDTO(
                "",
                "Evento sobre tecnologia",
                LocalDate.now().plusDays(1)
        );

        var excecao = assertThrows(DataEventoInvalidaException.class, () -> {
            eventoService.criarEvento(dto);
        });
        assertEquals(KeyMessages.NOME_EVENTO_OBRIGATORIO, excecao.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoDescricaoForNulo() {
        EventoRequestDTO dto = new EventoRequestDTO(
                "Tech Conference",
                null,
                LocalDate.now().plusDays(1)
        );

        var excecao = assertThrows(DataEventoInvalidaException.class, () -> {
            eventoService.criarEvento(dto);
        });
        assertEquals(KeyMessages.DESCRICAO_EVENTO_OBRIGATORIO, excecao.getMessage());
    }

    @Test
    void deveLancarExcecaoQuandoDescricaoForVazio() {
        EventoRequestDTO dto = new EventoRequestDTO(
                "Tech Conference",
                "",
                LocalDate.now().plusDays(1)
        );

        var excecao = assertThrows(DataEventoInvalidaException.class, () -> {
            eventoService.criarEvento(dto);
        });
        assertEquals(KeyMessages.DESCRICAO_EVENTO_OBRIGATORIO, excecao.getMessage());
    }
}