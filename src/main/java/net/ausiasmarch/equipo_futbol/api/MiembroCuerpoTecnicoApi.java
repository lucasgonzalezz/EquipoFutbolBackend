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
import net.ausiasmarch.equipo_futbol.entity.MiembroCuerpoTecnicoEntity;
import net.ausiasmarch.equipo_futbol.service.MiembroCuerpoTecnicoService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600) // Configura el comportamiento del CORS
@RestController // Marca la clase como un controlador REST
@RequestMapping("/miembroCuerpoTecnico") // Ruta del controlador al recibir una solicitud HTTP
public class MiembroCuerpoTecnicoApi {

    @Autowired
    MiembroCuerpoTecnicoService oMiembroCuerpoTecnicoService; // Inyección de dependencias

    @GetMapping("/{id}") // Obtener un Miembro del Cuerpo Tecnico mediante su id
    public ResponseEntity<MiembroCuerpoTecnicoEntity> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oMiembroCuerpoTecnicoService.get(id));
    }

    @PostMapping("") // Crea un nuevo Miembro del Cuerpo Tecnico
    public ResponseEntity<Long> create(@RequestBody MiembroCuerpoTecnicoEntity oMiembroCuerpoTecnicoEntity) {
        return ResponseEntity.ok(oMiembroCuerpoTecnicoService.create(oMiembroCuerpoTecnicoEntity));
    }

    @PutMapping("") // Actualiza un Miembro del Cuerpo Tecnico
    public ResponseEntity<MiembroCuerpoTecnicoEntity> update(
            @RequestBody MiembroCuerpoTecnicoEntity oMiembroCuerpoTecnicoEntity) {
        return ResponseEntity.ok(oMiembroCuerpoTecnicoService.update(oMiembroCuerpoTecnicoEntity));
    }

    @DeleteMapping("/{id}") // Elimina un Miembro del Cuerpo Tecnico a partir de su id
    public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oMiembroCuerpoTecnicoService.delete(id));
    }

    @GetMapping("") // Muestra una cantidad concreta de Miembros del Cuerpo Tecnico
    public ResponseEntity<Page<MiembroCuerpoTecnicoEntity>> getPage(Pageable oPageable) {
        return ResponseEntity.ok(oMiembroCuerpoTecnicoService.getPage(oPageable));
    }

    @PostMapping("/populate/{amount}") // Crea una cantidad específica de Miembros del Cuerpo Tecnico tomando como
                                       // parámetro "amount"
    public ResponseEntity<Long> populate(@PathVariable("amount") Integer amount) {
        return ResponseEntity.ok(oMiembroCuerpoTecnicoService.populate(amount));
    }

    /*
     * @DeleteMapping("/empty") // Elimina todos los Miembros del Cuerpo Tecnico de
     * la base de datos
     * public ResponseEntity<Long> empty() {
     * return ResponseEntity.ok(oMiembroCuerpoTecnicoService.empty());
     * }
     */

}