package fr.formation.cinema.ihm;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.formation.cinema.bll.ClientManager;
import fr.formation.cinema.bll.FilmManager;
import fr.formation.cinema.bll.ReserverManager;
import fr.formation.cinema.bll.SalleManager;
import fr.formation.cinema.bll.SeanceManager;
import fr.formation.cinema.bo.Client;
import fr.formation.cinema.bo.Film;
import fr.formation.cinema.bo.Reserver;
import fr.formation.cinema.bo.Salle;
import fr.formation.cinema.bo.Seance;


@Controller
public class ApplicationController {
	
	@Autowired
	FilmManager filmManager;
	@Autowired
	ClientManager clientManager;
	@Autowired
	SalleManager salleManager;
	@Autowired
	ReserverManager reserverManager;
	
	@GetMapping("/film")
	public String index(Model model) {
		model.addAttribute("lstFilms",filmManager.getAll());
		return "film";
	}
	
	@GetMapping("/detailsFilm/{id}")
	public String detailsFilm(Model model,@PathVariable("id") Integer id) {
		Film film = filmManager.getById(id);
				
		model.addAttribute("film",film);
		model.addAttribute("mode","detailsFilm");
		return "detailsFilm";
	}
	
	@GetMapping("/addFilm")
	public String addFilm(Model model,Film film) {
		model.addAttribute("id",0); // j'envoie un attribut id à 0 pour que l'url de retour du formulaire soit conforme à celle du modify
		model.addAttribute("mode","addFilm");
		return "filmForm";
	}
	
	@GetMapping("/deleteFilm/{id}")
	public String deleteFilm(@PathVariable("id") Integer id) {
		filmManager.deleteById(id);
		return "redirect:/film";
	}
	@PostMapping("/valid/{mode}/{id}")
	public String valid(Model model, @Valid Film film, BindingResult result, @PathVariable String mode, @PathVariable Integer id) {
		if(result.hasErrors()) {
			return "filmForm";
		}
		if("addFilm".equals(mode)) {
			filmManager.add(film);
			
		}
		else if("modifyFilm".equals(mode)) {
			film.setIdFilm(id); 
			filmManager.modify(film);
			
		}
		return "redirect:/film";
	}
	
	@GetMapping("/modifyFilm/{id}")
	public String modifyFilm(Model model,@PathVariable("id") Integer id, Film film) {
		film = filmManager.getById(id); 
		model.addAttribute("mode","modifyFilm");
		model.addAttribute("film",film); 
		model.addAttribute("id",id);
		return "filmForm";
	}
//---------------------------------------------------------------------//
//--------------------------------Client-------------------------------//
//---------------------------------------------------------------------//
	
	@GetMapping("/clients")
	public String indexClient(Model model) {
		model.addAttribute("lstClients",clientManager.getAll());
		return "clients";
	}
	
	@GetMapping("/detailsClient/{id}")
	public String detailsClient(Model model,@PathVariable("id") Integer id) {
		Client client = clientManager.getById(id);
				
		model.addAttribute("client",client);
		model.addAttribute("mode","detailsClient");
		return "detailsClient";
	}
	
	@GetMapping("/addClient")
	public String addClient(Model model,Client client) {
		model.addAttribute("id",0); // j'envoie un attribut id à 0 pour que l'url de retour du formulaire soit conforme à celle du modify
		model.addAttribute("mode","addClient");
		return "clientForm";
	}
	
	@GetMapping("/deleteClient/{id}")
	public String deleteClient(@PathVariable("id") Integer id) {
		clientManager.deleteById(id);
		return "redirect:/client";
	}
	@PostMapping("/validClient/{mode}/{id}")
	public String valid(Model model, @Valid Client client, BindingResult result, @PathVariable String mode, @PathVariable Integer id) {
		if(result.hasErrors()) {
			return "clientForm";
		}
		if("addClient".equals(mode)) {
			clientManager.add(client);
			
		}
		else if("modifyClient".equals(mode)) {
			client.setIdClient(id); 
			clientManager.modify(client);
			
		}
		return "redirect:/clients";
	}
	
	@GetMapping("/modifyClient/{id}")
	public String modifyClient(Model model,@PathVariable("id") Integer id, Client client) {
		client = clientManager.getById(id); 
		model.addAttribute("mode","modifyClient");
		model.addAttribute("client", client); 
		model.addAttribute("id",id);
		return "clientForm";
	}
//---------------------------------------------------------------------//
//--------------------------------Salle-------------------------------//
//---------------------------------------------------------------------//
	@GetMapping("/salle")
	public String indexSalle(Model model) {
		model.addAttribute("lstSalles",salleManager.getAll());
		return "salle";
	}
	
	@GetMapping("/detailsSalle/{id}")
	public String detailsSalle(Model model,@PathVariable("id") Integer id) {
		Salle salle = salleManager.getById(id);
				
		model.addAttribute("salle", salle);
		model.addAttribute("mode","detailsSalle");
		return "detailsSalle";
	}
	
