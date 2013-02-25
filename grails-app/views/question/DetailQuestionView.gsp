<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>List of all the questions</title>
	</head>
	
	<body>
	
		<a href="${createLink(uri: '/', absolute: true)}"><- Return to main page</a><br/><br/>
	
		
			<div>
				<h2>Question</h2> <br/>
				<g:link action="detail" id="${q.id}">Title  : ${q.title}</g:link>       <br/>
				by : ${q.author.pseudo} 												<br/>				
				Tags   :  <g:each var="t" in="${q.tags}"> ${t.word} </g:each>           <br/>
				
				<p>${q.body}</p>
				
				<br/>
				
				<div>
					<h2>Answers</h2> <br/>
					<g:each var="a" in="${q.answers}">
						     ${a.body} 				<br/>
						by : ${a.author.pseudo} 	<br/>
					</g:each>
				</div>

				<g:link controller="question" action="edit" params="${[idquestion: q.id]}">
					<input value="Edit"/>
				</g:link>
				<g:link controller="answer" action="answer" params="${[idquestion: q.id]}">
					<input value="Answer"/>
				</g:link>
				<g:link controller="answer" action="list" params="${[idquestion: q.id]}">
					<input value="List answers"/>
				</g:link><br/>
			</div>
			
		
	
	</body>
	
</html>