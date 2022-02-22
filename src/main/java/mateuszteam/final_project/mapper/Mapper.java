package mateuszteam.final_project.mapper;

public interface Mapper<T,R> {

    T mapFromDtoToDomain(R r);
    R mapFromDomainToDto(T t);


}
