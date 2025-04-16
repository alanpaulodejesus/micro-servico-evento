//package com.evento.evento.controller;
//
//import com.evento.evento.model.Evento;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.time.LocalDate;
//import java.util.Arrays;
//import java.util.List;
//
//@RestController
//public class EventoController {
//
//    private List<Evento> eventos = Arrays.asList(
//            new Evento(1, "Show da virada","Evento de final de ano", LocalDate.parse("2025-12-31"))
//    );
//
//    @GetMapping("/eventos")
//    public List<Evento>listarEventos(){
//        return eventos;
//    }
//
//    @GetMapping("/evento/{id}")
//    public Evento getEvento(@PathVariable int id){
//        return eventos.stream().filter( evento -> evento.getId()==id )
//                .findFirst()
//                .orElse( null );
//    }
//}
