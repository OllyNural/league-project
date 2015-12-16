<html>
<%@ include file="../common/header.jsp" %>
<script>
	$(document).ready( function(){
		startUniversityRanking();
	});
</script>

<body>
<div class="row">
	<div class="col-md-8 col-md-offset-2 mainUniversityDiv">
		<h1>Results</h1>
		<table id="resultsTableUniversity">
			<tr>
				<th>Ranking</th>
				<th>Username</th>
				<th>Summoner ID</th>
				<th>Summoner Level</th>
				<th>Tier</th>
				<th>Division</th>
				<th>League Points</th>
			</tr>
		</table>
		<br>
		<br>
	</div>
</div>
</body>

</html>