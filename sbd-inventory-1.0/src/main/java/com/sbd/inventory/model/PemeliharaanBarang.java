package com.sbd.inventory.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="pemeliharaan_barang")
public class PemeliharaanBarang {
	
	@Id
	@SequenceGenerator(allocationSize=1,initialValue=1,name="pemeliharaan_barang_seq",sequenceName="pemeliharaan_barang_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="pemeliharaan_barang_seq")
	private long idPemeliharaan;
	
	private Long idBarang;
	
	private Date tanggalPerbaikan;

	public long getIdPemeliharaan() {
		return idPemeliharaan;
	}

	public void setIdPemeliharaan(long idPemeliharaan) {
		this.idPemeliharaan = idPemeliharaan;
	}

	public Long getIdBarang() {
		return idBarang;
	}

	public void setIdBarang(Long idBarang) {
		this.idBarang = idBarang;
	}

	public Date getTanggalPerbaikan() {
		return tanggalPerbaikan;
	}

	public void setTanggalPerbaikan(Date tanggalPerbaikan) {
		this.tanggalPerbaikan = tanggalPerbaikan;
	}
	
}