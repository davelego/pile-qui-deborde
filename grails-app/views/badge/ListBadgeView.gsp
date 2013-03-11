<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>List of all the badges</title>
	</head>
	
	<body>
	
		<a href="${createLink(uri: '/', absolute: true)}"><- Return to main page</a><br/><br/>
	
		<h2>Badges</h2><br/>
	
		<g:each var="b" in="${badges}">
			<div class="oneFullTag">
				<div class="questionTag"><span class="tag"> <g:link controller="member" action="membersByBadge" id="${b.id}">${b.name}</g:link></span></div>
			</div>
		</g:each>
	
	</body>
	
</html>