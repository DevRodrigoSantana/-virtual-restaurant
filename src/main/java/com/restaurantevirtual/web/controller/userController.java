package com.restaurantevirtual.web.controller;


import com.restaurantevirtual.model.dto.DTOUpdatePassword;
import com.restaurantevirtual.model.dto.DTOUser;
import com.restaurantevirtual.model.dto.DTOResponseUser;
import com.restaurantevirtual.model.dto.mapper.UserMapper;
import com.restaurantevirtual.model.entity.User;
import com.restaurantevirtual.service.ServiceUser;
import com.restaurantevirtual.web.exception.ErrorMessage;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@Tag(name = "Usuarios",description = "Contém todas as operações para cadastro,edição,exclusão e leitura de usuarios.")
@RestController
@RequestMapping("v1/user")
public class userController {


    private final ServiceUser service;


    public userController(ServiceUser service) {
        this.service = service;
    }


    @Operation(summary = "Criar um novo usuario",description = "Recurso para criar usuarios.",
                            responses = {
                                        @ApiResponse(responseCode = "201",
                                                    description = "Recurso criado com sucesso.",
                                                    content = @Content(mediaType = "application/json",
                                                                        schema = @Schema(implementation = DTOResponseUser.class ))),
                                        @ApiResponse(responseCode = "409",
                                                    description = "Usuario e-mail já cadastrado no sistema.",
                                                    content = @Content(mediaType = "application/json",
                                                                        schema = @Schema(implementation = ErrorMessage.class))),
                                        @ApiResponse(responseCode = "422",
                                                    description = "Recurso não processado por dados de entrada incorretos.",
                                                    content = @Content(mediaType = "application/json",
                                                                        schema = @Schema(implementation = ErrorMessage.class)))
                            })
    @PostMapping
    public ResponseEntity<DTOResponseUser> createUser(@Valid @RequestBody DTOUser dto){

        User user = service.create(UserMapper.toUser(dto));

        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toResponseDto(user));
    }


    @Operation(summary = "Recuperar usuario por ID.",description = "Recurso de Recuperação de usuario por um id.",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Recurso recuperado com sucesso.",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = DTOResponseUser.class ))),
                    @ApiResponse(responseCode = "404",
                            description = "Recurso não encontrado.",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)))
            })
    @GetMapping("/{id}")
    public ResponseEntity<DTOResponseUser> getUser(@PathVariable UUID id){

        User user = service.findUserId(id);

        return ResponseEntity.status(HttpStatus.OK).body(UserMapper.toResponseDto(user));
    }


    @Operation(summary = "Recuperar todos os usuarios.",description = "Recurso de recuperação de todos usuarios.",
            responses = {
                    @ApiResponse(responseCode = "200",
                            description = "Recurso recuperado com sucesso.",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = DTOResponseUser.class ))),

            })
    @GetMapping
    public ResponseEntity<List<DTOResponseUser>> getListUsers(){

        List<User> users = service.searchAllUsers();

        return ResponseEntity.status(HttpStatus.OK).body(UserMapper.toListUsers(users));

    }

    @Operation(summary = "Deletar Usuario ",description = "Recurso para Deletar usuarios pelo id.",
            responses = {
                    @ApiResponse(responseCode = "204",
                            description = "Recurso deletado com sucesso.",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Void.class))),
                    @ApiResponse(responseCode = "404",
                            description = "Falha ao deletar, id não encontrado.",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "400",
                            description = "Recurso não processado por senhas de entrada incorretos.",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)))
            })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable UUID id){

        service.deleteUser(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }


    @Operation(summary = "Atualizar senha ",description = "atualizar senha",
            responses = {
                    @ApiResponse(responseCode = "204",
                            description = "Senha Atualizada com sucesso.",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = Void.class))),
                    @ApiResponse(responseCode = "404",
                            description = "Falha ao atualizar, id não encontrado.",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class))),
                    @ApiResponse(responseCode = "400",
                            description = "Recurso não processado por senhas de entrada incorretos.",
                            content = @Content(mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorMessage.class)))
            })
    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassword(@Valid @PathVariable UUID id,@RequestBody DTOUpdatePassword dto){

       service.UpdatePassword(dto.getPassedPassword(),dto.getCurrentPassword(),dto.getConfirmPassword(),id);


      return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
