package net.ausiasmarch.equipo_futbol.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import net.ausiasmarch.equipo_futbol.entity.JugadorEntity;
import net.ausiasmarch.equipo_futbol.service.JugadorService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600) // Configura el comportamiento del CORS
@RestController // Marca la clase como un controlador REST
@RequestMapping("/jugador") // Ruta del controlador al recibir una solicitud HTTP
public class JugadorApi {

    @Autowired
    JugadorService oJugadorService; // Inyección de dependencias

    @GetMapping("/{id}") // Obtener un Jugador mediante su id
    public ResponseEntity<JugadorEntity> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oJugadorService.get(id));
    }

    @PostMapping("") // Crea un nuevo Jugador
    public ResponseEntity<Long> create(@RequestBody JugadorEntity oJugadorEntity) {
        return ResponseEntity.ok(oJugadorService.create(oJugadorEntity));
    }

    @PutMapping("") // Actualiza un Jugador
    public ResponseEntity<JugadorEntity> update(
            @RequestBody JugadorEntity oJugadorEntity) {
        return ResponseEntity.ok(oJugadorService.update(oJugadorEntity));
    }

    @DeleteMapping("/{id}") // Elimina un Jugador a partir de su id
    public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oJugadorService.delete(id));
    }

    @GetMapping("") // ?
    public ResponseEntity<Page<JugadorEntity>> getPage(Pageable oPageable) {
        return ResponseEntity.ok(oJugadorService.getPage(oPageable));
    }

    @PostMapping("/populate/{amount}") // ?
    public ResponseEntity<Long> populate(@PathVariable("amount") Integer amount) {
        return ResponseEntity.ok(oJugadorService.populate(amount));
    }
    
}
