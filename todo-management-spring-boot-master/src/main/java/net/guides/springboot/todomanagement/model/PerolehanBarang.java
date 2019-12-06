package net.guides.springboot.todomanagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="perolehan_barang")
public class PerolehanBarang {

	@Id
	@SequenceGenerator(allocationSize=1,initialValue=1,name="perolehan_barang_seq",sequenceName="perolehan_barang_seq")
	@GeneratedValue(strategy = GenerationType.SEQUENCE,generator="perolehan_barang_seq")
	private long idPerolehan;
	
	private String sumberDana;

	public long getIdPerolehan() {
		return idPerolehan;
	}

	public void setIdPerolehan(long idPerolehan) {
		this.idPerolehan = idPerolehan;
	}

	public String getSumberDana() {
		return sumberDana;
	}

	public void setSumberDana(String sumberDana) {
		this.sumberDana = sumberDana;
	}
	
}
