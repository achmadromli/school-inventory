package com.sbd.inventory.service;

import java.util.Date;

import java.util.List;
import java.util.Optional;

import com.sbd.inventory.model.Barang;
import com.sbd.inventory.model.other.DaftarBarang;
import com.sbd.inventory.model.other.StatusBarang;

public interface IBarangService {

	List<DaftarBarang> getBarangByAll(String namaBarang, String merk, String namaRuangan, String sumberDana);
		
	List<DaftarBarang> getBarangsByUser(String user);

	Optional<Barang> getBarangById(Long idBarang);
	
	String getNamaBarangById(Long idBarang);
	
	void updateBarang(Barang barang);

	void addBarang(String userName, String namaBarang, String merk, Long jumlahTotal, Long jumlahSisa, String kondisi, Long idRuangan, Long idPerolehan, Date tanggalMaintain, StatusBarang statusBarang);

	void deleteBarang(long idBarang);
	
	void saveBarang(Barang barang);

}