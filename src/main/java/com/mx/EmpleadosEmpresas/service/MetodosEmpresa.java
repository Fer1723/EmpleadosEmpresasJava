package com.mx.EmpleadosEmpresas.service;

import java.util.List;

import com.mx.EmpleadosEmpresas.dtos.Respuesta;
import com.mx.EmpleadosEmpresas.entidad.Empresa;

public interface MetodosEmpresa {
	public Respuesta guardar(Empresa empresa);
	public Respuesta editar(Empresa empresa);
	public Respuesta eliminar(Empresa empresa);
	public Respuesta buscar(String rfc);
	
	public List<Empresa> mostrar();

}
