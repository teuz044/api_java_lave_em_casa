package lave_em_casa_api.api.models;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Table(name = "usuarios_proprietarios")
@Entity(name = "usuarios_proprietarios")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class UsuariosProprietarios implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String cpf;

    private String nome;

    private String email;

    @Column(name = "data_nascimento_proprietario")
    private Date dataNascimentoProprietario;

    private String senha;

    private String telefone;

    private String endereco;

    private String cep;

    private Double rendaTotal;

    private Double pesoSuportadoMaquina;

    private Integer quantidadeMaquinas;

    @OneToMany(mappedBy = "proprietario")
    private List<Anuncios> anuncios;

    @Column(name = "quantidade_anuncios")
    private Integer quantidadeAnuncios;
    @Column(name = "roles")
    @Enumerated(EnumType.STRING)
    private UserRole roles;

    public UsuariosProprietarios(String login, String password, UserRole role){
        this.cpf = login;
        this.senha = password;
        this.roles = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.roles == UserRole.ADMIN) return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USER"));
        else return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return cpf;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    // getters and setters
}
