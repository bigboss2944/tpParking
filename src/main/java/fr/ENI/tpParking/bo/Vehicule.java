package fr.ENI.tpParking.bo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Data
@Getter @Setter
@NoArgsConstructor
//@JsonIdentityInfo(
//generator = ObjectIdGenerators.PropertyGenerator.class, 
//property = "idVehicule")

public class Vehicule {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idVehicule;
	private String immat;
	private String marque;
	private String modele;
	
	@OneToMany(mappedBy="vehicule")
	@JsonBackReference
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
