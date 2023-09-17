package lave_em_casa_api.api.repositories;

import lave_em_casa_api.api.models.Anuncios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnuncioRepository extends JpaRepository<Anuncios, Integer> {

    @Query(nativeQuery = true, value = "SELECT * FROM anuncios WHERE cidade = :cidade")
    public abstract List<Anuncios> getAnunciosPorCidade(@Param("cidade") String cidade);
}
