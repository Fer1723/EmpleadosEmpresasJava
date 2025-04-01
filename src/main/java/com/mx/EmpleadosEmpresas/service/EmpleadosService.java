package com.mx.EmpleadosEmpresas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.EmpleadosEmpresas.dao.EmpleadoDao;
import com.mx.EmpleadosEmpresas.dtos.Respuesta;
import com.mx.EmpleadosEmpresas.entidad.Empleado;
import com.mx.EmpleadosEmpresas.entidad.Empresa;

@Service
public class EmpleadosService implements MetodosEmpleado{
	@Autowired
	EmpleadoDao dao;
	

	@Override
	public Respuesta guardar(Empleado empleado) {
		Respuesta rs = new Respuesta();
		if(dao.existsById(empleado.getCurp())) {
			rs.setMensaje("El empleado no se registro porque ya existe");
			rs.setSuccess(false);
			rs.setObj(empleado.getCurp());
			return rs;
		}
		for (Empleado e : dao.findAll()) {
			if(empleado.getNombre().equalsIgnoreCase(e.getNombre()) && 
			   empleado.getApellido().equalsIgnoreCase(e.getApellido())) {
				rs.setMensaje("El empleado no se registro porque ya existe");
				rs.setSuccess(false);
				rs.setObj(e);
				return rs;
			}
		}
		empleado = convertirMayusculas(empleado);
		dao.save(empleado);
		rs.setMensaje("El empleado ha sido agregado a la base de datos");
		rs.setSuccess(true);
		rs.setObj(empleado);
		return rs;
	}

	@Override
	public Respuesta ediatr(Empleado empleado) {
		Respuesta rs = new Respuesta();
		if(dao.existsById(empleado.getCurp())) {
			dao.save(empleado);
			rs.setMensaje("El empleado ha sido editado");
			rs.setSuccess(true);
			rs.setObj(empleado);
			return rs;
		}
		rs.setMensaje("El empleado  que tartas de editar no existe");
		rs.setSuccess(false);
		rs.setObj(empleado.getCurp());
		return rs;

	}

	@Override
	public Respuesta eliminar(Empleado empleado) {
		Respuesta rs = new Respuesta();
		String curp = empleado.getCurp();
		empleado = dao.findById(empleado.getCurp()).orElse(null);
		if(empleado == null) {
			rs.setMensaje("El empleado  que tartas de eliminar no existe");
			rs.setSuccess(false);
			rs.setObj(curp);
			return rs;
		}
		if(empleado.getEmpresa() != null) {
			empleado.getEmpresa().getEmpleados().remove(empleado);
			empleado.setEmpresa(null);
		}
		rs.setObj(empleado);
		dao.delete(empleado);
		rs.setMensaje("El empleado ha sido eliminado");
		rs.setSuccess(true);
		return rs;
		
	}

	@Override
	public Respuesta buscar(String curp) {
		Respuesta rs = new Respuesta();
		Empleado empleado = dao.findById(curp).orElseGet(null);
		if(empleado == null) {
			rs.setMensaje("El empleado que tratas de buscar no existe");
			rs.setSuccess(false);
			rs.setObj(null);
		}else {
			rs.setMensaje("El empleado ha sido encontrado");
			rs.setSuccess(true);
			rs.setObj(empleado);
		}
		return rs;
	}

	@Override
	public List<Empleado> mostrar() {
		return dao.findAll();
	}
	
	public Empleado convertirMayusculas(Empleado empleado) {
		empleado.setCurp(empleado.getCurp().toUpperCase());
		empleado.setNombre(empleado.getNombre().toUpperCase());
		empleado.setApellido(empleado.getApellido().toUpperCase());
		empleado.setGenero(empleado.getGenero().toUpperCase());
		empleado.setEstadoCivil(empleado.getEstadoCivil().toUpperCase());
		empleado.setCiudad(empleado.getCiudad().toUpperCase());
		return empleado;
	}
	
}
