package it.uniroma3.siw.catering.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.catering.model.Chef;
import it.uniroma3.siw.catering.service.ChefService;
import it.uniroma3.siw.catering.validator.ChefValidator;

@Controller
public class ChefController {

	@Autowired
	private ChefService chefService;

	@Autowired
	private ChefValidator chefValidator;

	@PostMapping("/admin/chef")
	public String addChef(@Valid @ModelAttribute(value="chef") Chef chef, 
			BindingResult bindingResult, Model model) {

		this.chefValidator.validate(chef, bindingResult);

		if (!bindingResult.hasErrors()) {
			this.chefService.save(chef); // salvo un oggetto chef
			model.addAttribute("chef", chef);
			return "chef";
		}
		else {
			model.addAttribute("chef", chef);
			return "admin/chefForm";
		}
	}
	
	@GetMapping("/chef/{id}")
	public String getChef(@PathVariable("id")Long id, Model model) {
		Chef chef = chefService.findById(id);		model.addAttribute("chef", chef);
		return "chef";
	}

	@GetMapping("/chefs")
	public String getChefs(Model model) {
		List<Chef> chefs = chefService.findAll();
		model.addAttribute("chefs", chefs);
		return "chefs";
	}

	@GetMapping("/admin/chefForm")
	public String chefForm(Model model) {
		model.addAttribute("chef", chefService.createChef());
		return "admin/chefForm";
	}
	
	@GetMapping("/admin/chefs/addBuffet") 
	public String addBuffet(Model model) {
		model.addAttribute("chefs", chefService.findAll());
		return "admin/selezionaChef";
	}
}

