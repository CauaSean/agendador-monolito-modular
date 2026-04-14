package com.caua.agendador.business.service;

import com.caua.agendador.business.dto.in.TarefasDTORequest;
import com.caua.agendador.business.dto.out.TarefasDTOResponse;
import com.caua.agendador.business.enums.StatusNotificacaoEnum;
import com.caua.agendador.infrastructure.client.TarefasClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasClient tarefasClient;

    public TarefasDTOResponse gravarTarefas(TarefasDTORequest dto, String token) {
        return tarefasClient.gravarTarefas(dto, token);
    }

    public List<TarefasDTOResponse> buscarTarefasAgendadasPorPeriodo(LocalDateTime dataInicial,
                                                                     LocalDateTime dataFinal, String token) {
        return tarefasClient.buscaListaDeTarefasPorPeriodo(dataInicial, dataFinal, token);

    }

    public List<TarefasDTOResponse> buscarTarefasPorEmail(String token) {
        String cleanedToken = token != null && token.startsWith("Bearer ") ? token.substring(7) : token;
        System.out.println("DEBUG - Sending Token to MS: " + token);
        return tarefasClient.buscaTarefasPorEmail(cleanedToken);
    }

    public void deletarTarefasPorId(String id, String token) {
        tarefasClient.deletarTarefasPorId(id, token);
    }

    public TarefasDTOResponse alteraStatus(StatusNotificacaoEnum status, String id, String token) {
        return tarefasClient.alteraStatusNotificacao(status, id, token);
    }

    public TarefasDTOResponse updateTarefas(TarefasDTORequest dto, String id, String token) {
        return tarefasClient.updateTarefas(dto, id, token);
    }
}