package lave_em_casa_api.api.services;
import lave_em_casa_api.api.models.UsuariosProprietarios;
import lave_em_casa_api.api.repositories.ProprietariosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProprietarioService {

    private final ProprietariosRepository proprietariosRepository;

    @Autowired
    public ProprietarioService(ProprietariosRepository proprietariosRepository) {
        this.proprietariosRepository = proprietariosRepository;
    }

    public UsuariosProprietarios criarProprietario(UsuariosProprietarios proprietario) {
        return proprietariosRepository.save(proprietario);
    }

    public List<UsuariosProprietarios> getProprietariosCpf(String cpf) {
        return proprietariosRepository.getProprietarioPorCpf(cpf);
    }


}

