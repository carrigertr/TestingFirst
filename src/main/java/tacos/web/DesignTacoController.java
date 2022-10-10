package tacos.web;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import lombok.extern.slf4j.Slf4j;
import tacos.Ingredient;
import tacos.Ingredient.Type;
import tacos.data.IngredientRepository;
import tacos.Taco;

@Slf4j
@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {
	
	@Autowired
	IngredientRepository ingredientRepository;
	
//	private final IngredientRepository ingredientRepo;
//	
//	@Autowired
//	public DesignTacoController(IngredientRepository ingredientRepo) {
//		this.ingredientRepo = ingredientRepo;
//	}
	
	
//	Ingredient myTestIng = new Ingredient("TRAV", "Travis", Type.CHEESE);
	
	@GetMapping
	public String showDesignForm(Model model) {
//		ingredientRepo.save(myTestIng);
		List<Ingredient> ingredients = new ArrayList<>();
		ingredientRepository.findAll().forEach(i -> ingredients.add(i));
		System.out.println("Size of ingredients is: " + ingredients.size());
		
		Type[] types = Ingredient.Type.values();
		for (Type type : types) {
			model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
		}
		model.addAttribute("design", new Taco());
		
		return "design";
		
		
		
		
		//OLD IMPLEMENTATION
//		List<Ingredient> ingredients = Arrays.asList(
//				new Ingredient("FLTO", "Flour Tortillas", Type.WRAP),
//				new Ingredient("COTO", "Corn Tortillas", Type.WRAP),
//				new Ingredient("GRBF", "Ground Beef", Type.PROTEIN), 
//				new Ingredient("CARN", "Carnitas", Type.PROTEIN),
//				new Ingredient("TMTO", "Diced Tomatoes", Type.VEGGIES), 
//				new Ingredient("LETC", "Lettuce", Type.VEGGIES),
//				new Ingredient("CHED", "Cheddar", Type.CHEESE), 
//				new Ingredient("JACK", "Monterrey Jack", Type.CHEESE),
//				new Ingredient("SLSA", "Salsa", Type.SAUCE), 
//				new Ingredient("SRCR", "Sour Cream", Type.SAUCE));
//
//		Type[] types = Ingredient.Type.values();
//
//		for (Type type : types) {
//			model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
//		}
//
//		model.addAttribute("design", new Taco());
//		log.info("Fetched the ingredients");
//
//		return "design";

	}

	private List<Ingredient> filterByType(List<Ingredient> ingredients, Type type) {
		log.info("Filtering by type");
		return ingredients.stream().filter(x -> x.getType().equals(type)).collect(Collectors.toList());

	}
	
	//when the design form is submitted - this is the POST call it is referencing
	@PostMapping
	public String processDesign(@Valid Taco design, Errors errors) {
		if (errors.hasErrors()) {
			return "design";
		}
		log.info("Processing design: " + design);
		return "redirect:/orders/current";
	}
	
	

}