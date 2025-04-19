package com.evento.evento.exception;

public enum KeyMessages {
    DATA_INVALIDA_PASSED("A data do evento não pode ser menor que a data atual.");

    String value;

    KeyMessages(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
