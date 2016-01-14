var APIKey = '896349ec-4ce2-49ed-be1a-b480bf0c7d49';

/*$(document).ready(function(){
	$('#input-ticket').keypress(function(e){
	if(e.keycode==13)
	$('#input-dubmit').click();
	});
});*/

/**
 * Functions to allow registering a player on /register
 */

function registerSummoner(form) {
	var registerSummonerName = $(form).parent().find('input[name="summonerRegisterName"]').val();
	var registerSummonerUniversity = $(form).parent().find('select[name="universityRegisterName"]').val();
	console.log(registerSummonerName);
	console.log(registerSummonerUniversity);
	
	$.post('register', 
			{summonerName : "" + registerSummonerName,
				summonerUniversity : "" + registerSummonerUniversity},
			function(data){
					switch (data) {
						case 0:
							console.log("Name Exists, but no ranking");
							break;
						case 1:
							console.log("Name and ranking exist");
							break;
						case -1:
							console.log("Neither exist");
							break;
					}
				});
	
	/*$.ajax({
		type: 'POST',
		url: 'register',
		data: { summonerName : "" + registerSummonerName,
				summonerUniversity : "" + registerSummonerUniversity},
		success: function(response){
			console.log(response);
			switch(response) {
				case 1:
					console.log("Username not found");
					break;
				case 0:
					console.log("Username found");
					break;
				case -1:
					console.log("Database error");
					break;
			}
		}
	});*/
	console.log("End");
}


/** Functions to show a single profile view on /Player?userName=(name)
 */

function startSearchSummoner(form) {
	var summonerNameInput = $(form).parent().find('input[name="summonerName"]');
	var summonerName = $(summonerNameInput).val();
	var url = '/Project/player?summonerName='+summonerName;
	location.href = url;
}

function searchSummoner(form) {
	var summonerNameInput = $(form).parent().find('input[name="summonerName"]');
	var summonerName = $(summonerNameInput).val();
	console.log(summonerName);
	
	if (summonerName !== "") {
		returnSingleSummoner(summonerName, 'resultsTablePlayer');
	} else {
		alert("Please enter a name!");
	} 
	$(summonerNameInput).val('');
}

function returnSingleSummoner(summonerName, summonerId, divId) {

}

function handleSingleSearchSummoner(basicInfo, divId) {
	$('#'+divId).append('<tr><td><img src="http://ddragon.leagueoflegends.com/cdn/5.17.1/img/profileicon/'+basicInfo[1]+
			'.png"></td><td><a href="https://www.google.com">'+basicInfo[2]+
			'</a></td><td>'+basicInfo[3]+
			'</td><td>'+basicInfo[4]+
			'</td><td>'+basicInfo[1]+
			'</td><td>'+
			'<div class="delete-button-div">'+
			'<img src="../Photos/delete-cross.png" style="width:15px;height:15px" onclick="removeSingleSummoner(this);"></div>'+
			'</td></tr>');
	
	$('#playerProfileDiv').append('<div class="playerIcon"><img class="center-block" src="http://ddragon.leagueoflegends.com/cdn/5.17.1/img/profileicon/'+basicInfo[1]+
			'.png"></div>');
}

/**
 * Functions to show University Rankings
 */

var UniversityList = [];

function startUniversityRanking() {
	var list1 = ["Lazrkiller", "kaibai", "theblackspectre", "morse0", "Aloadofbarnacles", "Slakkenrock", "MufBustinSince97", "Kritya", "Kirsty"];
	var list2 = ["NeonTalim101", "TeemoIsntThatBad", "Punksternuts", "heejin17"];
	
	var mainArray = [];
	mainArray.push(list1);
	mainArray.push(list2);
	
	returnUSingleSummoner(mainArray, 'resultsTableUniversity');
	//showUniversityRanking(UniversityList, 'resultsTableUniversity');
}

function startUSearchSummoner(form) {
	var summonerNameInput = $(form).parent().find('input[name="summonerName"]');
	var summonerName = $(summonerNameInput).val();
	var url = '/Project/player?userName='+summonerName;
	location.href = url;
}

function searchUSummoner(form) {
	var summonerNameInput = $(form).parent().find('input[name="summonerName"]');
	var summonerName = $(summonerNameInput).val();
	console.log(summonerName);
	
	if (summonerName !== "") {
		returnSingleSummoner(summonerName, 'resultsTablePlayer');
	} else {
		alert("Please enter a name!");
	} 
	$(summonerNameInput).val('');
}

