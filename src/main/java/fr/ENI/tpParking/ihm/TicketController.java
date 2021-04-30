package fr.ENI.tpParking.ihm;



import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.ENI.tpParking.bll.ticket.TicketManager;
import fr.ENI.tpParking.bo.Ticket;


@Controller
public class TicketController {

	@Autowired
	TicketManager ticketManager;

	@GetMapping("/ticket/saisie")
	public String entreSaisie(Ticket ticket) {
		return "addTicket";
	}

	@PostMapping("/ticket/add")
	public String addTicket(@Valid Ticket ticket, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "addTicket";
		}
		ticketManager.addTicket(ticket);
		return "redirect:/ticket/index"; // n'appelle pas l'html mais l'url

	}

	@GetMapping("/ticket/index")
	public String listTickets(Model model) {
		model.addAttribute("tickets", ticketManager.getAllTicket());
		return "indexTicket";
	}

	@GetMapping("ticket/edit/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		Ticket ticket = ticketManager.getTicketById(id);
		model.addAttribute("ticket", ticket);
		return "updateTicket";
	}

	@PostMapping("ticket/update/{id}")
	public String updateTicket(@PathVariable("id") Integer id, @Valid Ticket ticket, BindingResult result,
			Model model) {
		ticket.setIdTicket(id);
		//ticket.setDateHeureDepart(LocalDateTime.now());
		if (result.hasErrors()) {
			return "updateTicket";
		}
		
		ticketManager.updateTicket(ticket);
		return "redirect:/ticket/index";
	}
	
	@GetMapping("/ticket/delete/{id}")
	public String deleteTicket(@PathVariable("id") Integer id, Model model) {	
		ticketManager.deleteTicket(id);
		
	    return "redirect:/ticket/index";
	}
	
	@GetMapping("/ticket/vehicule-entrer/{idVehicule}")
	public String showTicketArrivee(@PathVariable("idVehicule") Integer id, Model model, HttpServletRequest request) {	
		List<Ticket> listTickets = ticketManager.getByVehicule(id);
		
		request.getSession().setAttribute("idVehicule",id);
		
		Ticket ticketCurrent=new Ticket();
		for (Ticket ticket : listTickets) {
			if(ticket.getDateHeureDepart() ==null) {
				ticketCurrent = ticket;
			}
		}
		model.addAttribute("ticket", ticketCurrent);
	    return "ticketArrivee";
	}
	
	@GetMapping("/ticket/vehicule-sortir/{idTicket}")
	public String showTicketDepart(@PathVariable("idTicket") Integer id, Model model) {	
		Ticket ticket = ticketManager.getTicketById(id);
		ticket.setDateHeureDepart(LocalDateTime.now());
		ticketManager.updateTicket(ticket);
		Duration duration = Duration.between(ticket.getDateHeureArrivee(), ticket.getDateHeureDepart());
		Float prix = (ticket.getParking().getTarifHoraire())*(duration.toMinutes())/60;
		DecimalFormat df = new DecimalFormat("0.00"); 
		Float prixArondi = Float.valueOf(df.format(prix));
		System.out.println(prixArondi);
		model.addAttribute("prix", prixArondi);
		model.addAttribute("ticket", ticket);
		
	    return "ticketDepart";
	}

}
