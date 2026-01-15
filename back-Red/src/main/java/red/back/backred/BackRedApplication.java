package red.back.backred;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import red.back.backred.usuarios.Rol;
import red.back.backred.usuarios.Usuario;
import red.back.backred.usuarios.UsuarioRepository;

@SpringBootApplication
@EnableMethodSecurity
public class BackRedApplication {

    public static void main(String[] args) {
        SpringApplication.run(BackRedApplication.class, args);
    }

    @Bean
    CommandLineRunner initUsers(
            UsuarioRepository repo,
            PasswordEncoder encoder) {

        return args -> {

            // ADMIN POR DEFECTO
            if (repo.findByEmail("admin@admin.com").isEmpty()) {
                repo.save(
                        Usuario.builder()
                                .nombre("Administrador")
                                .email("admin@admin.com")
                                .password(encoder.encode("123456"))
                                .rol(Rol.ADMIN)
                                .activo(true)
                                .build()
                );
            }

            // USUARIO COMÚN POR DEFECTO
            if (repo.findByEmail("user@user.com").isEmpty()) {
                repo.save(
                        Usuario.builder()
                                .nombre("Usuario Prueba")
                                .email("user@user.com")
                                .password(encoder.encode("123456"))
                                .rol(Rol.USUARIO)
                                .activo(true)
                                .build()
                );
            }
        };
    }
}
