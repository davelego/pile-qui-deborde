<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>List of all the members</title>
	</head>
	
	<body>
	
		<a href="${createLink(uri: '/', absolute: true)}"><- Return to main page</a><br/><br/>
		
		<h2>Members</h2>
		
		<g:each var="m" in="${members}">
			<div class="memberInfo">
				<g:link controller="member" action="myAccount" id="${m.id}">${m.pseudo}</g:link>         <br/>
				birth			 : ${m.dateNaissance.toString().substring(0, 10)} <br/>
				Reputation       : ${m.reputation}      <br/>
				Role			 : ${m.role}			<br/>
				Badges           :
				<g:if test="${m.badges}"> 
					<g:each var="b" in="${m.badges}">${b.name} </g:each>
				</g:if>
				<g:else>
					none
				</g:else>
			</div>
		</g:each>
		
	</body>
	
</html>