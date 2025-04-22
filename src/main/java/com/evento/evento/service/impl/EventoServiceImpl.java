package com.evento.evento.service.impl;

import com.evento.evento.dto.EventoRequestDTO;
import com.evento.evento.dto.EventoResponseDTO;
import com.evento.evento.entities.Evento;
import com.evento.evento.exception.DataEventoInvalidaException;
import com.evento.evento.exception.KeyMessages;
import com.evento.evento.mappers.EventoMapper;
import com.evento.evento.repositories.EventoRepository;
import com.evento.evento.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class EventoServiceImpl implements EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    @Override
    public EventoResponseDTO criarEvento(EventoRequestDTO dto) {
        if (dto.dataEvento() == null ) {
            throw new DataEventoInvalidaException(KeyMessages.DATA_OBRIGATORIA );
        }if (dto.dataEvento().isBefore( LocalDate.now())) {
            throw new DataEventoInvalidaException(KeyMessages.DATA_INVALIDA_MENOR_ATUAL );
        } if (dto.nomeEvento() == null || !StringUtils.hasText(dto.nomeEvento())) {
            throw new DataEventoInvalidaException(KeyMessages.NOME_EVENTO_OBRIGATORIO);
        } if (dto.descricaoEvento() == null || !StringUtils.hasText(dto.descricaoEvento())) {
            throw new DataEventoInvalidaException(KeyMessages.DESCRICAO_EVENTO_OBRIGATORIO);
        }
        Evento evento = EventoMapper.toEventoEntity(dto);
        Evento salvo = eventoRepository.save(evento);
        return EventoMapper.toEventoResponseDTO(salvo);
    }

    @Override
    public List <EventoResponseDTO> listarEventos() {
        return eventoRepository.findAll()
                .stream()
                .map(EventoMapper::toEventoResponseDTO)
                .collect( Collectors.toList());
    }

//
//    @Transactional
//    @CacheEvict(value = "registroParquimetroPorPlaca", key = "#dto.placa", allEntries = false)
//    public RegistroParquimetro sair(ParquimetroRequestDTO dto) {
//        Veiculo veiculo = findInDB(dto);
//        verificaSaida(veiculo, parquimetroRepository);
//        RegistroParquimetro registroParquimetro = veiculo.getRegistroParquimetroList().get(veiculo.getRegistroParquimetroList().size() -1);
//        registroParquimetro.setDataSaida(LocalDateTime.now());
//        registroParquimetro.setStatus(StatusPaquimetro.ENTROU_E_SAIU);
//        parquimetroRepository.save(registroParquimetro);
//        calculaValorDevido(veiculo.getTipoVeiculo(), registroParquimetro);
//        return registroParquimetro;
//    }
//
//    private void calculaValorDevido(TipoVeiculo tipoVeiculo, RegistroParquimetro registroParquimetro) {
//        Duration duration = Duration.between(registroParquimetro.getDataEntrada(), registroParquimetro.getDataSaida());
//        TipoVeiculoConfig config = TipoVeiculoConfig.retornarConfigVeiculo(tipoVeiculo);
//        registroParquimetro.setValor(((1 + duration.toHours()) * config.getValorHora()) + (config.getValorMinino()));
//    }
//
//    @Cacheable(value = "veiculo", key ="#dto.placa")
//    public Veiculo findOrCreateInDB(ParquimetroRequestDTO dto) {
//        final Veiculo veiculo = veiculoRepository.findByPlaca(dto.placa()).orElse(new Veiculo(dto.placa(), dto.tipoVeiculo(), new ArrayList<>()));
//        return veiculo;
//    }
//
//    @Cacheable(value = "veiculo", key ="#dto.placa")
//    public Veiculo findInDB(ParquimetroRequestDTO dto) {
//        final Veiculo veiculo = veiculoRepository.findByPlaca(dto.placa()).orElseThrow(()
//                -> new NotFoundException(KeyMessages.PLACA_NOT_FOUND.getValue()));
//        return veiculo;
//    }
//
//    private void verificaEntrada(Veiculo veiculo, ParquimetroRepository parquimetroRepository) {
//        Optional<List<RegistroParquimetro>> registros = parquimetroRepository.findAllByVeiculoPlaca(veiculo.getPlaca());
//        if(consultaValidadeRegistro(registros , StatusPaquimetro.ENTROU_E_NAO_SAIU)){
//            throw new NotFoundException(KeyMessages.VEICULO_JA_ESTA_ESTACIONADO.getValue());
//        }
//    }
//
//    private void verificaSaida(Veiculo veiculo, ParquimetroRepository parquimetroRepository) {
//        Optional<List<RegistroParquimetro>> registros = parquimetroRepository.findAllByVeiculoPlaca(veiculo.getPlaca());
//        if(consultaValidadeRegistro(registros , StatusPaquimetro.ENTROU_E_SAIU)){
//            throw new NotFoundException(KeyMessages.VEICULO_JA_SAIU.getValue());
//        }
//    }
//
//    private Boolean consultaValidadeRegistro(Optional<List<RegistroParquimetro>> registros, StatusPaquimetro statusPaquimetro){
//        if(registros.isPresent()){
//            if(registros.get().size()>1) {
//                if(registros.get().get(registros.get().size() - 1).getStatus() == statusPaquimetro){
//                    return true;
//                }
//            } else if (registros.get().size() == 1 && registros.get().get(0).getStatus() == statusPaquimetro) {
//                return true;
//            }
//        }
//        return false;
//    }

}
