package com.sbd.inventory.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Document;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import com.sbd.inventory.model.other.DaftarBarang;

public class PdfDaftarBarangReport extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		response.setHeader("Content-Disposition", "attachment; filename=\"daftar_barang.pdf\"");
		
		@SuppressWarnings("unchecked")
		List<DaftarBarang> list = (List<DaftarBarang>) model.get("daftarBarang");
		Table table = new Table(9);
		table.addCell("No.");
		table.addCell("Nama Barang");
		table.addCell("Merk");
		table.addCell("Jumlah Total");
		table.addCell("Jumlah Sisa");
		table.addCell("Kondisi");
		table.addCell("Ruangan");
		table.addCell("Sumber Dana");
		table.addCell("Tanggal Maintain");
		
		int no = 1;
		for (DaftarBarang daftarBarang : list) {
			table.addCell(String.valueOf(no));
			table.addCell(daftarBarang.getNamaBarang());
			table.addCell(daftarBarang.getMerk());
			table.addCell(String.valueOf(daftarBarang.getJumlahTotal()));
			table.addCell(String.valueOf(daftarBarang.getJumlahSisa()));
			table.addCell(daftarBarang.getKondisi());
			table.addCell(daftarBarang.getNamaRuangan());
			table.addCell(daftarBarang.getSumberDana());
			table.addCell(String.valueOf(daftarBarang.getTanggalMaintain()));
			no++;
		}
		document.add(table);
	}

}
