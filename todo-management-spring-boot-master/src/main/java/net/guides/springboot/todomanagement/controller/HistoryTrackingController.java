package net.guides.springboot.todomanagement.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import net.guides.springboot.todomanagement.repository.HistoryTrackingRepository;

@Controller
public class HistoryTrackingController {

	@Autowired
	private HistoryTrackingRepository historyTrackingRepository;
	
	@RequestMapping(value = "/historyBarang", method = RequestMethod.GET)
	public String showHistoryBarang(ModelMap model) {
		
		model.put("historys", historyTrackingRepository.getListHistory());
		
		return "historyBarang";
	}
	
}
