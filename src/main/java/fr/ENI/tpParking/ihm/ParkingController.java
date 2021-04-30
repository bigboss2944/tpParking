package fr.ENI.tpParking.ihm;

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
import fr.ENI.tpParking.bo.Parking;

@Controller
public class ParkingController {
	
	@Autowired
	ParkingManager parkingManager;
	
	
	@GetMapping("/tpParking/parking/saisie")
	public String entreSaisie(Parking parking) {
		return "addParking";
	}
	
	@PostMapping("/tpParking/parking/add")
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
		return "redirect:/tpParking/parking/index"; // n'appelle pas l'html mais l'url

	}
	
	@GetMapping("/tpParking/parking/index")
	public String listParkings(Model model) {
		model.addAttribute("parkings", parkingManager.getAllParking());
		return "indexParking";
	}
	
	@GetMapping("/tpParking/parking/edit/{id}")
	public String showUpdateForm(@PathVariable("id") Integer idParking, Model model) {
		Parking parking = parkingManager.getParkingById(idParking);
		model.addAttribute("parking", parking);
		return "updateParking";
	}
	
	
	@PostMapping("/tpParking/parking/update/{id}")
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
		return "redirect:/tpParking/parking/index";
	}
	
	@GetMapping("/tpParking/client/delete/{id}")
	public String deleteParking(@PathVariable("id") Integer idParking, Model model) {	
		parkingManager.deleteParking(idParking);
		
	    return "redirect:/client/index";
	}
	
	
}
