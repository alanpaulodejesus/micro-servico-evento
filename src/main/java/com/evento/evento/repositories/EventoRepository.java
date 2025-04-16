package com.evento.evento.repositories;

import com.evento.evento.entities.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento, Integer> {
    List<Evento> findAllByNomeEvento(String nomeEvento);
}

