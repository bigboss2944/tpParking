package fr.ENI.tpParking.bll.ticket;

import java.util.List;

import fr.ENI.tpParking.bo.Ticket;


public interface TicketManager {
	public void addTicket(Ticket ticket);
	public void updateTicket(Ticket ticket);
	public void deleteTicket(Integer idTicket);
	
	public Ticket getTicketById(Integer idTicket);
	
	public List<Ticket> getAllTicket();
	
	public Ticket getTicketByVehiculeImmat(String immat);
	
	public List<Ticket> getListTicketByVehiculeImmat(String immat);
	
	public Ticket getByVehiculeAndDepart(Integer id);

	public List<Ticket> getByVehicule(Integer id);
	List<Ticket> getByVehiculeImmat(String immat);
}
