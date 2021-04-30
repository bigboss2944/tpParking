package fr.ENI.tpParking.bll.vehicule;

import java.util.List;

import fr.ENI.tpParking.bo.Vehicule;

/**
 * CRUD de vehicule
 * @author manu
 *
 */
public interface VehiculeManager {
	/**
	 * Ajouter le vehicule
	 * @param vehicule
	 * @throws VehiculeManagerException 
	 */
	public void addVehicule(Vehicule vehicule) throws VehiculeManagerException;
	
	/**
	 * Supprime le vehicule
	 * @param vehicule
	 */
	public void removeVehicule(Vehicule vehicule);
	
	/**
	 * modifie le vehicule
	 * @param vehicule
	 */
	public void updateVehicule(Vehicule vehicule);
	
	/**
	 * Récupére tous les vehicules
	 * @return la liste des vehicules
	 */
	public List<Vehicule> getAllVehicule();
	
	/**
	 * récupére un vehicule par son identifiant
	 * @param id l'identifiant
	 * @return le vehicule ou null si non existant
	 */
	public Vehicule getById(Integer id);

	public void removeVehiculeFromId(Integer id);

}
