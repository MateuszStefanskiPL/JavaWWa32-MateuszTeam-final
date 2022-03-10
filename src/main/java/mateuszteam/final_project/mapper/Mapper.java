package mateuszteam.final_project.mapper;


public interface Mapper<ENTITY,DTO> {

    ENTITY mapFromDtoToDomain(DTO dto);

    DTO mapFromDomainToDto(ENTITY entity);


    //todo zbudować generyczny interfejs dla wszystkich mapperów

}
