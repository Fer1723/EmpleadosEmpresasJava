package com.mx.EmpleadosEmpresas.entidad;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table (name = "EMPLEADOS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Empleado {
	@Id
	private String curp;
	@Column(name = "NOMBRE_EMPLEADO")
	private String nombre;
	private String apellido;
	private int edad;
	private String genero;
	@Column(name = "ESTADO_CIVIL")
	private String estadoCivil;
	private String ciudad;
	private double sueldo;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "RFC_EMPRESA")
	Empresa empresa;

}
