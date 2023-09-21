package lave_em_casa_api.api.models;


public enum UserRole {
    ADMIN("admin"),
    USER("user");

    private String roles;

    UserRole(String role){
        this.roles = role;
    }

    public String getRole(){
        return roles;
    }
}