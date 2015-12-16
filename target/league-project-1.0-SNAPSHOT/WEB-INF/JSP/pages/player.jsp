<html>
<%@ include file="../common/header.jsp" %>
<script>
	$(document).ready( function(){
		returnSingleSummoner('${userName}', '${id}', 'resultsTablePlayer');
	});
</script>

<body>
<div class="row">
	<div class="col-md-2 col-md-offset-1 mainDiv" id="playerProfileDiv">
		<div class="playerName">
			<h2>${userName}</h2>
		</div>
	</div>
	<div class="col-md-8 col-md-offset-1 playerGamesDiv">
		<h1>Results</h1>
		<table id="resultsTablePlayer">
			<tr>
				<th>Summoner Photo</th>
				<th>Username</th>
				<th>Summoner ID</th>
				<th>Summoner Level</th>
				<th>Tier</th>
				<th>Division</th>
				<th>League Points</th>
				<th><%= request.getAttribute("userName") %></th>
			</tr>
		</table>
		<br>
		<br>
	</div>
</div>
</body>

</html>