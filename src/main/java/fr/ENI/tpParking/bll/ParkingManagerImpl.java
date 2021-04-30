package fr.ENI.tpParking.bll;

import java.text.DecimalFormat;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.ENI.tpParking.bll.ticket.TicketManager;
import fr.ENI.tpParking.bo.Parking;
import fr.ENI.tpParking.bo.Ticket;
import fr.ENI.tpParking.bo.Vehicule;
import fr.ENI.tpParking.dal.ParkingDAO;
import fr.ENI.tpParking.dal.TicketDAO;

@Service
public class ParkingManagerImpl implements ParkingManager {

	@Autowired
	ParkingDAO parkingDAO;
	
	@Autowired
	TicketDAO ticketDAO;
	
	@Autowired
	TicketManager ticketManager;
	
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
		System.out.println(parking.getNbrePlaces());
		
		
		if(getPlacesAvailable(parking)==0) {
			throw new ParkingManagerException("Il n'y a plus de places dans ce parking");
		}
		else if(!checkHourArrival(LocalDateTime.now())) {
			throw new ParkingManagerException("Impossible de se garer dans ce parking entre 21h et 6h du matin");
		}
		else {
			
			
			
			Ticket ticket = new Ticket(parking, vehicule, LocalDateTime.now());
			
			ticketManager.addTicket(ticket);
			
			Integer nbPlaces = getPlacesAvailable(parking);
			System.out.println("Le nombres de places restantes est de : "+nbPlaces);
			parking.setNbrePlaces(nbPlaces);
			updateParking(parking);
			
			
		}

	}

	@Override
	public void removeVehiculeFromParking(Integer idParking, Vehicule vehicule) throws ParkingManagerException {
		// TODO Auto-generated method stub
		Parking parking = getParkingById(idParking);
		Ticket ticket = parkingDAO.GetCurrentTicketForAVehicule(idParking, vehicule.getIdVehicule());
		
		if(!checkDateDeparture(ticket,LocalDateTime.now())) {
			throw new ParkingManagerException("La date d'entr�e et de sortie ne correspondent pas");
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
		return parking.getNbrePlaces()-parkingDAO.CountVehiculeByParking(parking.getIdParking()).size();
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
	
	@Override
	public List<Vehicule> getListVehiculesCurrent(Integer idParking){
	
		return ticketDAO.findVehiculeByParkingAndDepart(idParking);
	}
	
	@Override
	public Float getCAByParking(Integer idParking) {
		ticketDAO.findTicketByParkingAndDepart(idParking);
		
		Float totalCA = 0f;
		
		for (Ticket ticket : ticketDAO.findTicketByParkingAndDepart(idParking)) {
			Duration duration = Duration.between(ticket.getDateHeureArrivee(), ticket.getDateHeureDepart());
			Float prix = (ticket.getParking().getTarifHoraire())*(duration.toMinutes())/60;
			totalCA += prix;
		}
		DecimalFormat df = new DecimalFormat("0.00"); 
		Float totalCAArondi = Float.valueOf(df.format(totalCA));
		
		return totalCAArondi;
	}

}
