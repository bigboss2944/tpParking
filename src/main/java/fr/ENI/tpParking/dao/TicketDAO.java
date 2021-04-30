package fr.ENI.tpParking.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import fr.ENI.tpParking.bo.Ticket;

public interface TicketDAO extends CrudRepository<Ticket, Integer> {

	
	@Query("from Ticket t where t.dateHeureDepart = null and t.parking.id = :idParking")
	List<Ticket> CountVehiculeByParking(@Param("idParking")Integer idParking);
	
	
	@Query("from Ticket t where t.dateHeureDepart = null and t.vehicule.immat = :immatVehicule")
	Ticket getTicketByVehiculeImmat(@Param("immatVehicule")String immat);
	
	@Query("from Ticket t where t.vehicule.immat = :immatVehicule")
	List<Ticket> getListTicketByVehiculeImmat(@Param("immatVehicule")String immat);
	
}
