    package lave_em_casa_api.api.repositories;

    import lave_em_casa_api.api.models.Anuncios;
    import lave_em_casa_api.api.models.UsuariosProprietarios;
    import org.springframework.data.jpa.repository.JpaRepository;
    import org.springframework.data.jpa.repository.Query;
    import org.springframework.data.repository.query.Param;
    import org.springframework.stereotype.Repository;

    import java.util.List;
    import java.util.Optional;

    @Repository
    public interface ProprietariosRepository extends JpaRepository<UsuariosProprietarios, Integer> {
        @Query(nativeQuery = true, value = "SELECT * FROM usuarios_proprietario WHERE cpf = :cpf")
        public abstract List<UsuariosProprietarios> getProprietarioPorCpf(@Param("cpf") String cpf);

        UsuariosProprietarios findByCpf(String cpf);
    }
