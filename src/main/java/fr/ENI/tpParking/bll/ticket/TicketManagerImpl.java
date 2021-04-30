package fr.ENI.tpParking.bll;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import fr.ENI.tpParking.bo.Ticket;
import fr.ENI.tpParking.dao.TicketDAO;

public class TicketManagerImpl implements TicketManager {

	@Autowired
	TicketDAO ticketDAO;
	
	@Override
	public void addTicket(Ticket ticket) {
		// TODO Auto-generated method stub
		ticketDAO.save(ticket);
	}

	@Override
	public void updateTicket(Ticket ticket) {
		// TODO Auto-generated method stub
		ticketDAO.save(ticket);

	}

	@Override
	public void deleteTicket(Integer idTicket) {
		// TODO Auto-generated method stub
		ticketDAO.deleteById(idTicket);

	}

	@Override
	public List<Ticket> getAllTicket() {
		// TODO Auto-generated method stub
		return (List<Ticket>) ticketDAO.findAll();
	}

	@Override
	public Ticket getTicketByVehiculeImmat(String immat) {
		// TODO Auto-generated method stub
		return (Ticket) ticketDAO.getTicketByVehiculeImmat(immat);
	}

	@Override
	public List<Ticket> getListTicketByVehiculeImmat(String immat) {
		// TODO Auto-generated method stub
		return (List<Ticket>) ticketDAO.getListTicketByVehiculeImmat(immat);
	}

	@Override
	public Ticket getTicketById(Integer idTicket) {
		// TODO Auto-generated method stub
		return ticketDAO.findById(idTicket).orElse(null);
	}

}
