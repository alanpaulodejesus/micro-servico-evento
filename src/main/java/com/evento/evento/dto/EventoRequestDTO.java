package com.evento.evento.dto;

import com.evento.evento.exception.KeyMessages;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record EventoRequestDTO(@NotNull(message = KeyMessages.NOME_EVENTO_OBRIGATORIO)
                               String nomeEvento,
                               @NotNull(message = KeyMessages.DESCRICAO_EVENTO_OBRIGATORIO)
                               String descricaoEvento,
                               @JsonFormat(pattern = "yyyy-MM-dd")
                               @Schema(
                                       description = "Data do evento (apenas data)",
                                       type = "string",
                                       format = "date",
                                       example = "2025-04-17"
                               )
                               @NotNull(message = KeyMessages.DATA_OBRIGATORIA)
                               LocalDate dataEvento) {
}
