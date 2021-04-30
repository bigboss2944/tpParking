package fr.ENI.tpParking.ihm;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.ENI.tpParking.bll.ParkingManager;
import fr.ENI.tpParking.bll.ParkingManagerException;
import fr.ENI.tpParking.bll.vehicule.VehiculeManager;
import fr.ENI.tpParking.bo.Parking;
import fr.ENI.tpParking.bo.Vehicule;

@Controller
public class ParkingController {
	
	@Autowired
	ParkingManager parkingManager;
	
	@Autowired
	VehiculeManager vehiculeManager;
	
	
	@GetMapping("/parking/saisie")
	public String entreSaisie(Parking parking) {
		return "addParking";
	}
	
	@PostMapping("/parking/add")
	public String addParking(@Valid Parking parking, BindingResult result, Model model){
		if (result.hasErrors()) {
			return "addParking";
		}
		try {
			parkingManager.addParking(parking);
		} catch (ParkingManagerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/parking/index"; // n'appelle pas l'html mais l'url

	}
	
	@GetMapping("/parking/index")
	public String listParkings(Model model) {
		model.addAttribute("parkings", parkingManager.getAllParking());
		return "indexParking";
	}
	
	@GetMapping("/parking/edit/{id}")
	public String showUpdateForm(@PathVariable("id") Integer idParking, Model model) {
		Parking parking = parkingManager.getParkingById(idParking);
		model.addAttribute("parking", parking);
		return "updateParking";
	}
	
	
	@PostMapping("/parking/update/{id}")
	public String updateParking(@PathVariable("id") Integer idParking, @Valid Parking parking, BindingResult result,
			Model model) {
		parking.setIdParking(idParking);
		if (result.hasErrors()) {
			return "updateParking";
		}
		try {
			parkingManager.updateParking(parking);
		} catch (ParkingManagerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "redirect:/parking/index";
	}
	
	@GetMapping("/parking/delete/{id}")
	public String deleteParking(@PathVariable("id") Integer idParking, Model model) {	
		parkingManager.deleteParking(idParking);
		
	    return "redirect:/parking/index";
	}
	
	@GetMapping("/parking/seGarer/{id}")
	public String seGarer(@PathVariable("id") Integer idParking, Model model, HttpServletRequest request) {
		
		Parking parking = parkingManager.getParkingById(idParking);
		try {
			HttpSession session = request.getSession();
			
			Integer idVehicule = (Integer) session.getAttribute("idVehicule");
			Vehicule vehicule = vehiculeManager.getById(idVehicule);
			parkingManager.addVehiculeToParking(idParking, vehicule);
		} catch (ParkingManagerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	    return "redirect:/parking/index";
	}
	
	
}
