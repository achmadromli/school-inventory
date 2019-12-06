package net.guides.springboot.todomanagement.service;

import java.util.Date;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import net.guides.springboot.todomanagement.model.Barang;
import net.guides.springboot.todomanagement.model.other.DaftarBarang;

public interface IBarangService {

	List<DaftarBarang> getBarangByAll(EntityManager em, String namaBarang, String merk, String namaRuangan, String sumberDana);
	
	List<DaftarBarang> findDaftarBarangSpecific(String merk, String namaBarang, String ruangan, String perolehan);
	
	List<DaftarBarang> getBarangsByUser(String user);

	Optional<Barang> getBarangById(Long idBarang);
	
	void updateBarang(Barang barang);

	void addBarang(String userName, String namaBarang, String merk, Long jumlah, String kondisi, Long idRuangan, Long idPerolehan, Date tanggalMaintain);

	void deleteBarang(long idBarang);
	
	void saveBarang(Barang barang);

}