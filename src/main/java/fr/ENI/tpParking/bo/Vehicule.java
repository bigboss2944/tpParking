package fr.ENI.tpParking.bo;

import java.util.ArrayList;
import java.util.List;

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
public class Vehicule {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idVehicule;
	private String immat;
	private String marque;
	private String modele;
	
	@OneToMany(mappedBy="vehicule")
	private List<Ticket> listTicket =new ArrayList<>();
	
	/**
	 * @param immat
	 * @param marque
	 * @param modele
	 */
	public Vehicule(String immat, String marque, String modele) {
		this.immat = immat;
		this.marque = marque;
		this.modele = modele;
	}


}
