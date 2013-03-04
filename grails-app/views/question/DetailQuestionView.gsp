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
				Tags   : <g:each var="t" in="${q.tags}"> <span class="tag">${t.word}</span> </g:each>           <br/>
				
				<div class="questionBody">${q.body}</div>
				by : ${q.author.pseudo} 												<br/>	
				<br/>
				<g:if test="${session.user.id == q.author.id  || session.user.role == "admin"}">
					<g:link controller="question" action="edit" params="${[idquestion: q.id]}">
						<input value="Edit"/>
					</g:link>
				</g:if>
				<g:link controller="answer" action="answer" params="${[idquestion: q.id]}">
					<input value="Answer"/>
				</g:link>
				<g:link controller="comment" action="comment" params="${[idpost: q.id]}">
					<input value="Comment" />
				</g:link>
			
				<br/><br/>
				<h2>Comments</h2> <br/>
				<g:each var="c" in="${q.comments}">
					     ${c.body} 				<br/>
					by : ${c.author.pseudo} 	<br/>
				</g:each> <br/>
			
				<h2>Answers</h2> <br/>
					<g:each var="a" in="${q.answers}">
					     ${a.body} 				<br/>
					by : ${a.author.pseudo} 	<br/>
					
					<g:link controller="comment" action="comment" params="${[idpost: a.id]}">
						<input value="Comment" />
					</g:link><br/><br/>
					
					<h4>Comments : </h4>
					<g:each var="com" in="${a.comments}">
						     ${com.body} 				<br/>
						by : ${com.author.pseudo} 	<br/>
					</g:each> <br/>
					
				</g:each>
				
			</div>

	</body>
</html>