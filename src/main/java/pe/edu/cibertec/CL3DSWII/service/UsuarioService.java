package pe.edu.cibertec.CL3DSWII.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.CL3DSWII.model.bd.Usuario;
import pe.edu.cibertec.CL3DSWII.repository.UsuarioRepository;


@AllArgsConstructor
@Service
public class UsuarioService {

    private UsuarioRepository usuarioRepository;

    public Usuario findByNomusuario(String usuario){
        return usuarioRepository.findByNomusuario(usuario);
    }


}
