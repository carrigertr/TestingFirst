package tacos;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


//@NoArgsConstructor(access=AccessLevel.PRIVATE, force = true)
@Data
@RequiredArgsConstructor
@Entity
public class Ingredient {
	
	@Id
	public final String id;
	public final String name;
	public final Type type;

	public static enum Type {
		WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
	}
}
