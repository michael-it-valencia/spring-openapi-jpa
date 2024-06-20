package com.michael.valencia.prototypes.api.template.mappers;

import java.util.Collection;
import java.util.List;

import org.mapstruct.Mapper;

import com.michael.valencia.prototypes.api.openapi.model.UserDto;
import com.michael.valencia.prototypes.api.template.models.User;


@Mapper(componentModel = "spring")
public interface UserDtoMapper {

    UserDto toUserDto(User source);

    User toUser(UserDto destination);

    List<UserDto> toUsersDto(Collection<User> users);

}