package fr.ENI.tpParking.ihm;



import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import fr.ENI.tpParking.bll.ticket.TicketManager;
import fr.ENI.tpParking.bll.vehicule.VehiculeManager;
import fr.ENI.tpParking.bll.vehicule.VehiculeManagerException;
import fr.ENI.tpParking.bo.Vehicule;


@Controller
public class VehiculeController {

	@Autowired
	VehiculeManager vehiculeManager;

	@GetMapping("/vehicule/saisie")
	public String entreSaisie(Vehicule vehicule) {
		return "addVehicule";
	}

	@PostMapping("/vehicule/add")
	public String addVehicule(@Valid Vehicule vehicule, BindingResult result, Model model) {

		if (result.hasErrors()) {
			return "addVehicule";
		}
		try {
			vehiculeManager.addVehicule(vehicule);
		} catch (VehiculeManagerException e) {
			result.rejectValue("immat", "error.vehicule", e.getMessage());
			return "addVehicule";
		}
		return "redirect:/vehicule/index"; // n'appelle pas l'html mais l'url

	}

	@GetMapping("/vehicule/index")
	public String listVehicules(Model model) {
		model.addAttribute("vehicules", vehiculeManager.getAllVehicule());
		return "indexVehicule";
	}

	@GetMapping("vehicule/edit/{id}")
	public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
		Vehicule vehicule = vehiculeManager.getById(id);
		model.addAttribute("vehicule", vehicule);
		return "updateVehicule";
	}

	@PostMapping("vehicule/update/{id}")
	public String updateVehicule(@PathVariable("id") Integer id, @Valid Vehicule vehicule, BindingResult result,
			Model model) {
		vehicule.setIdVehicule(id);
		if (result.hasErrors()) {
			return "updateVehicule";
		}
		System.out.println(vehicule);
		vehiculeManager.updateVehicule(vehicule);
		return "redirect:/vehicule/index";
	}
	
	@GetMapping("/vehicule/delete/{id}")
	public String deleteVehicule(@PathVariable("id") Integer id, Model model) {	
		vehiculeManager.removeVehiculeFromId(id);
		
	    return "redirect:/vehicule/index";
	}

}
