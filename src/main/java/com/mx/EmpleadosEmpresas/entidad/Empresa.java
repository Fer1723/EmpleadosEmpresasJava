package com.mx.EmpleadosEmpresas.entidad;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="EMPRESAS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Empresa {
	@Id
	private String rfc;
	private String nombre;
	@Column (name = "PAI")
	private String pais;
	@Column(name = "ANIO_FUNDACION")
	private int anioFundacion;
	private double capital;
	private String tipo;
	
	@OneToMany(mappedBy = "empresa",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
	List<Empleado> empleados = new ArrayList<>();
}
