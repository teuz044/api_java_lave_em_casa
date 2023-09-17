package lave_em_casa_api.api.controllers;


import lave_em_casa_api.api.models.Anuncios;
import lave_em_casa_api.api.services.AnuncioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Path;
import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/anuncios")
public class AnuncioController {

    private final AnuncioService anuncioService;

    @Autowired
    public AnuncioController(AnuncioService anuncioService) {
        this.anuncioService = anuncioService;
    }

    @PostMapping("/{proprietarioId}")
    public ResponseEntity<Anuncios> criarAnuncio(@PathVariable Integer proprietarioId, @RequestBody Anuncios anuncio) {
        Anuncios novoAnuncio = anuncioService.criarAnuncio(proprietarioId, anuncio);
        return ResponseEntity.status(HttpStatus.CREATED).body(novoAnuncio);
    }

    @GetMapping
    public ResponseEntity<List<Anuncios>> getAnuncios() {
        List<Anuncios> anuncios = anuncioService.getAnuncios();
        return ResponseEntity.ok(anuncios);
    }

    @GetMapping("/{cidade}")
    public ResponseEntity<List<Anuncios>> getAnuncios(@PathVariable String cidade) {
        List<Anuncios> anuncios = anuncioService.getAnunciosPorCidade(cidade);
        return ResponseEntity.ok(anuncios);
    }
}
