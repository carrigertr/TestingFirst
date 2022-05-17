package tacos.data;

import tacos.Ingredient;

public interface IngredientRepository {
	
	Iterable<Ingredient> findAll();
	
	Ingredient findOne(String theId);
	
	Ingredient save(Ingredient theIngredient);	

}
