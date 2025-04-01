package com.mx.EmpleadosEmpresas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mx.EmpleadosEmpresas.EmpleadosEmpresasApplication;
import com.mx.EmpleadosEmpresas.dao.EmpresaDao;
import com.mx.EmpleadosEmpresas.dtos.Respuesta;
import com.mx.EmpleadosEmpresas.entidad.Empresa;

@Service
public class EmpresaService implements MetodosEmpresa{
	@Autowired
	EmpresaDao dao;
	@Override
	public Respuesta guardar(Empresa empresa) {
		Respuesta rs = new Respuesta();
		if(dao.existsById(empresa.getRfc())) {
			rs.setMensaje("La empresa no se agrego por que ya existe el RFC");
			rs.setSuccess(false);
			rs.setObj(empresa.getRfc());
			return rs;
		}
		for(Empresa e: dao.findAll()) {
			if(empresa.getNombre().equalsIgnoreCase(e.getNombre())) {
				rs.setMensaje("La empresa no se agrego porque ya existe");
				rs.setSuccess(false);
				rs.setObj(empresa.getNombre());
				return rs;
			}
		}
		empresa.setRfc(empresa.getRfc().toUpperCase());
		empresa.setNombre(empresa.getNombre().toUpperCase());
		empresa.setPais(empresa.getPais().toUpperCase());
		empresa.setTipo(empresa.getTipo().toUpperCase());
		dao.save(empresa);
		rs.setMensaje("La empresa ha sido registrada");
		rs.setSuccess(true);
		rs.setObj(empresa);
		return rs;
	}

	@Override
	public Respuesta editar(Empresa empresa) {
		Respuesta rs = new Respuesta();
		Empresa empresa_aux = dao.findById(empresa.getRfc()).orElse(null);
		if (empresa_aux == null) {
			rs.setMensaje("La empresa que tratas de editar no existe");
			rs.setSuccess(false);
			rs.setObj(empresa.getRfc());
			return rs;
		}
		empresa = convertirMayusculas(empresa);
		empresa_aux.setNombre(empresa.getNombre());
		empresa_aux.setPais(empresa.getPais());
		empresa_aux.setAnioFundacion(empresa.getAnioFundacion());
		empresa_aux.setCapital(empresa.getCapital());
		empresa_aux.setTipo(empresa.getTipo());
		dao.save(empresa_aux);
		rs.setMensaje("LA empresa ha sido editada");
		rs.setSuccess(true);
		rs.setObj(empresa_aux);
		return rs;
	}

	@Override
	public Respuesta eliminar(Empresa empresa) {
		Respuesta rs = new Respuesta();
		String rfc = empresa.getRfc();
		empresa = dao.findById(empresa.getRfc()).orElse(null);
		if(empresa != null) {
			if(empresa.getEmpleados().isEmpty()) {
				dao.delete(empresa);
				rs.setMensaje("La empresa ha sido eliminada");
				rs.setSuccess(true);
				rs.setObj(empresa);
				return rs;
			}
			rs.setMensaje("La empresa aun tiene empleados y por eso no puede ser eliminda");
			rs.setSuccess(false);
			rs.setObj(empresa.getEmpleados());
			return rs;
			
		}
		rs.setMensaje("La empresa que tratas de eliminar no existe");
		rs.setSuccess(false);
		rs.setObj(rfc);
		return rs;
	}

	@Override
	public Respuesta buscar(String rfc) {
		Respuesta rs = new Respuesta();
		Empresa empresa = dao.findById(rfc).orElse(null);
		if(empresa == null) {
			rs.setMensaje("LA empresa que tratas de busacr no existe");
			rs.setSuccess(false);
			rs.setObj(rfc);
			return rs;
		}
		rs.setMensaje("La empresa ha sido encontrada");
		rs.setSuccess(true);
		rs.setObj(empresa);
		return rs;
		
	}

	@Override
	public List<Empresa> mostrar() {
		return dao.findAll();
	}
	
	
	public Empresa convertirMayusculas(Empresa empresa) {
		empresa.setRfc(empresa.getRfc().toUpperCase());
		empresa.setNombre(empresa.getNombre().toUpperCase());
		empresa.setPais(empresa.getPais().toUpperCase());
		empresa.setTipo(empresa.getTipo().toUpperCase());
		return empresa;
	}

}
