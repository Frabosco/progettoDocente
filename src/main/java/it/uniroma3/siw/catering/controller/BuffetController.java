package it.uniroma3.siw.catering.controller;

import javax.validation.Valid;

import it.uniroma3.siw.catering.model.Credential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import it.uniroma3.siw.catering.model.Buffet;
import it.uniroma3.siw.catering.model.Chef;
import it.uniroma3.siw.catering.service.BuffetService;
import it.uniroma3.siw.catering.service.ChefService;
import it.uniroma3.siw.catering.service.PiattoService;
import it.uniroma3.siw.catering.validator.BuffetValidator;

@Controller
public class BuffetController {
	
	@Autowired
	private BuffetService buffetService;
	
	@Autowired
	private ChefService chefService;
	
	@Autowired
	private BuffetValidator buffetValidator;
	
	@PostMapping("/admin/chef/{chefId}/buffet")
	public String addBuffet(@Valid @ModelAttribute(value="buffet") Buffet buffet,
			@PathVariable("chefId") Long chefId,
			BindingResult bindingResult, Model model) {
		
		this.buffetValidator.validate(buffet, bindingResult);
		
		if(!bindingResult.hasErrors()) {
			this.buffetService.save(buffet, chefService.findById(chefId));
			model.addAttribute("buffet", buffet);
			model.addAttribute("piattiAssenti", buffetService.findPiattiNotInBuffet(buffet.getId()));
			return "admin/addPiattiToBuffet";
		}
		else {
			model.addAttribute("buffet", buffet);
			return "admin/buffetForm";
		}
	}
	
	@GetMapping("/admin/chef/{chefId}/newBuffet")
	public String newBuffet(@PathVariable("chefId") Long chefId, Model model) {
		model.addAttribute("chef", chefService.findById(chefId));
		model.addAttribute("buffet", buffetService.createBuffet());
		return "admin/buffetForm";
	}
	
	@GetMapping("/buffet/{buffetId}")
	public String getBuffet(@PathVariable("buffetId") Long buffetId, Model model) {
		model.addAttribute("buffet", buffetService.findById(buffetId));
		return "buffet";
	}
	
	@GetMapping("/chef/{chefId}/buffets")
	public String getBuffets(@PathVariable("chefId") Long chefId, Model model) {
		Chef chef = chefService.findById(chefId);
		model.addAttribute("buffets", buffetService.findAllByChef(chef));
		model.addAttribute("chef", chef);
		return "buffets";
	}
	
	@GetMapping("/buffets")
	public String getBuffets(Model model) {
		model.addAttribute("buffets", buffetService.findAll());
		return "buffets";
	}
	
	@GetMapping("/admin/buffet/{buffetId}/{piattoId}")
	public String addPiattoToNewBuffet(@PathVariable("buffetId") Long buffetId, 
			@PathVariable("piattoId") Long piattoId, Model model) {
		this.buffetService.addPiattoToBuffet(buffetId, piattoId);
		model.addAttribute(buffetService.findById(buffetId));
		model.addAttribute("piattiAssenti", buffetService.findPiattiNotInBuffet(buffetId));
		return "admin/addPiattiToBuffet";
	}
	
	@GetMapping("/admin/buffet/{buffetId}/add/{piattoId}")
	public String addPiattoToBuffet(@PathVariable("buffetId") Long buffetId, 
			@PathVariable("piattoId") Long piattoId, Model model) {
		this.buffetService.addPiattoToBuffet(buffetId, piattoId);
		model.addAttribute(buffetService.findById(buffetId));
		model.addAttribute("piattiAssenti", buffetService.findPiattiNotInBuffet(buffetId));
		return "admin/editBuffet";
	}
	
	@GetMapping("/admin/buffet/{buffetId}/removePiatto/{piattoId}")
	public String removePiattoFromBuffet(@PathVariable("buffetId") Long buffetId,
			@PathVariable("piattoId") Long piattoId, Model model) {
		this.buffetService.removePiattoFromBuffet(buffetId, piattoId);
		model.addAttribute(buffetService.findById(buffetId));
		model.addAttribute("piattiAssenti", buffetService.findPiattiNotInBuffet(buffetId));
		return "admin/editBuffet";
	}
	
	@GetMapping("/admin/editBuffet")
	public String chooseBuffetToEdit(Model model) {
		model.addAttribute("buffets", buffetService.findAll());
		return "admin/selectBuffetToEdit";
	}

	@GetMapping("/admin/editBuffet/{buffetId}")
	public String editBuffet(@PathVariable("buffetId") Long buffetId, Model model) {
		model.addAttribute("buffet", buffetService.findById(buffetId));
		return "admin/editBuffet";
	}

	@PostMapping("/admin/editBuffet")
	public String editBuffet(@Valid @ModelAttribute(value="buffet") Buffet buffet, Model model) {
		Buffet buffeTemp=buffetService.findById(buffet.getId());
		buffeTemp.setNome(buffet.getNome());
		buffeTemp.setDescrizione(buffet.getDescrizione());
		buffetService.save(buffeTemp, buffeTemp.getChef());
		return "admin/home";
	}
	
	@GetMapping("/admin/removeBuffet")
	public String chooseBuffetToRemove(Model model) {
		model.addAttribute("buffets", buffetService.findAll());
		return "admin/selectBuffetToRemove";
	}

	@GetMapping("/admin/removeBuffet/{buffetId}")
	public String removeBuffet(@PathVariable("buffetId")Long buffetId, Model model) {
		model.addAttribute("buffet", buffetService.findById(buffetId));
		return "admin/removeBuffet";
	}
	
	@GetMapping("/admin/confermaRemoveBuffet/{buffetId}")
	public String confermaRemoveBuffet(@PathVariable("buffetId")Long buffetId, Model model) {
		buffetService.removeBuffet(buffetId);
		model.addAttribute("buffets", buffetService.findAll());
		return "admin/selectBuffetToRemove";
	}
}
