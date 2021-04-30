package fr.ENI.tpParking.bo;

import java.time.LocalDateTime;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Ticket {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer idTicket;
	@ManyToOne
	private Parking parking;
	@ManyToOne
	private Vehicule vehicule;
	
	private LocalDateTime dateHeureArrivee;
	private LocalDateTime dateHeureDepart;
	/**
	 * @param parking
	 * @param vehicule
	 * @param dateHeureArrivee
	 */
	public Ticket(Parking parking, Vehicule vehicule, LocalDateTime dateHeureArrivee) {
		this.parking = parking;
		this.vehicule = vehicule;
		this.dateHeureArrivee = dateHeureArrivee;
	}
	
	
	
	

}
