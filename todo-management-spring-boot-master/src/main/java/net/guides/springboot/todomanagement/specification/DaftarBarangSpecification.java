package net.guides.springboot.todomanagement.specification;

import java.util.Optional;

import org.springframework.data.jpa.domain.Specification;

import net.guides.springboot.todomanagement.model.other.DaftarBarang;

public class DaftarBarangSpecification {
	
	public static Specification<DaftarBarang> merkLike(String merk) {
		return (dBarang, cq, cb) -> Optional.ofNullable(merk).map(m -> cb.like(dBarang.get("dbMerk"), "%" + m + "%")).orElse(null);
	}
	
	public static Specification<DaftarBarang> namaBarangLike(String namaBarang) {
        return (dBarang, cq, cb) -> Optional.ofNullable(namaBarang).map(nb -> cb.like(dBarang.get("dbNamaBarang"), "%" + nb + "%")).orElse(null);
    }
	
	public static Specification<DaftarBarang> ruanganLike(String ruangan) {
        return (dBarang, cq, cb) -> Optional.ofNullable(ruangan).map(r -> cb.like(dBarang.get("dbRuangan"), "%" + r + "%")).orElse(null);
    }
	
	public static Specification<DaftarBarang> perolehanLike(String perolehan) {
        return (dBarang, cq, cb) -> Optional.ofNullable(perolehan).map(p -> cb.like(dBarang.get("dbSumberDana"), "%" + p + "%")).orElse(null);
    }
}