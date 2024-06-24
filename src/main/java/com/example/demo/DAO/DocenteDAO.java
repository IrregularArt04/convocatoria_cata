package com.example.demo.DAO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="docente")
public class DocenteDAO {
	
	@Id
	@Column(name ="ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name = "tipo_documento",nullable=false, length=5)
	private String tipoDocumento;
	
	@Column(name = "numero_documento", nullable = false)
	private Integer numeroDocumento;
	
	@Column(name = "nombres", nullable=false, length=60)
	private String nombres;
	
	@Column(name = "apellidos", nullable=false, length=60)
	private String apellidos;
	
	@Column(name = "asignatura", nullable=false, length=50)
	private String asignatura;
}
