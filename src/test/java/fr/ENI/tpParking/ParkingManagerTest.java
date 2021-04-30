package fr.ENI.tpParking;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import fr.ENI.tpParking.bll.ParkingManager;
import fr.ENI.tpParking.bll.ParkingManagerException;
import fr.ENI.tpParking.bo.Parking;
import fr.ENI.tpParking.bo.Vehicule;



@SpringBootTest
public class ParkingManagerTest {

	@Autowired
	ParkingManager parkingManager;
	
	@Test
	@Transactional
	public void AddVehiculeNoPlaceInParking() {
		
		Parking parking = new Parking("15,rue de Paris",0,15.0f);
		Vehicule vehicule = new Vehicule("immat","Nomark","testla");
		
		try {
			parkingManager.addParking(parking);
		} catch (ParkingManagerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}
