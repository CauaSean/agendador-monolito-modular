package com.caua.agendador.infrastructure.entity;

import com.caua.agendador.business.enums.StatusNotificacaoEnum;
import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDateTime;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tarefa")
public class TarefasEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String nomeTarefa;
    private String descricao;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataEvento;
    private String emailUsuario;
    private LocalDateTime dataAlteracao;
    @Enumerated(EnumType.STRING)
    private StatusNotificacaoEnum statusNotificacaoEnum;

}
