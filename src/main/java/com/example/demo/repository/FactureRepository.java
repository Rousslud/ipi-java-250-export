package com.example.demo.repository;

import com.example.demo.dto.FactureDTO;
import com.example.demo.entity.Facture;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FactureRepository extends JpaRepository<Facture, Long> {
	/*@Query("SELECT ID, DATECOMMANDE, CLIENT_ID FROM FACTURE WHERE CLIENT_ID= :client_id")
	List<FactureDTO> findByClientId(@Param("client_id") Long client_id);*/
}
