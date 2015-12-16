<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<%@ include file="../common/header.jsp" %>
	
	<div class="row">
		<div class="col-md-2 col-md-offset-1 col-sm-6 col-xs-12 mainDiv">
			<div class="summonerSearchFormMain">
				<%@ include file="../common/summonerSearchForm.jsp" %>
			</div>
		</div>
		<div class="col-md-6 col-md-offset-1 col-sm-6 col-xs-12 mainDiv">
			<h1>Results</h1>
			<table id="resultsTableSingle">
				<tr>
					<th>Summoner Photo</th>
					<th>Username</th>
					<th>Summoner ID</th>
					<th>Summoner Level</th>
					<th>Summoner Photo ID</th>
					<th>Delete</th>
				</tr>
			</table>
		</div>
	</div>
</body>
</html>