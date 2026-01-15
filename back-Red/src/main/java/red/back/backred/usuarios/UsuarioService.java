package red.back.backred.usuarios;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository repo;
    private final PasswordEncoder encoder;

    public Usuario crear(Usuario usuario) {
        usuario.setPassword(encoder.encode(usuario.getPassword()));
        usuario.setActivo(true);
        return repo.save(usuario);
    }

    public List<Usuario> listar() {
        return repo.findAll();
    }

    public void desactivar(Long id) {
        Usuario usuario = repo.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        usuario.setActivo(false);
        repo.save(usuario);
    }

    public void eliminar(Long id) {
        repo.deleteById(id);
    }
}
