package com.sbd.inventory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.sbd.inventory.model.HistoryTracking;
import com.sbd.inventory.model.other.HistoryBarang;

public interface HistoryTrackingRepository extends JpaRepository<HistoryTracking, Long> {

	@Query(value = "select "
			+ "b.nama_barang as namaBarang, "
			+ "r.nama_ruangan as namaRuangan, "
			+ "ht.jumlah_pindah as jumlahPindah, "
			+ "ht.keterangan as keterangan, "
			+ "ht.tanggal_history as tanggalHistory "
			+ "from history_tracking ht "
			+ "left join barang b on ht.id_barang = b.id_barang "
			+ "left join ruangan r on ht.id_ruangan = r.id_ruangan "
			+ "order by ht.tanggal_history desc", nativeQuery = true)
	List<HistoryBarang> getListHistory();
}
