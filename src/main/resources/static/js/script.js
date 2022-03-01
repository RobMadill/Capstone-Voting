function getCapstone(id){
	if(document.getElementById("capstone"+id).innerHTML==""){
		fetch('http://localhost:8080/getCapstone/'+ id).then(capstone =>capstone.json())
			.then(function(capstone) {
				var textToDisplay = "";
				textToDisplay += "Title: " + capstone.title + "<br>";
				textToDisplay += "Team Name: " + capstone.team + "<br>";
				textToDisplay += "Video Link: " + capstone.videoLink + "<br>";
				textToDisplay += "Video Description: " + capstone.videoDescription + "<br>";
				textToDisplay += "Video Rank: " + capstone.rank + "<br>";
				document.getElementById("capstone"+id).innerHTML=textToDisplay;
			});				
			} else {
				document.getElementById("capstone"+id).innerHTML="";
		}
}