	@GetMapping("/addSalle")
	public String addSalle(Model model,Salle salle) {
		model.addAttribute("id",0); 
		model.addAttribute("mode","addSalle");
		return "salleForm";
	}
	
	@GetMapping("/deleteSalle/{id}")
	public String deleteSalle(@PathVariable("id") Integer id) {
		salleManager.deleteById(id);
		return "redirect:/salle";
	}
	@PostMapping("/validSalle/{mode}/{id}")
	public String valid(Model model, @Valid Salle salle, BindingResult result, @PathVariable String mode, @PathVariable Integer id) {
		if(result.hasErrors()) {
			return "salleForm";
		}
		if("addSalle".equals(mode)) {
			salleManager.add(salle);
			
		}
		else if("modifySalle".equals(mode)) {
			salle.setIdSalle(id); 
			salleManager.modify(salle);
			
		}
		return "redirect:/salle";
	}
	
	@GetMapping("/modifySalle/{id}")
	public String modifySalle(Model model,@PathVariable("id") Integer id, Salle salle) {
		salle = salleManager.getById(id); 
		model.addAttribute("mode","modifySalle");
		model.addAttribute("salle", salle); 
		model.addAttribute("id",id);
		return "salleForm";
	}
	
//---------------------------------------------------------------------//
//------------------------------Reservation----------------------------//
//---------------------------------------------------------------------//
	@GetMapping("/reserver")
	public String indexReserver(Model model) {
		model.addAttribute("lstReservations",reserverManager.getAll());
		return "reserver";
	}
	
	@GetMapping("/detailsReservation/{id}")
	public String detailsReservation(Model model,@PathVariable("id") Integer id) {
		Reserver reserver = reserverManager.getById(id);
				
		model.addAttribute("reserver", reserver);
		model.addAttribute("mode","detailsReservation");
		return "detailsReservation";
	}
	
	@GetMapping("/addReservation")
	public String addReservation(Model model,Reserver reserver) {
		model.addAttribute("id",0); 
		model.addAttribute("mode","addReservation");
		return "reserverForm";
	}
	
	@GetMapping("/deleteReservation/{id}")
	public String deleteReservation(@PathVariable("id") Integer id) {
		reserverManager.deleteById(id);
		return "redirect:/reserver";
	}
	@PostMapping("/validReservation/{mode}/{id}")
	public String valid(Model model, @Valid Reserver reserver, BindingResult result, @PathVariable String mode, @PathVariable Integer id) {
		if(result.hasErrors()) {
			return "reserverForm";
		}
		if("addReservation".equals(mode)) {
			reserverManager.add(reserver);
			
		}
		else if("modifyReservation".equals(mode)) {
			reserver.setIdReserver(id); 
			reserverManager.modify(reserver);
			
		}
		return "redirect:/reserver";
	}
	
	@GetMapping("/modifyReservation/{id}")
	public String modifyReservation(Model model,@PathVariable("id") Integer id, Reserver reserver) {
		reserver = reserverManager.getById(id); 
		model.addAttribute("mode","modifyReservation");
		model.addAttribute("reserver", reserver); 
		model.addAttribute("id",id);
		return "reserverForm";
	}
//---------------------------------------------------------------------//
//--------------------------------Seance-------------------------------//
//---------------------------------------------------------------------//
	@Autowired
	SeanceManager seanceManager;
	
		@GetMapping("/seance")
		public String indexSeance(Model model) {
			model.addAttribute("lstSeances",seanceManager.getAll());
			return "seance";
		}
		
		@GetMapping("/detailsSeance/{id}")
		public String detailsSeance(Model model,@PathVariable("id") Integer id) {
			Seance seance = seanceManager.getById(id);
					
			model.addAttribute("seance", seance);
			model.addAttribute("mode","detailsSeance");
			return "detailsSeance";
		}
		
		@GetMapping("/addSeance")
		public String addSeance(Model model,Seance seance) {
			model.addAttribute("id",0); 
			model.addAttribute("mode","addSeance");
			return "seanceForm";
		}
		
		@GetMapping("/deleteSeance/{id}")
		public String deleteSeance(@PathVariable("id") Integer id) {
			seanceManager.deleteById(id);
			return "redirect:/seance";
		}
		@PostMapping("/validSeance/{mode}/{id}")
		public String valid(Model model, @Valid Seance seance, BindingResult result, @PathVariable String mode, @PathVariable Integer id) {
			if(result.hasErrors()) {
				return "seanceForm";
			}
			if("addSeance".equals(mode)) {
				seanceManager.add(seance);
				
			}
			else if("modifySeance".equals(mode)) {
				seance.setIdSeance(id); 
				seanceManager.modify(seance);
				
			}
			return "redirect:/seance";
		}
		
		@GetMapping("/modifySeance/{id}")
		public String modifySeance(Model model,@PathVariable("id") Integer id, Seance seance) {
			seance = seanceManager.getById(id); 
			model.addAttribute("mode","modifySeance");
			model.addAttribute("seance", seance); 
			model.addAttribute("id",id);
			return "seanceForm";
		}
}
