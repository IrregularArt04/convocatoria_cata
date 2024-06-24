package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.DAO.DocenteDAO;

@Repository
public interface DocenteRepository extends JpaRepository<DocenteDAO,Integer>{
	
	@Query(nativeQuery = true, value = "SELECT * FROM docente d WHERE numero_documento = ?1 and tipo_documento = ?2 LIMIT 1")
	DocenteDAO findByDocum(Integer id, String tipoDoc);
}
