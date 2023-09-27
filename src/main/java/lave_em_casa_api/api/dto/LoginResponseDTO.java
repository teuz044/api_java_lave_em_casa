package lave_em_casa_api.api.dto;

import lave_em_casa_api.api.models.UsuariosProprietarios;

public record LoginResponseDTO(String token, UsuariosProprietarios user) {
}