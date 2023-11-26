package pe.edu.cibertec.CL3DSWII.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.cibertec.CL3DSWII.model.bd.sp.RolUsuarioSp;

import java.util.List;

@Repository
public interface RolUsuarioSpRepository extends
        JpaRepository<RolUsuarioSp, Integer> {


    @Query(value = "{call sp_RolxUsuario(:_idusuario)}",
            nativeQuery = true)
    List<RolUsuarioSp> listarRolesPorUsuario(@Param("_idusuario") int _idusuario);


}