package com.mecklon.backend.mapper;


import com.mecklon.backend.DTO.FlightDto;
import com.mecklon.backend.model.Flight;
import org.mapstruct.Mapper;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring")
public interface FlightMapper {
    FlightDto toDto(Flight flight);
}
