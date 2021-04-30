package fr.ENI.tpParking.ws;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.RestController;

import fr.ENI.tpParking.bll.vehicule.VehiculeManager;

import fr.ENI.tpParking.bo.Vehicule;




@RestController
public class VehiculeRest {

	@Autowired
	private VehiculeManager vehiculeManager;
	
	@GetMapping("/WS/voitures/")
	public List<Vehicule> getAll(){
		return vehiculeManager.getAllVehicule();
	}
	
}
