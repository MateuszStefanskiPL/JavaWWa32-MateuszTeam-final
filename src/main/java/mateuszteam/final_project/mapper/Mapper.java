package mateuszteam.final_project.mapper;

import org.springframework.stereotype.Component;

@Component
public interface Mapper<ENTITY,DTO> {

    ENTITY mapFromDtoToDomain(DTO dto);

    DTO mapFromDomainToDto(ENTITY entity);


    //todo zbudować generyczny interfejs dla wszystkich mapperów

}
