package com.sbd.inventory.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sbd.inventory.repository.HistoryTrackingRepository;

@Controller
public class HistoryTrackingController {

	@Autowired
	private HistoryTrackingRepository historyTrackingRepository;
	
	//untuk menampilkan record history barang
	@RequestMapping(value = "/historyBarang", method = RequestMethod.GET)
	public String showHistoryBarang(ModelMap model) {
		
		model.put("historys", historyTrackingRepository.getListHistory());
		
		return "historyBarang";
	}
	
}
