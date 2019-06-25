package com.danielsolawa.spaceflight.mapper;

public interface Mapper <T1, T2> {

    // Maps a domain object to a dto.
    T2 MapToDto(T1 t1);

    // Maps a dto to a domain object.
    T1 MapFromDto(T2 t2);
}
