package com.evento.evento.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;
import java.time.OffsetDateTime;

public record EventoRequestDTO(String nomeEvento,
                               String descricaoEvento,
                               //@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX", timezone = "UTC")
                               //@JsonFormat(pattern = "yyyy-MM-dd'T'HH:mmXXX", timezone = "UTC")
                               @JsonFormat(pattern = "yyyy-MM-dd")
                               @Schema(
                                       description = "Data do evento (apenas data)",
                                       type = "string",
                                       format = "date",
                                       example = "2025-04-17"
                               )
                               LocalDate dataEvento) {
}
