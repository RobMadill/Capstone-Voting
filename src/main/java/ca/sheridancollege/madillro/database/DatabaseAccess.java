package ca.sheridancollege.madillro.database;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.madillro.bean.CapstoneRatingSystem;

	@Repository
	public class DatabaseAccess {
		@Autowired
		NamedParameterJdbcTemplate jdbc;

		public List<CapstoneRatingSystem> findAll() {
			MapSqlParameterSource namedParameters = new MapSqlParameterSource();
			String query = "SELECT * FROM capstone ORDER BY rank DESC";
			return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<CapstoneRatingSystem>(CapstoneRatingSystem.class));
		}

		public CapstoneRatingSystem findById(Long id) {
			MapSqlParameterSource namedParameters = new MapSqlParameterSource();
			String query = "SELECT * FROM capstone WHERE id = :id";
			namedParameters.addValue("id", id);
			return jdbc.query(query, namedParameters, new BeanPropertyRowMapper<CapstoneRatingSystem>(CapstoneRatingSystem.class)).get(0);

		}

		public Long save(CapstoneRatingSystem capstone) {
			MapSqlParameterSource namedParameters = new MapSqlParameterSource();
			KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
			String query = "INSERT INTO capstone(title, team, videoLink, videoDescription, rank) "
					+ "VALUES(:title, :team, :videoLink, :videoDescription, :rank)";
			namedParameters.addValue("title", capstone.getTitle());
			namedParameters.addValue("team", capstone.getTeam());
			namedParameters.addValue("videoLink", capstone.getVideoLink());
			namedParameters.addValue("videoDescription", capstone.getVideoDescription());
			namedParameters.addValue("rank", capstone.getRank());
			jdbc.update(query, namedParameters, generatedKeyHolder);
			return (Long) generatedKeyHolder.getKey();
		}
		
		public void updateCapstoneById(Long id) {
			MapSqlParameterSource namedParameters = new MapSqlParameterSource();
			String query = "UPDATE capstone SET rank = rank+1 WHERE id = :id";
			namedParameters.addValue("id", id);
			int rowsAffected = jdbc.update(query, namedParameters);
			if (rowsAffected > 0) {
				System.out.println("Vote for capstone " + id + " from database");
			}
		}
}
