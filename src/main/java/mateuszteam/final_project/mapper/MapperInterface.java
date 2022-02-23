package mateuszteam.final_project.mapper;

public interface MapperInterface<T,R> {

    T mapFromDtoToDomain(R r);
    R mapFromDomainToDto(T t);


}
