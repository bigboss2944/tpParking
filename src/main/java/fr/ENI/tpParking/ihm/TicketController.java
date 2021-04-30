package fr.ENI.tpParking.ihm;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


import fr.ENI.tpParking.bll.TicketManager;

import fr.ENI.tpParking.bo.Ticket;

@Controller
public class TicketController {
	
	@Autowired
	TicketManager ticketManager;
	
	@GetMapping("/tpParking/ticket/saisie")
	public String entreSaisie(Ticket ticket) {
		return "addTicket";
	}
	
	@PostMapping("/tpParking/ticket/add")
	public String addParking(@Valid Ticket ticket, BindingResult result, Model model){
		if (result.hasErrors()) {
			return "addTicket";
		}
		ticketManager.addTicket(ticket);
		return "redirect:/tpParking/parking/index"; // n'appelle pas l'html mais l'url

	}
	
	@GetMapping("/tpParking/ticket/index")
	public String listTickets(Model model) {
		model.addAttribute("tickets", ticketManager.getAllTicket());
		return "indexTicket";
	}
	
	@GetMapping("/tpParking/ticket/edit/{id}")
	public String showUpdateForm(@PathVariable("id") Integer idTicket, Model model) {
		Ticket ticket = ticketManager.getTicketById(idTicket);
		model.addAttribute("ticket", ticket);
		return "updateTicket";
	}
	
	@PostMapping("/tpParking/ticket/update/{id}")
	public String updateTicket(@PathVariable("id") Integer idTicket, @Valid Ticket ticket, BindingResult result,
			Model model) {
		ticket.setIdTicket(idTicket);
		if (result.hasErrors()) {
			return "updateTicket";
		}
		ticketManager.updateTicket(ticket);
		return "redirect:/tpParking/ticket/index";
	}
	
	@GetMapping("/tpParking/ticket/delete/{id}")
	public String deleteParking(@PathVariable("id") Integer idTicket, Model model) {	
		ticketManager.deleteTicket(idTicket);
		
	    return "redirect:/tpParking/ticket/index";
	}
	
	

}
