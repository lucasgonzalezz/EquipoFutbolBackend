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
import org.springframework.http.ResponseEntity;
import net.ausiasmarch.equipo_futbol.entity.EquipoEntity;
import net.ausiasmarch.equipo_futbol.service.EquipoService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600) // Configura el comportamiento del CORS
@RestController // Marca la clase como un controlador REST
@RequestMapping("/equipo") // Ruta del controlador al recibir una solicitud HTTP
public class EquipoApi {

    @Autowired
    EquipoService oEquipoService; // Inyección de dependencias

    @GetMapping("{/id}") // Obtener un equipo mediante su id
    public ResponseEntity<EquipoEntity> get(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oEquipoService.get(id));
    }

    @PostMapping("") // Crea un nuevo equipo
    public ResponseEntity<Long> create(@RequestBody EquipoEntity oEquipoEntity) {
        return ResponseEntity.ok(oEquipoService.create(oEquipoEntity));
    }

    @PutMapping("")
    public ResponseEntity<EquipoEntity> update(@RequestBody EquipoEntity oEquipoEntity) {
        return ResponseEntity.ok(oEquipoService.update(oEquipoEntity));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Long> delete(@PathVariable("id") Long id) {
        return ResponseEntity.ok(oEquipoService.delete(id));
    }

    @GetMapping("")
    public ResponseEntity<Page<EquipoEntity>> getPage(Pageable oPageable) {
        return ResponseEntity.ok(oEquipoService.getPage(oPageable));
    }

    @PostMapping("/populate/{amount}")
    public ResponseEntity<Long> populate(@PathVariable("amount") Integer amount) {
        return ResponseEntity.ok(oEquipoService.populate(amount));
    }

}
