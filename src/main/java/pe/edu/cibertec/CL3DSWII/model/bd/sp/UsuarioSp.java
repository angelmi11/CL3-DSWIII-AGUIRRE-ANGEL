package pe.edu.cibertec.CL3DSWII.model.bd.sp;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class UsuarioSp {
    @Id
    private Long idusuario;
    private String nomusuario;
}