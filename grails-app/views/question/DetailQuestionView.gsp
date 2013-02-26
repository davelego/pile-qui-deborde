<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>List of all the questions</title>
	</head>
	
	<body>
	
		<a href="${createLink(uri: '/', absolute: true)}"><- Return to main page</a><br/><br/>
	
		
			<div class="content">
				<h2>Question</h2> <br/>
				Title  : <g:link action="detail" id="${q.id}">${q.title}</g:link>       <br/> <br/>			
				Tags   :  <g:each var="t" in="${q.tags}"> <span class="tag">${t.word}</span> </g:each>           <br/>
				
				<div class="questionBody">${q.body}</div>
				by : ${q.author.pseudo} 												<br/>	
				<br/>
				<g:if test="${session.user.id == q.author.id }">
					<g:link controller="question" action="edit" params="${[idquestion: q.id]}">
						<input value="Edit"/>
					</g:link>
				</g:if>
				<g:link controller="answer" action="answer" params="${[idquestion: q.id]}">
					<input value="Answer"/>
				</g:link>
				
				
				
				<div>
					<h2>Answers</h2> <br/>
					<g:each var="a" in="${q.answers}">
						     ${a.body} 				<br/>
						by : ${a.author.pseudo} 	<br/>
					</g:each>
				</div>

			</div>
			
		
	
	</body>
	
</html>