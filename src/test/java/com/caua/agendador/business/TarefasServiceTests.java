package com.caua.agendador.business;

import com.caua.agendador.business.dto.in.TarefasDTORequest;
import com.caua.agendador.business.dto.out.TarefasDTOResponse;
import com.caua.agendador.business.enums.StatusNotificacaoEnum;
import com.caua.agendador.business.service.TarefasService;
import com.caua.agendador.infrastructure.client.TarefasClient;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class TarefasServiceTest {

    @Mock
    private TarefasClient tarefasClient;

    @InjectMocks
    private TarefasService tarefasService;

    // gravarTarefas

    @Test
    void  gravarTarefas_passarDTOeTOKENaoClienteRetornarResponse() {
        TarefasDTORequest dto = new TarefasDTORequest();
        TarefasDTOResponse respostaEsperada = new TarefasDTOResponse();
        String token = "bearer sarrada77";

        when(tarefasClient.gravarTarefas(dto, token)).thenReturn(respostaEsperada);

        TarefasDTOResponse resposta = tarefasService.gravarTarefas(dto, token);

        assertThat(resposta).isEqualTo(respostaEsperada);
        verify(tarefasClient).gravarTarefas(dto, token);
    }

    // buscarTarefasAgendadasPorPeriodo

    @Test
    void buscarPorPeriodo_passarDataseTokensAiClient(){
        LocalDateTime inicio = LocalDateTime.of(2026, 1, 1, 1, 2);
        LocalDateTime fim = LocalDateTime.of(2026, 4, 25, 17, 39);
        String token = "bearer patinho77";
        List<TarefasDTOResponse> respostaEsperada = List.of(new TarefasDTOResponse());

        when(tarefasClient.buscaListaDeTarefasPorPeriodo(inicio, fim, token)).thenReturn(respostaEsperada);

        List<TarefasDTOResponse> resultado = tarefasService.buscarTarefasAgendadasPorPeriodo(inicio, fim, token);

        assertThat(resultado).isEqualTo(respostaEsperada);
        verify(tarefasClient).buscaListaDeTarefasPorPeriodo(inicio, fim, token);
    }


    // buscarTarefasPorEmail

    @Test
    void buscarTarefasPorEmail_deveRemoverPrefixoBearer_quandoTokenTemBearer() {
        String tokenComBearer = "Bearer abc123";
        String tokenLimpo = "abc123";
        List<TarefasDTOResponse> respostaEsperada = List.of(new TarefasDTOResponse());

        when(tarefasClient.buscaTarefasPorEmail(tokenLimpo)).thenReturn(respostaEsperada);

        List<TarefasDTOResponse> resultado = tarefasService.buscarTarefasPorEmail(tokenComBearer);

        assertThat(resultado).isEqualTo(respostaEsperada);
        // garante que o prefixo foi removido antes de chamar o client
        verify(tarefasClient).buscaTarefasPorEmail(tokenLimpo);
        verify(tarefasClient, never()).buscaTarefasPorEmail(tokenComBearer);
    }

    @Test
    void buscarTarefasPorEmail_deveManterToken_quandoNaoTemPrefixoBearer() {
        String tokenSemBearer = "abc123";
        List<TarefasDTOResponse> respostaEsperada = List.of(new TarefasDTOResponse());

        when(tarefasClient.buscaTarefasPorEmail(tokenSemBearer)).thenReturn(respostaEsperada);

        tarefasService.buscarTarefasPorEmail(tokenSemBearer);

        verify(tarefasClient).buscaTarefasPorEmail(tokenSemBearer);
    }

    @Test
    void buscarTarefasPorEmail_deveManterToken_quandoTokenEhNulo() {
        when(tarefasClient.buscaTarefasPorEmail(null)).thenReturn(List.of());

        tarefasService.buscarTarefasPorEmail(null);

        verify(tarefasClient).buscaTarefasPorEmail(null);
    }

    // deletarTarefasPorId

    @Test
    void deletarTarefasPorId_deveRepassarIdETokenAoClient() {
        tarefasService.deletarTarefasPorId("id-123", "Bearer abc123");

        verify(tarefasClient).deletarTarefasPorId("id-123", "Bearer abc123");
    }

    // alteraStatus

    @Test
    void alteraStatus_deveRepassarStatusIdETokenERetornarResposta() {
        TarefasDTOResponse respostaEsperada = new TarefasDTOResponse();
        when(tarefasClient.alteraStatusNotificacao(StatusNotificacaoEnum.NOTIFICADO, "id-1", "token"))
                .thenReturn(respostaEsperada);

        TarefasDTOResponse resultado =
                tarefasService.alteraStatus(StatusNotificacaoEnum.NOTIFICADO, "id-1", "token");

        assertThat(resultado).isEqualTo(respostaEsperada);
    }

    // updateTarefas

    @Test
    void updateTarefas_deveRepassarDtoIdETokenAoClient() {
        TarefasDTORequest dto = new TarefasDTORequest();
        TarefasDTOResponse respostaEsperada = new TarefasDTOResponse();

        when(tarefasClient.updateTarefas(dto, "id-1", "token")).thenReturn(respostaEsperada);

        TarefasDTOResponse resultado = tarefasService.updateTarefas(dto, "id-1", "token");

        assertThat(resultado).isEqualTo(respostaEsperada);
        verify(tarefasClient).updateTarefas(dto, "id-1", "token");
    }
}