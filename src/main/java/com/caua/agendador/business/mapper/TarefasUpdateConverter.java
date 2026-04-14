package com.caua.agendador.business.mapper;

import com.caua.agendador.business.dto.out.TarefasDTOResponse;
import com.caua.agendador.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TarefasUpdateConverter {

    void  updateTarefas(TarefasDTOResponse dto, @MappingTarget TarefasEntity entity);
}
