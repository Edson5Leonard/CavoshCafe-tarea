package com.example.demo.repositories;

import com.example.demo.entities.OpcionPersonalizacion;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface OpcionPersonalizacionRepository extends JpaRepository<OpcionPersonalizacion, Long> {
    List<OpcionPersonalizacion> findByTipoOpcion(String tipoOpcion);
}