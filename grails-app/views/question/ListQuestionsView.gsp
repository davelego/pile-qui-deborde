<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>List of all the questions</title>
	</head>
	
	<body>
		<a href='/pile-qui-deborde'>Retour a l'accueil</a><br/><br/>
	
		<g:each var="q" in="${questions}">
			<div>
				Id     : ${q.id}            <br/>
				Author : ${q.author.pseudo} <br/>
				Title  : ${q.title}         <br/>
				Tags   : ${q.tags}          <br/>
				Body   : ${q.body}          <br/>
				<g:form controller="question" params="${[idquestion: q.id]}">
					<g:actionSubmit value="Edit" action="edit" /><br/><br/>
				</g:form>
			</div>
			
		</g:each>
	
	</body>
	
</html>