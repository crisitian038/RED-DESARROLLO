package red.back.backred.carreras;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/carreras")
@RequiredArgsConstructor
public class CarreraController {

    private final CarreraService service;

    // 🌐 PÚBLICO (HOME / OFERTA EDUCATIVA)
    @GetMapping
    public List<Carrera> listar() {
        return service.listarActivas();
    }

    @GetMapping("/top")
    public List<Carrera> topCarreras() {
        return service.top3();
    }

    // 🔒 SOLO ADMIN
    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Carrera crear(@RequestBody Carrera carrera) {
        return service.crear(carrera);
    }
}
