package fr.ENI.tpParking.bll.ticket;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.ENI.tpParking.bo.Ticket;
import fr.ENI.tpParking.bo.Vehicule;
import fr.ENI.tpParking.dal.TicketDAO;
import fr.ENI.tpParking.dal.VehiculeDAO;




@Service
public class TicketManagerImpl implements TicketManager {

	@Autowired
	TicketDAO TicketDAO;
	
	
	
	@Override
	@Transactional
	public void addTicket(Ticket ticket)  {
			TicketDAO.save(ticket);
	}

	@Override
	@Transactional
	public void removeTicket(Ticket ticket) {
		TicketDAO.delete(ticket);
	}

	@Override
	@Transactional
	public void updateTicket(Ticket ticket) {
		TicketDAO.save(ticket);
	}

	@Override
	public List<Ticket> getAllTicket() {
		return (List<Ticket>) TicketDAO.findAll();
	}

	@Override
	public Ticket getById(Integer id) {
		return TicketDAO.findById(id).orElse(null);
	}

	@Override
	public void removeTicketFromId(Integer id) {
		TicketDAO.deleteById(id);
	}
	
	
	@Override
	public Ticket getByVehiculeAndDepart(Integer id) {
		return TicketDAO.findByByVehiculeAndDepart(id);
	}

	@Override
	public List<Ticket> getByVehicule(Integer id) {
		
		return TicketDAO.findByByIdVehicule(id);
	}
	
	
}
