package com.evento.evento.controller;

import com.evento.evento.dto.EventoRequestDTO;
import com.evento.evento.dto.EventoResponseDTO;
import com.evento.evento.entities.Evento;
import com.evento.evento.mappers.EventoMapper;
import com.evento.evento.service.EventoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/evento")
public class EventoController {

    private final EventoService eventoService;

    public EventoController(EventoService eventoService) {
        this.eventoService = eventoService;
    }

    @PostMapping
    public ResponseEntity<EventoResponseDTO> criarEvento(@RequestBody EventoRequestDTO eventoRequestDTO) {

        Evento eventoEntities = eventoService.criarEvento(eventoRequestDTO);
        EventoResponseDTO eventoResponseDTO = EventoMapper.toEventoResponseDTO(eventoEntities);
        return ResponseEntity.ok(eventoResponseDTO);
    }

//    @GetMapping("/{placa}")
//    public ResponseEntity<List<ParquimetroResponseDTO>> getByPlaca(@PathVariable String placa){
//        final List<RegistroParquimetro> registroParquimetros = parquimetroServiceImpl.getByPlaca(placa);
//        final List<ParquimetroResponseDTO> responseDTOS = registroParquimetros.stream().map(ParquimetroMapper::saidaDoEstacionamento).collect(Collectors.toList());
//        return ResponseEntity.ok(responseDTOS);
//    }
//
//    @PostMapping("/{placa}/sair")
//    public ResponseEntity<ParquimetroResponseDTO> sair(@PathVariable String placa, ParquimetroRequestDTO parquimetroRequestDTO) {
//        final RegistroParquimetro registroParquimetro = parquimetroServiceImpl.sair(parquimetroRequestDTO);
//        return ResponseEntity.ok(ParquimetroMapper.saidaDoEstacionamento(registroParquimetro));
//    }

}
