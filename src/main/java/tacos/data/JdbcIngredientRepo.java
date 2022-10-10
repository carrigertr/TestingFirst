package tacos.data;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import lombok.extern.slf4j.Slf4j;
import tacos.Ingredient;

@Slf4j
@Repository
public class JdbcIngredientRepo implements IngredientRepository {

	private JdbcTemplate jdbc;
	
	@Autowired
	public JdbcIngredientRepo(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	@Override
	public List<Ingredient> findAll() {
		log.info("Querying for the ingredients");

		try {
			return jdbc.query("select id, name, type from ingredient", this::mapRowToIngredient);


		} catch (Exception e) {
			log.error("Error while trying to get the ingredients");
			return null;
		}
	}

	@Override
	public Ingredient findOne(String id) {
		return jdbc.queryForObject("select id, name, type from Ingredient where id = ?", this::mapRowToIngredient, id);
	}

	@Override
	public Ingredient save(Ingredient ingredient) {
		System.out.println("This is the TYPE of the enum type: " + ingredient.getType().getClass());
		jdbc.update("insert into Ingredient (id, name, type) values (?, ?, ?)", ingredient.getId(), ingredient.getName(), ingredient.getType().toString());
		return ingredient;
	}
	
	private Ingredient mapRowToIngredient(ResultSet rs, int rowNum) throws SQLException {
		return new Ingredient (rs.getString("id"), rs.getString("name"), Ingredient.Type.valueOf(rs.getString("type")));
	}
	
	
	
	
}
