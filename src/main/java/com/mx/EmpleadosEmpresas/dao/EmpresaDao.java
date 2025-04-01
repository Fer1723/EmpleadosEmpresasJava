package com.mx.EmpleadosEmpresas.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.mx.EmpleadosEmpresas.entidad.Empresa;

public interface EmpresaDao extends JpaRepository<Empresa, String>{
	
}
