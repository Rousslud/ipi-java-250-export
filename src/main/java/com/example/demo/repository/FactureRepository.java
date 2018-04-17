package com.example.demo.repository;

import com.example.demo.entity.Facture;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FactureRepository extends JpaRepository<Facture, Long> {
	@Query(value = "SELECT ID, DATECOMMANDE, CLIENT_ID FROM FACTURE WHERE CLIENT_ID= :client_id", nativeQuery = true)
	List<Facture> findByClientId(@Param("client_id") Long client_id);
}
