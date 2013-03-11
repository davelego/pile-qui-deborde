<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>List of all the tags</title>
	</head>
	
	<body>
	
		<a href="${createLink(uri: '/', absolute: true)}"><- Return to main page</a><br/><br/>
	
		<g:each var="t" in="${tags}">
			<div class="oneFullTag">
				<div class="questionTag"><span class="tag"> <g:link controller="question" action="questionByTags" id="${t.id}">${t.word}</g:link></span></div>
				
				<div class="descTag">${t.description} </div> <br/>
				
			</div>
			
		</g:each>
	</body>
	
</html>