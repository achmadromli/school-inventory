package com.sbd.inventory.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="barang")
public class Barang {

	@Id
	@SequenceGenerator(allocationSize=1,initialValue=1,name="barang_seq",sequenceName="barang_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="barang_seq")
	private long idBarang;

	private String userName;
	
	@Size(min = 2, message = "Enter at least 10 Characters...")
	private String namaBarang;
	
	private String merk;
	
	private Long jumlah;
	
	private String kondisi;
	
	private Date tanggalMaintain;
	
	private Long idRuangan;
	
	private Long idPerolehan;
	
	public Barang() {
		super();
	}

	public Barang(String userName, String namaBarang, String merk, Long jumlah, String kondisi, Long idRuangan, Long idPerolehan, Date tanggalMaintain) {
		super();
		this.userName = userName;
		this.namaBarang = namaBarang;
		this.merk = merk;
		this.jumlah = jumlah;
		this.kondisi = kondisi;
		this.idRuangan = idRuangan;
		this.idPerolehan = idPerolehan;
		this.tanggalMaintain = tanggalMaintain;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public long getIdBarang() {
		return idBarang;
	}

	public void setIdBarang(long idBarang) {
		this.idBarang = idBarang;
	}

	public String getNamaBarang() {
		return namaBarang;
	}

	public void setNamaBarang(String namaBarang) {
		this.namaBarang = namaBarang;
	}

	public Long getJumlah() {
		return jumlah;
	}

	public void setJumlah(Long jumlah) {
		this.jumlah = jumlah;
	}

	public String getKondisi() {
		return kondisi;
	}

	public void setKondisi(String kondisi) {
		this.kondisi = kondisi;
	}

	public Date getTanggalMaintain() {
		return tanggalMaintain;
	}

	public void setTanggalMaintain(Date tanggalMaintain) {
		this.tanggalMaintain = tanggalMaintain;
	}

	public String getMerk() {
		return merk;
	}

	public void setMerk(String merk) {
		this.merk = merk;
	}

	public Long getIdRuangan() {
		return idRuangan;
	}

	public void setIdRuangan(Long idRuangan) {
		this.idRuangan = idRuangan;
	}

	public Long getIdPerolehan() {
		return idPerolehan;
	}

	public void setIdPerolehan(Long idPerolehan) {
		this.idPerolehan = idPerolehan;
	}
	
}