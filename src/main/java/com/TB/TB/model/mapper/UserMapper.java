package com.TB.TB.model.mapper;

import com.TB.TB.model.dto.UserDto;
import com.TB.TB.model.entity.authorization.User;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {

	UserDto toDto(User user);

	User toEntity(UserDto userDto);

	void merge(@MappingTarget User user, UserDto userDto);
}
