package net.guides.springboot.todomanagement.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import net.guides.springboot.todomanagement.model.Barang;
import net.guides.springboot.todomanagement.model.other.DaftarBarang;
import net.guides.springboot.todomanagement.repository.BarangRepository;
import net.guides.springboot.todomanagement.specification.DaftarBarangSpecification;

@Service
public class BarangService implements IBarangService {

	@Autowired
	private BarangRepository barangRepository;
	
	public List<DaftarBarang> getBarangByAll(EntityManager em, String namaBarang, String merk, String namaRuangan, String sumberDana) {
		String sql = "";
		
		if (namaBarang!=null && namaBarang.length()>0) sql = sql + "and b.nama_barang ilike :namaBarang ";
		if (merk!=null && merk.length()>0) sql = sql + "and b.merk ilike :merk ";
		if (namaRuangan!=null && namaRuangan.length()>0) sql = sql + "r.nama_ruangan ilike :namaRuangan ";
		if (sumberDana!=null && sumberDana.length()>0) sql = sql + "pb.sumber_dana ilike :sumberDana ";
		
		String sqll = "select "
				+ "from barang b "
				+ "left join ruangan r on b.id_ruangan = r.id_ruangan "
				+ "left join perolehan_barang pb on b.id_perolehan = pb.id_perolehan "
				+ "where 1=1 " + sql;
		
	    TypedQuery<DaftarBarang> query = em.createQuery(sqll, DaftarBarang.class);
	    return query.getResultList();
	} 
	
	@Override
	public List<DaftarBarang> findDaftarBarangSpecific(String merk, String namaBarang, String ruangan, String perolehan) {
		return barangRepository.findAll(Specification.where(DaftarBarangSpecification.merkLike(merk)).and(DaftarBarangSpecification.namaBarangLike(namaBarang)).and(DaftarBarangSpecification.ruanganLike(ruangan)).and(DaftarBarangSpecification.perolehanLike(perolehan)));
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
	public void addBarang(String userName, String namaBarang, String merk, Long jumlah, String kondisi, Long idRuangan, Long idPerolehan, Date tanggalMaintain) {
		barangRepository.save(new Barang(userName, namaBarang, merk, jumlah, kondisi, idRuangan, idPerolehan, tanggalMaintain));
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