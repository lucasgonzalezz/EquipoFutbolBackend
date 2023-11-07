package net.ausiasmarch.equipo_futbol.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.servlet.http.HttpServletRequest;
import net.ausiasmarch.equipo_futbol.bean.EquipoBean;
import net.ausiasmarch.equipo_futbol.helper.JWTHelper;
import net.ausiasmarch.equipo_futbol.entity.EquipoEntity;
import net.ausiasmarch.equipo_futbol.repository.EquipoRepository;
import net.ausiasmarch.equipo_futbol.exception.ResourceNotFoundException;

@Service
public class SessionService {
 
    @Autowired
    EquipoRepository oEquipoRepository;

    @Autowired
    HttpServletRequest oHttpServletRequest;

    public String login(EquipoBean oEquipoBean) {
        oEquipoRepository.findByUsernameAndPassword(oEquipoBean.getUsername(), oEquipoBean.getPassword())
                .orElseThrow(() -> new ResourceNotFoundException("Wrong Equipo or password"));
        return JWTHelper.generateJWT(oEquipoBean.getUsername());
    }

    public EquipoEntity getSessionEquipo() {
        String strJWTusername = oHttpServletRequest.getAttribute("username").toString();
        return oEquipoRepository.findByUsername(strJWTusername)
                .orElseThrow(() -> new ResourceNotFoundException("Equipo not found"));
    }

    public Boolean isSessionActive() {
        String strJWTusername = oHttpServletRequest.getAttribute("username").toString();
        return oEquipoRepository.findByUsername(strJWTusername).isPresent();
    }

    public Boolean isAdmin() {
        String strJWTusername = oHttpServletRequest.getAttribute("username").toString();
        EquipoEntity oEquipoEntityInSession = oEquipoRepository.findByUsername(strJWTusername)
                .orElseThrow(() -> new ResourceNotFoundException("Equipo not found"));
        return Boolean.FALSE.equals(oEquipoEntityInSession.getRole());
    }

    public Boolean isEquipo() {
        String strJWTusername = oHttpServletRequest.getAttribute("username").toString();
        EquipoEntity oEquipoEntityInSession = oEquipoRepository.findByUsername(strJWTusername)
                .orElseThrow(() -> new ResourceNotFoundException("Equipo not found"));
        return Boolean.TRUE.equals(oEquipoEntityInSession.getRole());
    }

    public void onlyAdmins() {
        if (!this.isAdmin()) {
            throw new ResourceNotFoundException("Only admins can do this");
        }
    }

    public void onlyEquipos() {
        if (!this.isEquipo()) {
            throw new ResourceNotFoundException("Only users can do this");
        }
    }

    public void onlyAdminsOrEquipos() {
        if (!this.isSessionActive()) {
            throw new ResourceNotFoundException("Only admins or users can do this");
        }
    }

    public void onlyEquiposWithIisOwnData(Long id_equipo) {
        if (!this.isEquipo()) {
            throw new ResourceNotFoundException("Only users can do this");
        }
        if (!this.getSessionEquipo().getId().equals(id_equipo)) {
            throw new ResourceNotFoundException("Only users can do this");
        }
    }

    public void onlyAdminsOrEquiposWithIisOwnData(Long id_equipo) {
        if (this.isSessionActive()) {
            if (!this.isAdmin()) {
                if (!this.isEquipo()) {
                    throw new ResourceNotFoundException("Only admins or users can do this");
                } else {
                    if (!this.getSessionEquipo().getId().equals(id_equipo)) {
                        throw new ResourceNotFoundException("Only admins or users with its own data can do this");
                    }
                }
            }
        } else {
            throw new ResourceNotFoundException("Only admins or users can do this");
        }
    }

}
