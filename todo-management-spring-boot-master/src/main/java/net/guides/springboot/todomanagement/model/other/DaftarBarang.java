package net.guides.springboot.todomanagement.model.other;

import java.util.Date;

public interface DaftarBarang {
	
	Long getIdBarang();
	String getNamaBarang();
	String getMerk();
	Long getJumlah();
	String getKondisi();
    String getNamaRuangan();
    String getSumberDana();
    Date getTanggalMaintain();
	
}