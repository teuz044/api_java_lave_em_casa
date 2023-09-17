package lave_em_casa_api.api.controllers;

import lave_em_casa_api.api.dto.Login;
import lave_em_casa_api.api.models.UsuariosProprietarios;
import lave_em_casa_api.api.services.ProprietarioService;
import lave_em_casa_api.api.services.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/proprietarios")
public class ProprietarioController {

    private final ProprietarioService proprietarioService;

    @Autowired
    public ProprietarioController(ProprietarioService proprietarioService) {
        this.proprietarioService = proprietarioService;
    }

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;
    @PostMapping ("/cadastro")
    public ResponseEntity<UsuariosProprietarios> criarProprietario(@RequestBody UsuariosProprietarios proprietario) {
        UsuariosProprietarios novoProprietario = proprietarioService.criarProprietario(proprietario);
        List<UsuariosProprietarios> buscarUsuario = proprietarioService.getProprietariosCpf(proprietario.getCpf());
        for (UsuariosProprietarios proprietarioExistente : buscarUsuario) {
            if (novoProprietario.getCpf().equals(proprietarioExistente.getCpf())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(novoProprietario);
    }

    @PostMapping("/login")
    public String login (@RequestBody Login login) {
       UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
               new UsernamePasswordAuthenticationToken(login.login(), login.password());

      Authentication authenticate = this.authenticationManager.authenticate(usernamePasswordAuthenticationToken);

      var usuario = (UsuariosProprietarios) authenticate.getPrincipal();

      return tokenService.gerarToken(usuario);
    }
}
