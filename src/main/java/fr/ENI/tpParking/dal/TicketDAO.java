package fr.ENI.tpParking.dal;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import fr.ENI.tpParking.bo.Ticket;
import fr.ENI.tpParking.bo.Vehicule;

public interface TicketDAO extends CrudRepository<Ticket, Integer> {
	@Query("SELECT t FROM Ticket t WHERE t.vehicule.idVehicule =:id AND t.dateHeureDepart = null")
	Ticket findByByVehiculeAndDepart(@Param("id") Integer id);
	@Query("SELECT t FROM Ticket t WHERE t.vehicule.idVehicule =:id")
	List<Ticket> findByByIdVehicule(@Param("id") Integer id);

}
