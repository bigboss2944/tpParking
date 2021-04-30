package fr.ENI.tpParking.bll;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.ENI.tpParking.bo.Parking;
import fr.ENI.tpParking.bo.Ticket;
import fr.ENI.tpParking.bo.Vehicule;
import fr.ENI.tpParking.dal.ParkingDAO;

@Service
public class ParkingManagerImpl implements ParkingManager {

	@Autowired
	ParkingDAO parkingDAO;
	
	@Override
	public void addParking(Parking parking) throws ParkingManagerException {
		// TODO Auto-generated method stub
		if("".equals(parking.getAdresse())){
			throw new ParkingManagerException("Le parking doit avoir une adresse");
		}
		else if(parking.getNbrePlaces()==null){
			throw new ParkingManagerException("Le parking doit avoir un nombre de places. ");
		}
		else if(parking.getTarifHoraire()==null){
			throw new ParkingManagerException("Le parking doit avoir un tarif horaire");
		}
		else {
			parkingDAO.save(parking);
		}

	}

	@Override
	public void updateParking(Parking parking)throws ParkingManagerException{
		// TODO Auto-generated method stub
		if("".equals(parking.getAdresse())){
			throw new ParkingManagerException("Le parking doit avoir une adresse");
		}
		else if(parking.getNbrePlaces()==null){
			throw new ParkingManagerException("Le parking doit avoir un nombre de places. ");
		}
		else if(parking.getTarifHoraire()==null){
			throw new ParkingManagerException("Le parking doit avoir un tarif horaire");
		}
		else {
			parkingDAO.save(parking);
		}

	}

	@Override
	public void deleteParking(Integer idParking) {
		// TODO Auto-generated method stub
		parkingDAO.deleteById(idParking);

	}

	@Override
	public List<Parking> getAllParking() {
		// TODO Auto-generated method stub
		return (List<Parking>) parkingDAO.findAll();
	}

	@Override
	public Parking getParkingById(Integer idParking) {
		// TODO Auto-generated method stub
		return parkingDAO.findById(idParking).orElse(null);
	}

	@Override
	public void addVehiculeToParking(Integer idParking, Vehicule vehicule) throws ParkingManagerException {
		// TODO Auto-generated method stub
		Parking parking = getParkingById(idParking);
		if(getPlacesAvailable(parking)==0) {
			throw new ParkingManagerException("Il n'y a plus de places dans ce parking");
		}
		else if(!checkHourArrival(LocalDateTime.now())) {
			throw new ParkingManagerException("Impossible de se garer dans ce parking entre 21h et 6h du matin");
		}
		else {
			
			Integer nbPlaces = parkingDAO.CountVehiculeByParking(idParking).size();
			
			parking.setNbrePlaces(nbPlaces-1);
			updateParking(parking);
		}

	}

	@Override
	public void removeVehiculeFromParking(Integer idParking, Vehicule vehicule) throws ParkingManagerException {
		// TODO Auto-generated method stub
		Parking parking = getParkingById(idParking);
		Ticket ticket = parkingDAO.GetCurrentTicketForAVehicule(idParking, vehicule.getIdVehicule());
		
		if(!checkDateDeparture(ticket,LocalDateTime.now())) {
			throw new ParkingManagerException("La date d'entrée et de sortie ne correspondent pas");
		}
		else {
			Integer nbPlaces = getPlacesAvailable(parking);
			
			parking.setNbrePlaces(nbPlaces+1);
			updateParking(parking);
		}
	}

	@Override
	public float calculatePrice(Ticket ticket, Parking parking) {
		// TODO Auto-generated method stub
		LocalDateTime dateHeureDepart = ticket.getDateHeureDepart();
		return (float) dateHeureDepart.minusHours(ticket.getDateHeureArrivee().getHour()).getHour()*parking.getTarifHoraire();
	}

	@Override
	public float calculateCAByParking(Parking parking) {
		// TODO Auto-generated method stub
		float totalCA = 0;
		
		for (Ticket ticket : parking.getListTicket()) {
			LocalDateTime dateHeureDepart = ticket.getDateHeureDepart();
			totalCA = dateHeureDepart.minusHours(ticket.getDateHeureArrivee().getHour()).getHour()*parking.getTarifHoraire();
		}
		
		return totalCA;
	}

	@Override
	public Integer getPlacesAvailable(Parking parking) {
		// TODO Auto-generated method stub
		return parkingDAO.CountVehiculeByParking(parking.getIdParking()).size();
	}

	@Override
	public boolean checkHourArrival(LocalDateTime heureArrivee) {
		// TODO Auto-generated method stub
		
		if(heureArrivee.getHour()>19&&(heureArrivee.getHour()<6)) {
			return false;
		}
		else {
			return true;
		}
		
		
	}

	@Override
	public boolean checkDateDeparture(Ticket ticket, LocalDateTime heureDepart) {
		// TODO Auto-generated method stub
		
		if(ticket.getDateHeureArrivee().getDayOfMonth()!=heureDepart.getDayOfMonth()) {
			return false;
		}
		else {
			return true;
		}
		
	}

}
