package fr.ENI.tpParking.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import fr.ENI.tpParking.bll.ParkingManager;
import fr.ENI.tpParking.bo.Vehicule;




@RestController
public class ParkingRest {
	@Autowired
	private ParkingManager parkingManager;
	
	@GetMapping("/WS/parking/vehicules-current/{id}")
	public List<Vehicule> getListVehiculesCurrent(@PathVariable Integer id) {
		return parkingManager.getListVehiculesCurrent(id);
	}
	
	@GetMapping("/WS/parking/CA/{id}")
	public String getCA(@PathVariable Integer id) {
		
		return "CA : "+parkingManager.calculateCAByParking(parkingManager.getParkingById(id)) + " €";
		 
	}

}
