package fr.ENI.tpParking.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.ENI.tpParking.bll.ticket.TicketManager;

import fr.ENI.tpParking.bo.Ticket;


@RestController
public class TicketRest {

	@Autowired
	private TicketManager ticketManager;
	
	@GetMapping("/WS/tickets/{immat}")
	public List<Ticket> getAll(@PathVariable String immat){
		
		return ticketManager.getListTicketByVehiculeImmat(immat);
	}
}
