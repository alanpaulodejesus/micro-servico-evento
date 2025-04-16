package com.evento.evento.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.OffsetDateTime;

public record EventoRequestDTO(String nomeEvento,
                               String descricaoEvento, @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX", timezone = "UTC")
                               OffsetDateTime dataEvento) {
}
