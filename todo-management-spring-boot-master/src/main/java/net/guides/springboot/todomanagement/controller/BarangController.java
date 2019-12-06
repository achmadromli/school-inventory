package net.guides.springboot.todomanagement.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.List;

import javax.validation.Valid;

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

import net.guides.springboot.todomanagement.model.Barang;
import net.guides.springboot.todomanagement.model.HistoryTracking;
import net.guides.springboot.todomanagement.model.PerolehanBarang;
import net.guides.springboot.todomanagement.model.Ruangan;
import net.guides.springboot.todomanagement.repository.HistoryTrackingRepository;
import net.guides.springboot.todomanagement.repository.PerolehanBarangRepository;
import net.guides.springboot.todomanagement.repository.RuanganRepository;
import net.guides.springboot.todomanagement.service.IBarangService;

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
	
	@RequestMapping(value = "/list-barangs", method = RequestMethod.GET)
	public String showBarangs(ModelMap model) {
		String name = getLoggedInUserName(model);
		
		model.put("barangs", barangService.getBarangsByUser(name));
		
		return "list-barangs";
	}

	private String getLoggedInUserName(ModelMap model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			return ((UserDetails) principal).getUsername();
		}

		return principal.toString();
	}

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
		
		List<Ruangan> ruangan = ruanganRepository.findAll();
		model.put("ruangan", ruangan);
		
		List<PerolehanBarang> perolehanBarang = perolehanBarangRepository.findAll();
		model.put("perolehanBarang", perolehanBarang);
		
		model.addAttribute("barang", new Barang());
		return "barang";
	}

	@RequestMapping(value = "/delete-barang", method = RequestMethod.GET)
	public String deleteBarang(@RequestParam Long idBarang) {
		barangService.deleteBarang(idBarang);
		return "redirect:/list-barangs";
	}

	@RequestMapping(value = "/update-barang", method = RequestMethod.GET)
	public String showUpdateBarangPage(@RequestParam Long idBarang, ModelMap model) {
		Barang barang = barangService.getBarangById(idBarang).get();
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
		return "barang";
	}

	@RequestMapping(value = "/update-barang", method = RequestMethod.POST)
	public String updateBarang(ModelMap model, @Valid Barang barang, BindingResult result) {

		if (result.hasErrors()) {
			return "barang";
		}
		
		HistoryTracking historyTracking = new HistoryTracking(barang.getIdRuangan(), barang.getIdBarang(), "Barang dipindah ke "+ruanganRepository.getNamaRuanganById(barang.getIdRuangan()), new Date(), barang.getJumlah());
		historyTrackingRepository.save(historyTracking);
		
		barang.setUserName(getLoggedInUserName(model));
		barangService.updateBarang(barang);
		return "redirect:/list-barangs";
	}

	@RequestMapping(value = "/add-barang", method = RequestMethod.POST)
	public String addBarang(ModelMap model, @Valid Barang barang, BindingResult result) {
		
		if (result.hasErrors()) {
			return "barang";
		}
		barang.setUserName(getLoggedInUserName(model));
		barangService.saveBarang(barang);
		HistoryTracking historyTracking = new HistoryTracking(barang.getIdRuangan(), barang.getIdBarang(), "Barang dimasukkan ke "+ruanganRepository.getNamaRuanganById(barang.getIdRuangan()), new Date(), barang.getJumlah());
		historyTrackingRepository.save(historyTracking);
		return "redirect:/list-barangs";
	}
	
}
