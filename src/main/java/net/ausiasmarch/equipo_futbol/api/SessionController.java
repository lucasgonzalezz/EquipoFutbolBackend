package net.ausiasmarch.equipo_futbol.api;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.ResponseEntity;
import net.ausiasmarch.equipo_futbol.bean.EquipoBean;
import net.ausiasmarch.equipo_futbol.service.SessionService;

@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600) // Configura el comportamiento del CORS
@RestController // Marca la clase como un controlador REST
@RequestMapping("/session") // Ruta del controlador al recibir una solicitud HTTP
public class SessionController {

    @Autowired
    SessionService oSessionService;

    @PostMapping()
    public ResponseEntity<String> login(@RequestBody EquipoBean oEquipoBean) {
        return ResponseEntity.ok("\"" + oSessionService.login(oEquipoBean) + "\"");
    }
    
}