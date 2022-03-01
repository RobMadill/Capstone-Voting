package ca.sheridancollege.madillro.database;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import ca.sheridancollege.madillro.bean.CapstoneRatingSystem;
@SpringBootTest
@AutoConfigureTestDatabase
public class TestDatabaseAccess {
	@Autowired
	private DatabaseAccess da;
	
	@Test
	public void whenSaveCalendar_GetCalendarList() {
		int size = da.findAll().size();
		CapstoneRatingSystem capstone = new CapstoneRatingSystem();
		Long num = (long)1;
		capstone.setId(num);		
		capstone.setTitle("Test");
		capstone.setTeam("Test");
		capstone.setVideoLink("Test");
		capstone.setVideoDescription("Test");
		capstone.setRank(9);
		da.save(capstone);
		assertEquals(da.findAll().size(), ++size);
	}
}
