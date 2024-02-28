package com.backendorg.quizapp.mapper;

public interface BaseMapper<Entity, DTO> {

    Entity dtoToEntity(DTO dto);

    DTO entityToDTO(Entity entity);
}
