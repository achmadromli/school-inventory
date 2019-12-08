package com.sbd.inventory.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="history_tracking")
public class HistoryTracking {
	
	@Id
	@SequenceGenerator(allocationSize=1,initialValue=1,name="history_tracking_seq",sequenceName="history_tracking_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="history_tracking_seq")
	private long idTrack;
	
	private Long idRuangan;
	
	private Long idBarang;
	
	private String keterangan;
	
	private Date tanggalHistory;

	private Long jumlahPindah;
	
	private Long jumlahBarang;
	
	public Long getIdRuangan() {
		return idRuangan;
	}

	public void setIdRuangan(Long idRuangan) {
		this.idRuangan = idRuangan;
	}
	
	public Long getIdBarang() {
		return idBarang;
	}

	public void setIdBarang(Long idBarang) {
		this.idBarang = idBarang;
	}

	public String getKeterangan() {
		return keterangan;
	}

	public void setKeterangan(String keterangan) {
		this.keterangan = keterangan;
	}

	public Date getTanggalHistory() {
		return tanggalHistory;
	}

	public void setTanggalHistory(Date tanggalHistory) {
		this.tanggalHistory = tanggalHistory;
	}

	public long getIdTrack() {
		return idTrack;
	}

	public void setIdTrack(long idTrack) {
		this.idTrack = idTrack;
	}

	public Long getJumlahPindah() {
		return jumlahPindah;
	}

	public void setJumlahPindah(Long jumlahPindah) {
		this.jumlahPindah = jumlahPindah;
	}

	public Long getJumlahBarang() {
		return jumlahBarang;
	}

	public void setJumlahBarang(Long jumlahBarang) {
		this.jumlahBarang = jumlahBarang;
	}
	
}