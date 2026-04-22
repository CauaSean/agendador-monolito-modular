package com.caua.agendador.business.service;

import com.caua.agendador.business.dto.out.TarefasDTOResponse;
import com.caua.agendador.business.enums.StatusNotificacaoEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CronService {
    private final TarefasService tarefasService;
    private final EmailService emailService;

    @Value("${usuario.email}")
    private String email;

    @Value("${usuario.senha}")
    private String senha;

    @Value("${tarefas.service-token:}")
    private String tarefasServiceToken;

    @Scheduled(cron = "${cron.horario}")
    public void buscaTarefasProximaHora(){
        try {
            log.info("Iniciada a busca de tarefas agendadas");

            if (!StringUtils.hasText(tarefasServiceToken)) {
                log.warn("Execucao do cron ignorada: propriedade tarefas.service-token nao configurada");
                return;
            }

            LocalDateTime horaFutura = LocalDateTime.now().plusHours(1);
            LocalDateTime horaFuturaMaisCinco = LocalDateTime.now().plusHours(1).plusMinutes(5);
            log.info("Buscando tarefas entre " + horaFutura + " e " + horaFuturaMaisCinco);

            List<TarefasDTOResponse> listaTarefas = tarefasService.buscarTarefasAgendadasPorPeriodo(
                    horaFutura,
                    horaFuturaMaisCinco,
                    tarefasServiceToken
            );

            if (listaTarefas == null || listaTarefas.isEmpty()) {
                log.info("Nenhuma tarefa encontrada para notificação");
                return;
            }

            log.info("Total de tarefas encontradas: " + listaTarefas.size());

            listaTarefas.forEach(tarefa -> {
                try {
                    emailService.enviarEmail(tarefa);
                    log.info("Email enviado para: " + tarefa.getEmailUsuario());

                    tarefasService.alteraStatus(
                            StatusNotificacaoEnum.NOTIFICADO,
                            tarefa.getId(),
                            tarefasServiceToken
                    );

                } catch (Exception e) {
                    log.error("Erro ao processar tarefa " + tarefa.getId() + ": " + e.getMessage(), e);
                }
            });

            log.info("Finalizada a busca e notificação de tarefas");
        } catch (Exception e) {
            log.error("Erro ao executar busca de tarefas agendadas: " + e.getMessage(), e);
        }
    }
}
