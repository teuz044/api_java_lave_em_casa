package lave_em_casa_api.api.controllers;
import jakarta.validation.Valid;
import lave_em_casa_api.api.config.TokenService;
import lave_em_casa_api.api.dto.Login;
import lave_em_casa_api.api.dto.LoginResponseDTO;
import lave_em_casa_api.api.dto.RegisterDTO;
import lave_em_casa_api.api.models.UsuariosProprietarios;
import lave_em_casa_api.api.repositories.ProprietariosRepository;
import lave_em_casa_api.api.services.ProprietarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;
import java.util.List;

@CrossOrigin("*")
@RestController
    @RequestMapping("/proprietarios")
public class ProprietarioController {

    @Autowired
    private final ProprietarioService proprietarioService;
    @Autowired
    private ProprietariosRepository proprietarioRepository;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private ProprietariosRepository repository;
    @Autowired
    private TokenService tokenService;
    @Autowired
    public ProprietarioController(ProprietarioService proprietarioService) {
        this.proprietarioService = proprietarioService;
    }

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;


//    @PostMapping ("/cadastro")
//    public ResponseEntity<UsuariosProprietarios> criarProprietario(@RequestBody UsuariosProprietarios proprietario) {
//        UsuariosProprietarios novoProprietario = proprietarioService.criarProprietario(proprietario);
//        List<UsuariosProprietarios> buscarUsuario = proprietarioService.getProprietariosCpf(proprietario.getCpf());
//        for (UsuariosProprietarios proprietarioExistente : buscarUsuario) {
//            if (novoProprietario.getCpf().equals(proprietarioExistente.getCpf())) {
//                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
//            }
//        }
//        return ResponseEntity.status(HttpStatus.CREATED).body(novoProprietario);
//    }

    @PostMapping("/register")
    public ResponseEntity register(@RequestBody @Valid RegisterDTO data){
        if(this.repository.findByCpf(data.login()) != null) return ResponseEntity.badRequest().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
        UsuariosProprietarios newUser = new UsuariosProprietarios(data.login(), encryptedPassword, data.role());

        this.repository.save(newUser);

        return ResponseEntity.ok().build();
    }


    @PostMapping("/login")
    public ResponseEntity login(@RequestBody @Valid Login data) {
        var usernamePassword = new UsernamePasswordAuthenticationToken(data.login(), data.password());
        var auth = this.authenticationManager.authenticate(usernamePassword);
        UsuariosProprietarios usuarioLogado =  proprietarioService.getProprietariosCpf(data.login());
        var token = tokenService.generateToken((UsuariosProprietarios) auth.getPrincipal());

        if (token.isEmpty() || usuarioLogado == null) {
            return ResponseEntity.badRequest().build();
        }

        // Retorne os dados do usuário e o token na resposta
        LoginResponseDTO responseDTO = new LoginResponseDTO(token, usuarioLogado);
        return ResponseEntity.ok(responseDTO);
    }
}
