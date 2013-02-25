<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>List of all the questions</title>
	</head>
	
	<body>
	
		<a href="${createLink(uri: '/', absolute: true)}"><- Return to main page</a><br/><br/>
	
		<g:each var="q" in="${questions}">
			<div>
				<g:link action="detail" id="${q.id}">Title  : ${q.title}</g:link>         <br/>
				by : ${q.author.pseudo} <br/>				
				Tags   :  <g:each var="t" in="${q.tags}"> ${t.word}</g:each>           <br/>
			</div> 
		</g:each>
	
	</body>
	
</html>