package com.evento.evento.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

public record EventoResponseDTO(Integer id,
                                String nomeEvento,
                                String descricaoEvento,  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
                                LocalDateTime dataEvento) {
}
