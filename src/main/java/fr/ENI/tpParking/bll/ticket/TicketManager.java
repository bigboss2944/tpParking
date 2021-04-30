package fr.ENI.tpParking.bll.ticket;

import java.util.List;

import fr.ENI.tpParking.bo.Ticket;
import fr.ENI.tpParking.bo.Vehicule;

/**
 * CRUD de ticket
 * @author manu
 *
 */
public interface TicketManager {
	/**
	 * Ajouter le ticket
	 * @param ticket
	 * @throws TicketManagerException 
	 */
	public void addTicket(Ticket ticket) ;
	
	/**
	 * Supprime le ticket
	 * @param ticket
	 */
	public void removeTicket(Ticket ticket);
	
	/**
	 * modifie le ticket
	 * @param ticket
	 */
	public void updateTicket(Ticket ticket);
	
	/**
	 * Récupére tous les tickets
	 * @return la liste des tickets
	 */
	public List<Ticket> getAllTicket();
	
	/**
	 * récupére un ticket par son identifiant
	 * @param id l'identifiant
	 * @return le ticket ou null si non existant
	 */
	public Ticket getById(Integer id);

	public void removeTicketFromId(Integer id);

	public Ticket getByVehiculeAndDepart(Integer id);

	public List<Ticket> getByVehicule(Integer id);

}
