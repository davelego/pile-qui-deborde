<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>List of all the tags</title>
	</head>
	
	<body>
	
		<a href="${createLink(uri: '/', absolute: true)}"><- Return to main page</a><br/><br/>
	
		<g:each var="t" in="${tags}">
			<div>
				Id    		 : ${t.id}            <br/>
				Tag 		 : ${t.word}		  <br/>
				Description  : ${t.description}  <br/>
				
			</div>
			
		</g:each>
	
	</body>
	
</html>