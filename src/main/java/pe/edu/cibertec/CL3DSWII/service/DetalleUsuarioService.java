package pe.edu.cibertec.CL3DSWII.service;


import lombok.AllArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.edu.cibertec.CL3DSWII.model.bd.Rol;
import pe.edu.cibertec.CL3DSWII.model.bd.Usuario;
import pe.edu.cibertec.CL3DSWII.repository.UsuarioRepository;


import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@Service
public class DetalleUsuarioService implements UserDetailsService {
    private UsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioRepository.findByNomusuario(username);
        return autenticacionUsuario(
                usuario,
                obtenerListaRolesUsuario(usuario.getRoles())
            );
    }
    public List<GrantedAuthority> obtenerListaRolesUsuario(Set<Rol> listRoles) {
        Set<GrantedAuthority> roles = new HashSet<GrantedAuthority>();
        for (Rol rol : listRoles) {
            roles.add(new SimpleGrantedAuthority(rol.getNomrol()));
        }
        List<GrantedAuthority> grantedAuthorities = new ArrayList<>(roles);
        return grantedAuthorities;
    }

    public Usuario findByNomusuario(String usuario){
        return usuarioRepository.findByNomusuario(usuario);
    }

    public Usuario guardarUsuario(Usuario usuario) {
        // Implementa la lógica para guardar el usuario en la base de datos
        // Puedes usar el repositorio de Spring Data JPA para esto
        return usuarioRepository.save(usuario); // Asegúrate de tener UserRepository definido en tu proyecto
    }
    private UserDetails autenticacionUsuario(
            Usuario usuario, List<GrantedAuthority> authorityList) {
        UserDetails userDetails = new User(usuario.getNomusuario(),
                usuario.getPassword(),
                usuario.getActivo(),
                true,
                true,
                true, authorityList);
        return userDetails;
    }

}
