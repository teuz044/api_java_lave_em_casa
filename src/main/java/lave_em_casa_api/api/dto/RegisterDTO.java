package lave_em_casa_api.api.dto;

import lave_em_casa_api.api.models.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}