package com.caua.agendador.business.service;

import com.caua.agendador.business.dto.in.TarefasDTORequest;
import com.caua.agendador.business.dto.out.TarefasDTOResponse;
import com.caua.agendador.business.enums.StatusNotificacaoEnum;
import com.caua.agendador.infrastructure.client.TarefasClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasClient tarefasClient;

    public TarefasDTOResponse gravarTarefas(TarefasDTORequest dto, String token) {
        return tarefasClient.gravarTarefas(dto, normalizeAuthorizationHeader(token));
    }

    public List<TarefasDTOResponse> buscarTarefasAgendadasPorPeriodo(LocalDateTime dataInicial,
                                                                     LocalDateTime dataFinal, String token) {
        return tarefasClient.buscaListaDeTarefasPorPeriodo(
                dataInicial,
                dataFinal,
                normalizeAuthorizationHeader(token)
        );

    }

    public List<TarefasDTOResponse> buscarTarefasPorEmail(String token) {
        return tarefasClient.buscaTarefasPorEmail(normalizeAuthorizationHeader(token));
    }

    public void deletarTarefasPorId(String id, String token) {
        tarefasClient.deletarTarefasPorId(id, normalizeAuthorizationHeader(token));
    }

    public TarefasDTOResponse alteraStatus(StatusNotificacaoEnum status, String id, String token) {
        return tarefasClient.alteraStatusNotificacao(status, id, normalizeAuthorizationHeader(token));
    }

    public TarefasDTOResponse updateTarefas(TarefasDTORequest dto, String id, String token) {
        return tarefasClient.updateTarefas(dto, id, normalizeAuthorizationHeader(token));
    }

    private String normalizeAuthorizationHeader(String token) {
        if (!StringUtils.hasText(token)) {
            return null;
        }

        String normalizedToken = token.trim();
        if (normalizedToken.regionMatches(true, 0, "Bearer ", 0, 7)) {
            return "Bearer " + normalizedToken.substring(7).trim();
        }

        return "Bearer " + normalizedToken;
    }
}
