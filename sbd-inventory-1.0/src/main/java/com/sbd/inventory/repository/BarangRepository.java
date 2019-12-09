package com.sbd.inventory.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.sbd.inventory.model.Barang;
import com.sbd.inventory.model.other.DaftarBarang;

public interface BarangRepository extends JpaRepository<Barang, Long>, JpaSpecificationExecutor<DaftarBarang> {
	
	@Query(value = "select "
			+ "b.id_barang as idBarang, "
			+ "b.nama_barang as namaBarang, "
			+ "b.merk as merk, "
			+ "b.jumlah_sisa as jumlahSisa, "
			+ "b.jumlah_total as jumlahTotal, "
			+ "b.kondisi as kondisi, "
			+ "r.nama_ruangan as namaRuangan, "
			+ "pb.sumber_dana as sumberDana, "
			+ "b.tanggal_maintain as tanggalMaintain "
			+ "from barang b "
			+ "left join ruangan r on b.id_ruangan = r.id_ruangan "
			+ "left join perolehan_barang pb on b.id_perolehan = pb.id_perolehan "
			+ "where b.user_name = :userName ", nativeQuery = true)
	List<DaftarBarang> findByUserName(String userName);
	
	@Query(value = "select "
			+ "nama_barang "
			+ "from barang "
			+ "where id_barang = :idBarang ", nativeQuery = true)
	String getNamaBarangById(Long idBarang);
	
	/*
	 * @Query("select " + "e " + "from Employee e " +
	 * "where lower(e.firstName) like lower(concat('%', :search, '%')) " +
	 * "or lower(e.lastName) like lower(concat('%', :search, '%'))")
	 * List<DaftarBarang> findByFirstNameOrLastName(@Param("search") String search);
	 */
}
