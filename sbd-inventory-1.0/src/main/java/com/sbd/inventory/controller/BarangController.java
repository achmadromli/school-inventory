package com.sbd.inventory.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.sbd.inventory.model.Barang;
import com.sbd.inventory.model.HistoryTracking;
import com.sbd.inventory.model.PerolehanBarang;
import com.sbd.inventory.model.Ruangan;
import com.sbd.inventory.model.other.DaftarBarang;
import com.sbd.inventory.model.other.StatusBarang;
import com.sbd.inventory.repository.HistoryTrackingRepository;
import com.sbd.inventory.repository.PerolehanBarangRepository;
import com.sbd.inventory.repository.RuanganRepository;
import com.sbd.inventory.service.IBarangService;
import com.sbd.inventory.view.PdfDaftarBarangReport;

@Controller
public class BarangController {

	@Autowired
	private IBarangService barangService;
	
	@Autowired
	private RuanganRepository ruanganRepository;
	
	@Autowired
	private PerolehanBarangRepository perolehanBarangRepository;
	
	@Autowired
	private HistoryTrackingRepository historyTrackingRepository;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		// Date - dd/MM/yyyy
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
	}
	
	//untuk tampilahan awal di record barang
	@RequestMapping(value = "/list-barangs", method = RequestMethod.GET)
	public String showBarangs(ModelMap model) {
		String name = getLoggedInUserName(model);
		
		model.put("barangs", barangService.getBarangsByUser(name));
		
		return "list-barangs";
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String searchBarang(ModelMap model, HttpServletRequest request) {
		List<DaftarBarang> list = barangService.getBarangByAll(request.getParameter("merkX"), request.getParameter("namaBarangX"), request.getParameter("ruanganX"), request.getParameter("perolehanX"));
		model.put("daftarBarang", list);
		return "list-barangs";
	}
	
	private String getLoggedInUserName(ModelMap model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}

		return principal.toString();
	}

	//untuk menampilkan menu-menu barang yang akan ditambahkan
	@RequestMapping(value = "/add-barang", method = RequestMethod.GET)
	public String showAddBarangPage(ModelMap model) {
		Vector<String> kondisi = new Vector<>();
		if (kondisi.size()==0) {
			kondisi.add("Baik");
			kondisi.add("Rusak");
			kondisi.add("Kadaluarsa");
			kondisi.add("Baru");
		}
		model.put("kondisi", kondisi);
		
		Vector<String> statusBarangs = new Vector<>();
		if (statusBarangs.size()==0) {
			StatusBarang[] status = StatusBarang.values();
			for (int i=0; i<status.length; i++) {
				statusBarangs.add(status[i].name());
			}
		}
		model.put("statusBarangs", statusBarangs);
		
		List<Ruangan> ruangan = ruanganRepository.findAll();
		model.put("ruangan", ruangan);
		
		List<PerolehanBarang> perolehanBarang = perolehanBarangRepository.findAll();
		model.put("perolehanBarang", perolehanBarang);
		
		model.addAttribute("barang", new Barang());
		return "barang";
	}

	//untuk menghapus record barang yang ada
	@RequestMapping(value = "/delete-barang", method = RequestMethod.GET)
	public String deleteBarang(@RequestParam Long idBarang) {
		barangService.deleteBarang(idBarang);
		return "redirect:/list-barangs";
	}

	//untuk menampilkan data-data barang yang hendak dipindahkan
	@RequestMapping(value = "/update-barang", method = RequestMethod.GET)
	public String showUpdateBarangPage(@RequestParam Long idBarang, ModelMap model) {
		Barang barang = barangService.getBarangById(idBarang).get();
		model.addAttribute("idBarang", idBarang);
		
		Vector<String> kondisi = new Vector<>();
		if (kondisi.size()==0) {
			kondisi.add("Baik");
			kondisi.add("Rusak");
			kondisi.add("Kadaluarsa");
			kondisi.add("Baru");
		}
		model.put("kondisi", kondisi);
		
		Vector<String> statusBarangs = new Vector<>();
		if (statusBarangs.size()==0) {
			StatusBarang[] status = StatusBarang.values();
			for (int i=0; i<status.length; i++) {
				statusBarangs.add(status[i].name());
			}
		}
		model.put("statusBarangs", statusBarangs);
		
		List<Ruangan> ruangan = ruanganRepository.findAll();
		model.put("ruangan", ruangan);
		
		List<PerolehanBarang> perolehanBarang = perolehanBarangRepository.findAll();
		model.put("perolehanBarang", perolehanBarang);
		
		model.put("barang", barang);
		return "barang";
	}

	//untuk pemindahan barang
	@RequestMapping(value = "/update-barang", method = RequestMethod.POST)
	public String updateBarang(ModelMap model, @RequestParam Long idBarang, HttpServletRequest request) {
		//penanda untuk barang dipindah atau tidak (dibagi menjadi dua jenis, dipindah semua atau sebagian)
		boolean pindah = false;
		boolean pindahSemua = false;
		
		Barang barangClone = new Barang();
		Barang barangAwal = barangService.getBarangById(idBarang).get();
		
		//check apakah sudah pilih ruangan baru
		//jika tidak pilih ruangan baru, maka akan muncul pesan error bahwa pesan belum dipilih
		if (barangAwal.getIdRuangan()!=Long.parseLong(request.getParameter("idRuangan"))) {
			
			//cek apakah jumlah yang dipindahkan barang yang ada
			//kalau lebih akan diberi pesan jumlah barang yang akan dipindah melebihi barang yang ada
			if (barangAwal.getJumlahSisa()-Long.parseLong(request.getParameter("jumlahSisa"))>0) {
				pindah = true;
	        } else if (barangAwal.getJumlahSisa()-Long.parseLong(request.getParameter("jumlahSisa"))==0) {
	        	pindahSemua = true;
	        } else {
	        	Barang barang = barangService.getBarangById(idBarang).get();
	    		model.addAttribute("idBarang", idBarang);
	    		
	    		Vector<String> kondisi = new Vector<>();
	    		if (kondisi.size()==0) {
	    			kondisi.add("Baik");
	    			kondisi.add("Rusak");
	    			kondisi.add("Kadaluarsa");
	    			kondisi.add("Baru");
	    		}
	    		model.put("kondisi", kondisi);
	    		
	    		List<Ruangan> ruangan = ruanganRepository.findAll();
	    		model.put("ruangan", ruangan);
	    		
	    		List<PerolehanBarang> perolehanBarang = perolehanBarangRepository.findAll();
	    		model.put("perolehanBarang", perolehanBarang);
	    		
	    		model.put("barang", barang);
	    		
	        	model.addAttribute("em", "Barang yang akan dipindah melebihi barang yang ada");
	        	return "barang";
	        }
		} else {
			Barang barang = barangService.getBarangById(idBarang).get();
    		model.addAttribute("idBarang", idBarang);
    		
    		Vector<String> kondisi = new Vector<>();
    		if (kondisi.size()==0) {
    			kondisi.add("Baik");
    			kondisi.add("Rusak");
    			kondisi.add("Kadaluarsa");
    			kondisi.add("Baru");
    		}
    		model.put("kondisi", kondisi);
    		
    		List<Ruangan> ruangan = ruanganRepository.findAll();
    		model.put("ruangan", ruangan);
    		
    		List<PerolehanBarang> perolehanBarang = perolehanBarangRepository.findAll();
    		model.put("perolehanBarang", perolehanBarang);
    		
    		model.put("barang", barang);
			model.addAttribute("em", "Ruangan belum dipilih untuk memindahkan barang!");
			return "barang";
		}
		
		//jika hendak pindahkan barang dari ruangan a ke ruangan b
		if (pindahSemua) {
			barangAwal.setIdRuangan(Long.parseLong(request.getParameter("idRuangan")));
			barangService.updateBarang(barangAwal);
		} else if (pindah) {
			barangAwal.setJumlahSisa(barangAwal.getJumlahSisa()-Long.parseLong(request.getParameter("jumlahSisa")));
			barangService.updateBarang(barangAwal);
			
			barangClone.setIdPerolehan(barangAwal.getIdPerolehan());
			barangClone.setIdRuangan(Long.parseLong(request.getParameter("idRuangan")));
			barangClone.setJumlahSisa(Long.parseLong(request.getParameter("jumlahSisa")));
			barangClone.setJumlahTotal(Long.parseLong(request.getParameter("jumlahSisa")));
			barangClone.setKondisi(barangAwal.getKondisi());
			barangClone.setMerk(barangAwal.getMerk());
			barangClone.setNamaBarang(barangAwal.getNamaBarang());
			barangClone.setTanggalMaintain(barangAwal.getTanggalMaintain());
			barangClone.setUserName(barangAwal.getUserName());
			barangService.saveBarang(barangClone);
			
		}
		
		//pencatatan history pemindahan barang
        if (pindah) {
	        HistoryTracking historyTracking = new HistoryTracking();
	        
	        historyTracking.setIdBarang(barangClone.getIdBarang());
	        historyTracking.setIdRuangan(Long.parseLong(request.getParameter("idRuangan")));
	        historyTracking.setKeterangan(Long.parseLong(request.getParameter("jumlahSisa")) + " " + barangService.getNamaBarangById(barangClone.getIdBarang()) 
	        	+ " dipindahkan ke " + ruanganRepository.getNamaRuanganById(Long.parseLong(request.getParameter("idRuangan"))));
	        historyTracking.setTanggalHistory(new Date());
	        historyTracking.setJumlahPindah(Long.parseLong(request.getParameter("jumlahSisa")));
	        historyTracking.setJumlahBarang(Long.parseLong(request.getParameter("jumlahTotal")));
	        historyTrackingRepository.save(historyTracking);
        } else if (pindahSemua) {
        	HistoryTracking historyTracking = new HistoryTracking();
 	        
 	        historyTracking.setIdBarang(idBarang);
 	        historyTracking.setIdRuangan(Long.parseLong(request.getParameter("idRuangan")));
 	        historyTracking.setKeterangan(Long.parseLong(request.getParameter("jumlahSisa")) + " " + barangService.getNamaBarangById(idBarang) 
 	        	+ " dipindahkan ke " + ruanganRepository.getNamaRuanganById(Long.parseLong(request.getParameter("idRuangan"))));
 	        historyTracking.setTanggalHistory(new Date());
 	        historyTracking.setJumlahPindah(Long.parseLong(request.getParameter("jumlahSisa")));
 	        historyTracking.setJumlahBarang(Long.parseLong(request.getParameter("jumlahTotal")));
 	        historyTrackingRepository.save(historyTracking);
        }
        return "redirect:/list-barangs";
	}

	//penambahan barang baru
	@RequestMapping(value = "/add-barang", method = RequestMethod.POST)
	public String addBarang(ModelMap model, @Valid Barang barang, BindingResult result, HttpServletRequest request) {
		if (result.hasErrors()) {
			return "barang";
		}
		barang.setUserName(getLoggedInUserName(model));
		barang.setJumlahSisa(Long.parseLong(request.getParameter("jumlahTotal")));
		barangService.saveBarang(barang);
		
		HistoryTracking historyTracking = new HistoryTracking();
		historyTracking.setIdBarang(barang.getIdBarang());
        historyTracking.setIdRuangan(Long.parseLong(request.getParameter("idRuangan")));
        historyTracking.setKeterangan(Long.parseLong(request.getParameter("jumlahTotal")) + " " + barangService.getNamaBarangById(barang.getIdBarang()) 
        	+ " dimasukkan ke " + ruanganRepository.getNamaRuanganById(Long.parseLong(request.getParameter("idRuangan"))));
        historyTracking.setTanggalHistory(new Date());
        historyTracking.setJumlahPindah(new Long(0));
        historyTracking.setJumlahBarang(Long.parseLong(request.getParameter("jumlahTotal")));
		historyTrackingRepository.save(historyTracking);
		return "redirect:/list-barangs";
	}
	
	//untuk download record barang yang ada
	@RequestMapping(path = "/download", method = RequestMethod.GET)
	public ModelAndView reportDaftarBarang(ModelMap model) {
		String userName = getLoggedInUserName(model);
		List<DaftarBarang> list = barangService.getBarangsByUser(userName);
		Map<String, Object> map = new HashMap<>();
		map.put("daftarBarang", list);
		return new ModelAndView(new PdfDaftarBarangReport(), map);
	}
	
}
