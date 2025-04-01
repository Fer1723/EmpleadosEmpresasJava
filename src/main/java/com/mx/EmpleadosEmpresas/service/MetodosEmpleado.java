package com.mx.EmpleadosEmpresas.service;

import java.util.List;

import com.mx.EmpleadosEmpresas.dtos.Respuesta;
import com.mx.EmpleadosEmpresas.entidad.Empleado;

public interface MetodosEmpleado {
	public Respuesta guardar(Empleado empleado);
	public Respuesta ediatr(Empleado empleado);
	public Respuesta eliminar(Empleado empleado);
	public Respuesta buscar(String curp);
	
	public List<Empleado> mostrar();

}
