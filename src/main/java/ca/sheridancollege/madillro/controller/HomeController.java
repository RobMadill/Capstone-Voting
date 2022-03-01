package ca.sheridancollege.madillro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import ca.sheridancollege.madillro.bean.CapstoneRatingSystem;
import ca.sheridancollege.madillro.database.DatabaseAccess;

@Controller
public class HomeController {
	
	@Autowired
	private DatabaseAccess da;
	
	@GetMapping("/")
	public String index(Model model, RestTemplate rest) {
		ResponseEntity<CapstoneRatingSystem[]> rE = 
				rest.getForEntity("http://localhost:8080/capstone",
				CapstoneRatingSystem[].class);
		model.addAttribute("capstone", new CapstoneRatingSystem());		
		model.addAttribute("capstoneList", rE.getBody());
		return "index";
	}
	
	@GetMapping(value = "/getCapstone/{id}", produces = "application/json")
	@ResponseBody
	public CapstoneRatingSystem getCapstone(@PathVariable int id, RestTemplate rest) {
		ResponseEntity<CapstoneRatingSystem> rE = 
				rest.getForEntity("http://localhost:8080/capstone/" + id,
						CapstoneRatingSystem.class);
		return rE.getBody();
	}
	
	@PostMapping("/insertCapstone")
	public String insertCapstone(Model model, RestTemplate rest, @ModelAttribute CapstoneRatingSystem capstone) {		
		rest.postForEntity("http://localhost:8080/capstone", capstone, null);		
		ResponseEntity<CapstoneRatingSystem[]> rE = rest.getForEntity("http://localhost:8080/capstone",
				CapstoneRatingSystem[].class);		
		model.addAttribute("capstone", new CapstoneRatingSystem());
		model.addAttribute("capstoneList", rE.getBody());
		return "index";
	}	
	
	@GetMapping(value = "/updateCapstone/{id}", produces = "application/json")	
	public String updateCapstone(Model model, @PathVariable Long id, @ModelAttribute CapstoneRatingSystem capstone, RestTemplate rest) {
		ResponseEntity<CapstoneRatingSystem[]> rE = 
				rest.getForEntity("http://localhost:8080/capstone",
				CapstoneRatingSystem[].class);
		model.addAttribute("capstone", capstone);
		da.updateCapstoneById(id);
		model.addAttribute("capstoneList", rE.getBody());
		return "index";
	}	
}