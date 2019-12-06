package net.guides.springboot.todomanagement.model.other;

import java.util.Date;

public interface HistoryBarang {

	String getNamaBarang();
	String getNamaRuangan();
	Long getJumlahPindah();
	String getKeterangan();
    Date getTanggalHistory();
	
}
