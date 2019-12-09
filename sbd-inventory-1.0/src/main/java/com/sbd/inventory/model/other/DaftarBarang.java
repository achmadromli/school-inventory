package com.sbd.inventory.model.other;

import java.util.Date;

//kelas penampung untuk record barang untuk menampilkan detail-detail info yang ada
public interface DaftarBarang {
	
	Long getIdBarang();
	String getNamaBarang();
	String getMerk();
	Long getJumlahTotal();
	Long getJumlahSisa();
	String getKondisi();
    String getNamaRuangan();
    String getSumberDana();
    Date getTanggalMaintain();
	
}