package net.guides.springboot.todomanagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="ruangan")
public class Ruangan {
	
	@Id
	@SequenceGenerator(allocationSize=1,initialValue=1,name="ruangan_seq",sequenceName="ruangan_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="ruangan_seq")
	private long idRuangan;
	
	private String namaRuangan;

	public long getIdRuangan() {
		return idRuangan;
	}

	public void setIdRuangan(long idRuangan) {
		this.idRuangan = idRuangan;
	}

	public String getNamaRuangan() {
		return namaRuangan;
	}

	public void setNamaRuangan(String namaRuangan) {
		this.namaRuangan = namaRuangan;
	}

}
