package fr.ENI.tpParking.bo;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Generated;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Parking {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idParking;
	private String adresse;
	private Integer nbrePlaces;
	private Float tarifHoraire;
	
	@OneToMany(mappedBy="parking")
	private List<Ticket> listTicket =new ArrayList<>();

	/**
	 * @param adresse
	 * @param nbrePlaces
	 * @param tarifHoraire
	 */
	public Parking(String adresse, Integer nbrePlaces, Float tarifHoraire) {
		this.adresse = adresse;
		this.nbrePlaces = nbrePlaces;
		this.tarifHoraire = tarifHoraire;
	}
	
	
	
	

}
