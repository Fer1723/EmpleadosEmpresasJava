package com.mx.EmpleadosEmpresas.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mx.EmpleadosEmpresas.entidad.Empleado;

public interface EmpleadoDao extends JpaRepository<Empleado, String> {

}
