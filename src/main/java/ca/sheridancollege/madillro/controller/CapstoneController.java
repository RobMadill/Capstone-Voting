package ca.sheridancollege.madillro.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ca.sheridancollege.madillro.bean.CapstoneRatingSystem;
import ca.sheridancollege.madillro.database.DatabaseAccess;

@RestController
@RequestMapping("/capstone")
public class CapstoneController {
	@Autowired
	public DatabaseAccess da;
	
	@GetMapping
	public List<CapstoneRatingSystem> getCapstoneController(){
		return da.findAll();
	}
	
	@GetMapping(value = "/{id}")
	public CapstoneRatingSystem getIndividualCapstone(@PathVariable Long id) {
		return da.findById(id);
	}
	
	@PostMapping
	public void postCapstone(@RequestBody CapstoneRatingSystem capstone) {
		da.save(capstone);
	}
}
