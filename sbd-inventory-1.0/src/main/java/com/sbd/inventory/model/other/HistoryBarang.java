package com.sbd.inventory.model.other;

import java.util.Date;

//kelas penampung untuk menampilkan record history secara detail
public interface HistoryBarang {

	String getNamaBarang();
	String getNamaRuangan();
	Long getJumlahPindah();
	String getKeterangan();
    Date getTanggalHistory();
	
}
