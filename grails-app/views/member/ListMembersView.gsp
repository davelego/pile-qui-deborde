<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>List of all the questions</title>
	</head>
	
	<body>
	
		<g:link url="${createLinkTo(dir:'')}"><- Return to main page</g:link><br/><br/>
		
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
				Inscription date : ${m.dateInscription} <br/><br/>
			</div>
		</g:each>
		
	</body>
	
</html>