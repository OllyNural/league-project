<html>
<%@ include file="../common/header.jsp" %>
<script>

</script>

<body>
<div class="row">
	<div class="col-md-8 col-md-offset-2 mainRegisterDiv">
		<div class="register-form">
			<input type="text" name="summonerRegisterName" placeholder="Summoner Name">
			<select name="universityRegisterName">
				<option value="none">Please Select a University</option>
				<option value="UKC">The University of Kent</option>
				<option value="UEA">The University of East Anglia</option>
				<option value="UKC">The University of Kent</option>
				<option value="UKC">The University of Kent</option>
				<option value="UKC">The University of Kent</option>
				<option value="UKC">The University of Kent</option>
				<option value="UKC">The University of Kent</option>
				<option value="UKC">The University of Kent</option>
			</select>
			<input type="submit" class="submit-register" value="Add Summoner" onclick="registerSummoner(this)">
		</div>
	</div>
</div>
</body>

</html>