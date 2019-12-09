package com.sbd.inventory.model.other;

import java.util.Date;

public interface HistoryBarang {

	String getNamaBarang();
	String getNamaRuangan();
	Long getJumlahPindah();
	String getKeterangan();
    Date getTanggalHistory();
	
}
