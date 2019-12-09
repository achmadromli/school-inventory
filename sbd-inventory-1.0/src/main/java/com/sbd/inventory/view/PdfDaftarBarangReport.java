package com.sbd.inventory.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sbd.inventory.view.AbstractPdfView;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.sbd.inventory.model.other.DaftarBarang;

public class PdfDaftarBarangReport extends AbstractPdfView {

	//format untuk pdf yang di download
	@Override
	protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter writer,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		response.setHeader("Content-Disposition", "attachment; filename=\"daftar_barang.pdf\"");
		
		@SuppressWarnings("unchecked")
		List<DaftarBarang> list = (List<DaftarBarang>) model.get("daftarBarang");
		
		PdfPTable table = new PdfPTable(9);
		table.setWidthPercentage(100);
		table.setWidths(new int[] {5, 15, 15, 15, 15, 15, 15, 15, 15});
		
		Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
		headFont.setSize(11);
		
		PdfPCell hcell;
		hcell = new PdfPCell(new Phrase("No.", headFont));
		hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		hcell.setBackgroundColor(BaseColor.GRAY);
		table.addCell(hcell);
		
		hcell = new PdfPCell(new Phrase("Nama Barang", headFont));
		hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		hcell.setBackgroundColor(BaseColor.GRAY);
		table.addCell(hcell);
		
		hcell = new PdfPCell(new Phrase("Merk", headFont));
		hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		hcell.setBackgroundColor(BaseColor.GRAY);
		table.addCell(hcell);
		
		hcell = new PdfPCell(new Phrase("Jumlah Total", headFont));
		hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		hcell.setBackgroundColor(BaseColor.GRAY);
		table.addCell(hcell);
		
		hcell = new PdfPCell(new Phrase("Jumlah Sisa", headFont));
		hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		hcell.setBackgroundColor(BaseColor.GRAY);
		table.addCell(hcell);
		
		hcell = new PdfPCell(new Phrase("Kondisi", headFont));
		hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		hcell.setBackgroundColor(BaseColor.GRAY);
		table.addCell(hcell);
		
		hcell = new PdfPCell(new Phrase("Ruangan", headFont));
		hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		hcell.setBackgroundColor(BaseColor.GRAY);
		table.addCell(hcell);
		
		hcell = new PdfPCell(new Phrase("Sumber Dana", headFont));
		hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		hcell.setBackgroundColor(BaseColor.GRAY);
		table.addCell(hcell);
		
		hcell = new PdfPCell(new Phrase("Tanggal Maintain", headFont));
		hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
		hcell.setBackgroundColor(BaseColor.GRAY);
		table.addCell(hcell);
		
		Font cellFont = FontFactory.getFont(FontFactory.TIMES_ROMAN);
		cellFont.setSize(11);
		
		Long no = new Long(1);
		
		for (DaftarBarang daftarBarang : list) {
			
			PdfPCell cell;
			
            cell = new PdfPCell(new Phrase(no.toString()));
            cell.setPaddingLeft(5);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_CENTER);
            table.addCell(cell);
			
            cell = new PdfPCell(new Phrase(daftarBarang.getNamaBarang()));
            cell.setPaddingLeft(5);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase(daftarBarang.getMerk()));
            cell.setPaddingLeft(5);
            cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase(daftarBarang.getJumlahTotal().toString()));
	        cell.setPaddingLeft(5);
	        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase(daftarBarang.getJumlahSisa().toString()));
	        cell.setPaddingLeft(5);
	        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase(daftarBarang.getKondisi()));
	        cell.setPaddingLeft(5);
	        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase(daftarBarang.getNamaRuangan()));
	        cell.setPaddingLeft(5);
	        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase(daftarBarang.getSumberDana()));
	        cell.setPaddingLeft(5);
	        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(cell);
			
			cell = new PdfPCell(new Phrase(daftarBarang.getTanggalMaintain().toString()));
	        cell.setPaddingLeft(5);
	        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
	        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
			table.addCell(cell);
			
			no++;
		}
		document.add(table);
	}

}
