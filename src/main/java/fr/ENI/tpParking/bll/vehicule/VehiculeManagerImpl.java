package fr.ENI.tpParking.bll.vehicule;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fr.ENI.tpParking.bo.Ticket;
import fr.ENI.tpParking.bo.Vehicule;
import fr.ENI.tpParking.dal.VehiculeDAO;




@Service
public class VehiculeManagerImpl implements VehiculeManager {

	@Autowired
	VehiculeDAO VehiculeDAO;
	
	@Override
	@Transactional
	public void addVehicule(Vehicule vehicule) throws VehiculeManagerException {
		List<Vehicule> identique = VehiculeDAO.findByImmat(vehicule.getImmat());
		if(identique.isEmpty()) {
			VehiculeDAO.save(vehicule);
		}
		else {
			throw new VehiculeManagerException("Immatriculationt déjà présent");
		}
	}

	@Override
	@Transactional
	public void removeVehicule(Vehicule vehicule) {
		VehiculeDAO.delete(vehicule);
	}

	@Override
	@Transactional
	public void updateVehicule(Vehicule vehicule) {
		VehiculeDAO.save(vehicule);
	}

	@Override
	public List<Vehicule> getAllVehicule() {
		return (List<Vehicule>) VehiculeDAO.findAll();
	}

	@Override
	public Vehicule getById(Integer id) {
		return VehiculeDAO.findById(id).orElse(null);
	}

	@Override
	public void removeVehiculeFromId(Integer id) {
		VehiculeDAO.deleteById(id);
	}

	
	@Override
	public List<Ticket> getByImmat(String immat) {
		// TODO Auto-generated method stub
		List<Vehicule> listVehicule = VehiculeDAO.findByImmat(immat);
		List<Ticket> listTicket = null;
		for (Vehicule vehicule : listVehicule) {
			vehicule.getListTicket();
		}
		
		return listTicket;
	}
}
