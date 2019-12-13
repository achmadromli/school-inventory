package com.sbd.inventory.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbd.inventory.model.Barang;
import com.sbd.inventory.model.other.DaftarBarang;
import com.sbd.inventory.model.other.StatusBarang;
import com.sbd.inventory.repository.BarangRepository;

@Service
public class BarangService implements IBarangService {

	@Autowired
	private BarangRepository barangRepository;
	
	@Override
	public String getNamaBarangById(Long idBarang) {
		return barangRepository.getNamaBarangById(idBarang);
	}
	
	public List<DaftarBarang> getBarangByAll(String merk, String namaBarang, String namaRuangan, String sumberDana) {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("com.sbd.inventory.model.other");
		EntityManager em = entityManagerFactory.createEntityManager();
		
		String sql = "";
				
		if (namaBarang!=null && namaBarang.length()>0) sql = sql + "and b.nama_barang ilike :namaBarang ";
		if (merk!=null && merk.length()>0) sql = sql + "and b.merk ilike :merk ";
		if (namaRuangan!=null && namaRuangan.length()>0) sql = sql + "r.nama_ruangan ilike :namaRuangan ";
		if (sumberDana!=null && sumberDana.length()>0) sql = sql + "pb.sumber_dana ilike :sumberDana ";
		
		String sqll = "select "
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
				+ "where 1=1 " + sql;
		
		TypedQuery<DaftarBarang> query = em.createQuery(sqll, DaftarBarang.class);
		
		return query.getResultList();
	} 
	
	@Override
	public List<DaftarBarang> getBarangsByUser(String user) {
		return barangRepository.findByUserName(user);
	}

	@Override
	public Optional<Barang> getBarangById(Long idBarang) {
		return barangRepository.findById(idBarang);
	}

	@Override
	public void updateBarang(Barang barang) {
		barangRepository.save(barang);
	}

	@Override
	public void addBarang(String userName, String namaBarang, String merk, Long jumlahTotal, Long jumlahSisa, String kondisi, Long idRuangan, Long idPerolehan, Date tanggalMaintain, StatusBarang statusBarang) {
		barangRepository.save(new Barang(userName, namaBarang, merk, jumlahTotal, jumlahSisa, kondisi, idRuangan, idPerolehan, tanggalMaintain, statusBarang));
	}

	@Override
	public void deleteBarang(long idBarang) {
		Optional<Barang> barang = barangRepository.findById(idBarang);
		if (barang.isPresent()) {
			barangRepository.delete(barang.get());
		}
	}

	@Override
	public void saveBarang(Barang barang) {
		barangRepository.save(barang);
	}
}