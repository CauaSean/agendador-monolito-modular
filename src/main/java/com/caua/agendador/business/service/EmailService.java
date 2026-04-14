package com.caua.agendador.business.service;


import com.caua.agendador.business.dto.out.TarefasDTOResponse;
import com.caua.agendador.infrastructure.client.EmailClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final EmailClient emailClient;

    public void enviarEmail(TarefasDTOResponse dto) {emailClient.enviarEmail(dto);
    }
}