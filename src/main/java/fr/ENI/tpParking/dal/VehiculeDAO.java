package fr.ENI.tpParking.dal;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


import fr.ENI.tpParking.bo.Vehicule;

public interface VehiculeDAO extends CrudRepository<Vehicule, Integer> {

	List<Vehicule> findByImmat(String immat);

}
