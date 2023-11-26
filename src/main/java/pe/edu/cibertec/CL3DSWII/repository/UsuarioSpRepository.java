package pe.edu.cibertec.CL3DSWII.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.CL3DSWII.model.bd.sp.UsuarioSp;

import java.util.Optional;

@Repository
public interface UsuarioSpRepository extends
        JpaRepository<UsuarioSp, Integer> {
    @Query(value = "{call sp_AutenticarUsuario(:_usuario, :_password)}",
            nativeQuery = true)
    Optional<UsuarioSp> autenticarUsuario(@Param("_usuario") String _usuario,
                                          @Param("_password") String _password);

}
