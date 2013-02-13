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
				Id     : ${q.id}            <br/>
				Author : ${q.author.pseudo} <br/>
				Title  : ${q.title}         <br/>
				Tags   : ${q.tags}          <br/>
				Body   : ${q.body}          <br/>
				<g:form controller="question" params="${[idquestion: q.id]}">
					<g:actionSubmit value="Edit"   action="edit" />
				</g:form>
				<g:form controller="answer" params="${[idquestion: q.id]}">
					<g:actionSubmit value="Answer" action="answer"/>
				</g:form>
				<g:form controller="answer" params="${[idquestion: q.id]}">
					<g:actionSubmit value="List answers" action="list"/>
				</g:form><br/>
			</div>
			
		</g:each>
	
	</body>
	
</html>