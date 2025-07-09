package com.evento.evento.controller;

import com.evento.evento.dto.EventoRequestDTO;
import com.evento.evento.dto.EventoResponseDTO;
import com.evento.evento.entities.Evento;
import com.evento.evento.mappers.EventoMapper;
import com.evento.evento.service.EventoService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "https://micro-servico-evento.onrender.com")
@RestController
@RequestMapping("/evento")
public class EventoController {

    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @PostMapping
    public ResponseEntity<EventoResponseDTO> criarEvento(@RequestBody @Valid EventoRequestDTO eventoRequestDTO) {
        EventoResponseDTO eventoResponseDTO = eventoService.criarEvento(eventoRequestDTO);
        return ResponseEntity.ok(eventoResponseDTO);
    }

    @GetMapping
    public ResponseEntity<List<EventoResponseDTO>> listarEventos() {
        List<EventoResponseDTO> eventos = eventoService.listarEventos();
        return ResponseEntity.ok(eventos);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarEvento(@PathVariable Integer id) {
        eventoService.deletarEvento(id);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping()
    public ResponseEntity<Void> deletarEventos() {
        eventoService.deletarEventos();
        return ResponseEntity.ok().build();
    }
}
