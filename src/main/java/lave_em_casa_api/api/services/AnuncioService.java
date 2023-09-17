package lave_em_casa_api.api.services;


import lave_em_casa_api.api.models.Anuncios;
import lave_em_casa_api.api.models.UsuariosProprietarios;
import lave_em_casa_api.api.repositories.AnuncioRepository;
import lave_em_casa_api.api.repositories.ProprietariosRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnuncioService {

    private final AnuncioRepository anuncioRepository;
    private final ProprietariosRepository proprietariosRepository;

    public AnuncioService(AnuncioRepository anuncioRepository, ProprietariosRepository proprietariosRepository) {
        this.anuncioRepository = anuncioRepository;
        this.proprietariosRepository = proprietariosRepository;
    }

    public Anuncios criarAnuncio(Integer proprietarioId, Anuncios anuncio) {
        UsuariosProprietarios proprietario = proprietariosRepository.findById(proprietarioId)
                .orElseThrow(() -> new RuntimeException("Usuário proprietário não encontrado"));
        anuncio.setProprietario(proprietario);


        Anuncios novoAnuncio = anuncioRepository.save(anuncio);

        return novoAnuncio;
    }

    public List<Anuncios> getAnuncios() {
        return anuncioRepository.findAll();
    }

    public List<Anuncios> getAnunciosPorCidade(@Param("cidade") String cidade) {
        return anuncioRepository.getAnunciosPorCidade(cidade);
    }

}
