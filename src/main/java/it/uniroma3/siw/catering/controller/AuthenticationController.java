package it.uniroma3.siw.catering.controller;

import it.uniroma3.siw.catering.model.Credential;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import it.uniroma3.siw.catering.model.User;
import it.uniroma3.siw.catering.service.CredentialService;
import it.uniroma3.siw.catering.service.UserService;
import it.uniroma3.siw.catering.validator.CredentialValidator;
import it.uniroma3.siw.catering.validator.UserValidator;

import javax.validation.Valid;

@Controller
public class AuthenticationController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private CredentialService credentialService;
	
	@Autowired
	private UserValidator userValidator;
	
	@Autowired
	private CredentialValidator credentialsValidator;


	@GetMapping("/login")
	public String getLoginPage(Model model) {
		return "loginForm";
	}
	
	@GetMapping("/logout") 
	public String logout(Model model) {
		return "index";
	}
	
	@GetMapping("/register")
	public String getRegisterPage(Model model) {
		model.addAttribute("user", new User());
		model.addAttribute("credential", new Credential());
		return "registerUser";
	}
	
	@PostMapping("/register")
	public String newUser(@Valid @ModelAttribute(value = "credential") Credential credential,
			BindingResult CredentialsBindingResult, @Valid @ModelAttribute(value = "user") User user,
			BindingResult userBindingResult ,Model model) {
		this.userValidator.validate(user, userBindingResult);
		this.credentialsValidator.validate(credential, CredentialsBindingResult);
		if(!userBindingResult.hasErrors() && !CredentialsBindingResult.hasErrors()) {
			credential.setUser(user);
            credentialService.saveCredential(credential);
            return "registrationSuccessful";
		}
		return "registerUser";
	}

	@RequestMapping(value = "/default", method = RequestMethod.GET)
	public String defaultAfterLogin(Model model) {
		UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credential credentials = credentialService.getCredential(userDetails.getUsername());
		if (credentials.getRole().equals(Credential.ADMIN_ROLE)) {
			return "admin/home";
		}
		return "home";
	}
    

	@GetMapping("/home")
	public String homePage(Model model) {
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    	Credential credential = credentialService.getCredential(userDetails.getUsername());
    	if (credential.getRole().equals(credential.ADMIN_ROLE)) {
            return "admin/home";
        }
        return "home";
	}
}
