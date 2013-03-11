<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Member for the badge ${badge.name}</title>
	</head>
	
	<body>
	
		<a href="${createLink(uri: '/', absolute: true)}"><- Return to main page</a><br/><br/>
	
		<h2>Members who have the badge ${badge.name}</h2><br/>
	
		<g:each var="m" in="${members}">
			<div>
				<g:link controller="member" action="myAccount" id="${m.id}">${m.pseudo}</g:link>
			</div>
		</g:each>
	
	</body>
	
</html>