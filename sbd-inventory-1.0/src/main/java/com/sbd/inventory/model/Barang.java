package com.sbd.inventory.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import com.sbd.inventory.model.other.StatusBarang;

@Entity
@Table(name="barang")
public class Barang {

	@Id
	@SequenceGenerator(allocationSize=1,initialValue=1,name="barang_seq",sequenceName="barang_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="barang_seq")
	private long idBarang;

	private String userName;
	
	@Size(min = 2, message = "Enter at least 2 Characters...")
	private String namaBarang;
	
	private String merk;
	
	private Long jumlahTotal;
	
	private Long jumlahSisa;
	
	private String kondisi;
	
	private Date tanggalMaintain;
	
	private Long idRuangan;
	
	private Long idPerolehan;
	
	@Enumerated(EnumType.STRING)
	private StatusBarang statusBarang;
	
	public Barang() {
		super();
	}

	public Barang(String userName, String namaBarang, String merk, Long jumlahTotal, Long jumlahSisa, String kondisi, Long idRuangan, Long idPerolehan, Date tanggalMaintain, StatusBarang statusBarang) {
		super();
		this.userName = userName;
		this.namaBarang = namaBarang;
		this.merk = merk;
		this.jumlahTotal = jumlahTotal;
		this.jumlahSisa = jumlahSisa;
		this.kondisi = kondisi;
		this.statusBarang = statusBarang;
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

	public Long getJumlahTotal() {
		return jumlahTotal;
	}

	public void setJumlahTotal(Long jumlahTotal) {
		this.jumlahTotal = jumlahTotal;
	}

	public Long getJumlahSisa() {
		return jumlahSisa;
	}

	public void setJumlahSisa(Long jumlahSisa) {
		this.jumlahSisa = jumlahSisa;
	}

	public StatusBarang getStatusBarang() {
		return statusBarang;
	}

	public void setStatusBarang(StatusBarang statusBarang) {
		this.statusBarang = statusBarang;
	}
	
}