package com.caua.agendador.business.mapper;


import com.caua.agendador.business.dto.in.TarefasDTORequest;
import com.caua.agendador.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TarefasConverter {

    TarefasEntity paraTarefaEntity(TarefasDTORequest dto);
    TarefasDTORequest paraTarefasDTORecord(TarefasEntity entity);

    List<TarefasDTORequest> paraListaTarefasDTORecord(List<TarefasEntity> entities);
}