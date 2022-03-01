package ca.sheridancollege.madillro.bean;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CapstoneRatingSystem {
	private Long id;
	private String title;
	private String team;
	private String videoLink;
	private String videoDescription;
	private int rank = 0;	
	
	public CapstoneRatingSystem(String title, String team, String videoLink, String videoDescription) {
		this.title = title;
		this.team = team;
		this.videoLink = videoLink;
		this.videoDescription = videoDescription;
	}	
}