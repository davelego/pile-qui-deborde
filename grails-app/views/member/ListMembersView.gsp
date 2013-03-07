<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>List of all the members</title>
	</head>
	
	<body>
	
		<a href="${createLink(uri: '/', absolute: true)}"><- Return to main page</a><br/><br/>
		
		<g:each var="m" in="${members}">
			<div>
				Id               : ${m.id}              <br/>
				First name       : ${m.firstName}       <br/>
				Last name        : ${m.lastName}        <br/>
				Email            : ${m.email}           <br/>
				Pseudo           : ${m.pseudo}          <br/>
				Password         : ${m.password}        <br/>
				Bio              : ${m.bio}             <br/>
				Website          : ${m.website}         <br/>
				Avatar           : ${m.photoPath}       <br/>
				Birth date       : ${m.dateNaissance}   <br/>
				Inscription date : ${m.dateInscription} <br/>
				Reputation       : ${m.reputation}      <br/>
				Role			 : ${m.role}			<br/>
				Badges           :
				<g:if test="${m.badges}"> 
					<g:each var="b" in="${m.badges}">${b.name} </g:each>
				</g:if>
				<g:else>
					none
				</g:else>
			</div><br/>
		</g:each>
		
	</body>
	
</html>