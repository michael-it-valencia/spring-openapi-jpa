package com.michael.valencia.prototypes.api.template.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.michael.valencia.prototypes.api.openapi.ApiUtil;
import com.michael.valencia.prototypes.api.openapi.UsersApi;
import com.michael.valencia.prototypes.api.openapi.model.UserDto;
import com.michael.valencia.prototypes.api.template.mappers.UserDtoMapper;
import com.michael.valencia.prototypes.api.template.models.User;
import com.michael.valencia.prototypes.api.template.service.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/v1")
public class UsersApiController implements UsersApi {

    @Autowired
    private UserDtoMapper userDtoMapper;
    @Autowired
    private UserService userService;
    /**
     * POST /users : Create new user of Prototype API
     * Create new user of Prototype API
     *
     * @param userDto User details user creation (required)
     * @return Success (status code 200)
     */
    @Operation(
        operationId = "createUser",
        summary = "Create new user of Prototype API",
        description = "Create new user of Prototype API",
        tags = { "users" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/users",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    
    public ResponseEntity<UserDto> createUser(
        @Parameter(name = "UserDto", description = "User details user creation", required = true) @Valid @RequestBody UserDto userDto
    ) {
        User user = userDtoMapper.toUser(userDto);
		user = userService.createUser(user);
		UserDto resultUserDto = userDtoMapper.toUserDto(user);
		return new ResponseEntity<>(resultUserDto, HttpStatus.CREATED);

    }


    /**
     * GET /users : Get all users of Prototype API
     * Get all users of Prototype API
     *
     * @return Success (status code 200)
     */
    @Operation(
        operationId = "listUsers",
        summary = "Get all users of Prototype API",
        description = "Get all users of Prototype API",
        tags = { "users" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = UserDto.class)))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/users",
        produces = { "application/json" }
    )
    
    public ResponseEntity<List<UserDto>> listUsers(
        
    ) {
        List<User> users = userService.listUsers();
		List<UserDto> usersDto = userDtoMapper.toUsersDto(users);
		if (users.isEmpty()) {
			return new ResponseEntity<>(usersDto, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<>(usersDto, HttpStatus.OK);
		}

    }


    /**
     * GET /users/{id} : Find a user by ID of Prototype API
     * Find a user by ID of Prototype API
     *
     * @param id  (required)
     * @return Success (status code 200)
     */
    @Operation(
        operationId = "searchUserById",
        summary = "Find a user by ID of Prototype API",
        description = "Find a user by ID of Prototype API",
        tags = { "users" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/users/{id}",
        produces = { "application/json" }
    )
    
    public ResponseEntity<UserDto> searchUserById(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Integer id
    ) {
        User user = userService.findUserById(id);
		if (user != null) {
			UserDto userDto = userDtoMapper.toUserDto(user);
			return new ResponseEntity<>(userDto, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}

    }


    /**
     * PUT /users/{id} : Update user of Prototype API
     * Update user of Prototype API
     *
     * @param id  (required)
     * @param userDto User details to be modified (required)
     * @return Success (status code 200)
     */
    @Operation(
        operationId = "updateUser",
        summary = "Update user of Prototype API",
        description = "Update user of Prototype API",
        tags = { "users" },
        responses = {
            @ApiResponse(responseCode = "200", description = "Success", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = UserDto.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/users/{id}",
        produces = { "application/json" },
        consumes = { "application/json" }
    )
    
    public ResponseEntity<UserDto> updateUser(
        @Parameter(name = "id", description = "", required = true, in = ParameterIn.PATH) @PathVariable("id") Integer id,
        @Parameter(name = "UserDto", description = "User details to be modified", required = true) @Valid @RequestBody UserDto userDto
    ) {
       
        User dbUser = userService.findUserById(id);
        User user = userDtoMapper.toUser(userDto);
        if(dbUser==null || !dbUser.getId().equals(user.getId())){
            return new ResponseEntity<>( HttpStatus.BAD_REQUEST);
        }

		// Service method to update user
		User updatedUser = userService.updateUser(user);

		// Convert updated User entity back to UserDto
		UserDto resultUserDto = userDtoMapper.toUserDto(updatedUser);

		return new ResponseEntity<>(resultUserDto, HttpStatus.OK);

    }

}
