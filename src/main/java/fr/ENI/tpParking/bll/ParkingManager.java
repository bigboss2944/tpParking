package fr.ENI.tpParking.bll;

import java.time.LocalDateTime;
import java.util.List;

import fr.ENI.tpParking.bo.Parking;
import fr.ENI.tpParking.bo.Ticket;
import fr.ENI.tpParking.bo.Vehicule;

public interface ParkingManager {
	public void addParking(Parking parking) throws ParkingManagerException;
	public void updateParking(Parking parking) throws ParkingManagerException;
	public void deleteParking(Integer idParking);
	
	public List<Parking> getAllParking();
	
	public Parking getParkingById(Integer idParking);
	
	public void addVehiculeToParking(Integer idParking, Vehicule vehicule) throws ParkingManagerException;
	public void removeVehiculeFromParking(Integer idParking, Vehicule vehicule) throws ParkingManagerException;
	
	public float calculatePrice(Ticket ticket, Parking parking);
	
	public float calculateCAByParking(Parking parking);
	
	public Integer getPlacesAvailable(Parking parking);
	
	public boolean checkHourArrival(LocalDateTime heureArrivee);
	
	public boolean checkDateDeparture(Ticket ticket, LocalDateTime heureDepart);
	public List<Vehicule> getListVehiculesCurrent(Integer idParking);
	public Float getCAByParking(Integer idParking);
}
