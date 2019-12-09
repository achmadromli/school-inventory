package com.sbd.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sbd.inventory.model.Ruangan;

public interface RuanganRepository extends JpaRepository<Ruangan, Long> {

	@Query(value = "select nama_ruangan from ruangan where id_ruangan = :idRuangan", nativeQuery = true)
	String getNamaRuanganById(Long idRuangan);
}
