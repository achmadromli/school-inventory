package net.guides.springboot.todomanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import net.guides.springboot.todomanagement.model.Ruangan;

public interface RuanganRepository extends JpaRepository<Ruangan, Long> {

	@Query(value = "select nama_ruangan from ruangan where id_ruangan = :idRuangan", nativeQuery = true)
	String getNamaRuanganById(Long idRuangan);
}