function getSummonerById(summonerList){
	for(i = 0; i < summArray.length; i++){
		
	}
}

function returnUSingleSummoner(summArray, divId) {
	for (i = 0; i < summArray.length; i++) {
		var summonerName = [];
		summonerName = summArray[i];
		var summURL = 'https://euw.api.pvp.net/api/lol/euw/v1.4/summoner/by-name/' + summonerName +'?api_key=' + APIKey;
		var allRankedInfo = [];
		var basicInfoUniversity = [];
		$.ajax({
			url: summURL,
			type: 'GET',
			dataType: 'json',
			async: false,
			data: {},
			success: function(json) {
					//Get data
				for(x = 0; x < summArray[i].length; x++){
						//Get data
					var summonerNameNew = summArray[i][x].toLowerCase().trim();
					console.log(summonerNameNew);
					summonerID = json[summonerNameNew].id;
						//Put data in array, and put array in main array for each name provided
					summArray[i][x] = summonerID;
					console.log(summArray[i][x]);
				}
				console.log(summArray[i]);
				console.log(summArray);
					//New data for second JSON search
				var soloQueueInfo = [];
				var rankedURL = 'https://euw.api.pvp.net/api/lol/euw/v2.5/league/by-summoner/' + summArray[i] + '/entry?api_key=' + APIKey;
				
				$.ajax({
					url: rankedURL,
					type: 'GET',
					dataType: 'json',
					async: false,
					data: {},
					success: function(json) {
							//get data and push to array
						for(y = 0; y < summArray[i]; y++){
							soloQueueInfo.push(summonerName);
							soloQueueInfo.push(json[summonerID][0].tier);
							soloQueueInfo.push(json[summonerID][0].entries[0].division);
							soloQueueInfo.push(json[summonerID][0].entries[0].leaguePoints);
								//Add array to main array
							UniversityList.push(soloQueueInfo);
							console.log(UniversityList);
						}
					},
					error: function (XMLHttpRequest, textStatus, errorThrown) {
						console.log("Something went wrong with second GET");
						console.log(errorThrown);
					}
				})
				
			},
			error: function (XMLHttpRequest, textStatus, errorThrown) {
				console.log("Something went wrong with first GET");
				console.log(errorThrown);
			}
		})
	}
	showUniversityRanking(UniversityList, 'resultsTableUniversity');
}

function showUniversityRanking(allRankedInfo, divId) {
		//Define colours for ranking
	var tierDiv = "";
	console.log(allRankedInfo);
	switch(allRankedInfo[1][0]) {
		case "CHALLENGER":
			tierDiv += '<div style="font-weight: strong;color: #FFEC00;">Challenger</div>'
				break;
		case "MASTER":
			tierDiv += '<div style="font-weight: strong;color: #C8B337;">Master</div>'
				break;
		case "DIAMOND":
			tierDiv += '<div style="font-weight: strong;color: #4EE2EC;">Diamond</div>'
			break;
		case "PLATINUM":
			tierDiv += '<div style="font-weight: strong;color: #3E9DC1;">Platinum</div>'
			break;
		case "GOLD":
			tierDiv += '<div style="font-weight: strong;color: #FDD017;">Gold</div>'
			break;
		case "SILVER":
			tierDiv += '<div style="font-weight: strong;color: #C0C0C0;">Silver</div>'
			break;
		case "BRONZE":
			tierDiv += '<div style="font-weight: strong;color: #CD7F32;">Bronze</div>'
			break;
			
	}
	
	//$('#'+divId).append('<tr><td><img src="http://ddragon.leagueoflegends.com/cdn/5.17.1/img/profileicon/'+allRankedInfo[0][1]+
	$('#'+divId).append('<tr><td>'+i+
			'</td><td><a href="https://www.google.com">'+allRankedInfo[0][2]+
			'</a></td><td>'+allRankedInfo[0][3]+
			'</td><td>'+allRankedInfo[0][4]+
			'</td><td>'+tierDiv+
			'</td><td>'+allRankedInfo[1][1]+
			'</td><td>'+allRankedInfo[1][2]+
			'</td></tr>');
}
