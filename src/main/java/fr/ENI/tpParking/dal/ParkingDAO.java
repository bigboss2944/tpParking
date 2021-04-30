package fr.ENI.tpParking.dal;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import fr.ENI.tpParking.bo.Parking;
import fr.ENI.tpParking.bo.Ticket;


public interface ParkingDAO extends CrudRepository<Parking, Integer> {

	
	@Query("from Ticket t where t.dateHeureDepart = null and t.parking.id = :idParking")
	List<Ticket> CountVehiculeByParking(@Param("idParking")Integer idParking);
	
	
	@Query("from Ticket t where t.dateHeureDepart = null and  t.parking.id = :idParking and t.vehicule.id = :idVehicule")
	Ticket GetCurrentTicketForAVehicule(@Param("idParking")Integer idParking, @Param("idVehicule")Integer idVehicule);
	
}
