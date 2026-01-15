package red.back.backred.carreras;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarreraService {

    private final CarreraRepository repo;

    public Carrera crear(Carrera carrera) {
        carrera.setActiva(true);
        carrera.setSolicitudes(0);
        return repo.save(carrera);
    }

    public List<Carrera> listarActivas() {
        return repo.findByActivaTrue();
    }

    public List<Carrera> top3() {
        return repo.findTop3ByActivaTrueOrderBySolicitudesDesc();
    }

    public void incrementarSolicitudes(String nombreCarrera) {
        repo.findAll().stream()
                .filter(c -> c.getNombre().equalsIgnoreCase(nombreCarrera))
                .findFirst()
                .ifPresent(c -> {
                    c.setSolicitudes(c.getSolicitudes() + 1);
                    repo.save(c);
                });
    }
}
