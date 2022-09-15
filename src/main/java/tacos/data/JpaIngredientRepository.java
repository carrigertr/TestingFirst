package tacos.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import tacos.Ingredient;

@Repository
public class JpaIngredientRepository implements IngredientRepository {

	private JdbcTemplate jdbc;
	
	@Autowired
	private IngredientRepository ingredientRepository;

	@Autowired
	public JpaIngredientRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	@Override
	public Iterable<Ingredient> findAll() {
		return jdbc.query("select id, name, type from Ingredient", this::mapRowToIngredient);

	}

	private Ingredient mapRowToIngredient(ResultSet rs, int rowNum) throws SQLException {
		return new Ingredient(rs.getString("id"), rs.getString("name"), Ingredient.Type.valueOf(rs.getString("type")));
	}

	@Override
	public Ingredient save(Ingredient theIngredient) {
		jdbc.update("insert into Ingredient (id, name, type) values (?, ?, ?)", theIngredient.getId(),
				theIngredient.getName(), theIngredient.getType().toString());
		return theIngredient;
	}

	@Override
	public <S extends Ingredient> Iterable<S> saveAll(Iterable<S> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Optional<Ingredient> findById(String id) {
		return ingredientRepository.findById(id);
	}

	@Override
	public boolean existsById(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<Ingredient> findAllById(Iterable<String> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(String id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Ingredient entity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAllById(Iterable<? extends String> ids) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll(Iterable<? extends Ingredient> entities) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		
	}
}
