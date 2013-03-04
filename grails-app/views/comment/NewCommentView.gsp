<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'questionstyle.css')}" type="text/css">
		<title>Answer to the question</title>
	</head>
	
	<body>
	
		<a href="${createLink(uri: '/', absolute: true)}"><- Return to main page</a><br/><br/>
		
		<g:renderErrors bean="${comment}" />
			<g:form name="formnewcomment" params="${[idquestion: question.id]}">	
				<fieldset>
					<legend>Answer to :</legend>
					
					<p>
						<label for="title">Title</label>
						<g:textField name="title" type="text" size="30" value="${question.title}" readonly="readonly"/>
					</p>
					
					<p>
						<label for="title">Tags</label>
						<g:textField name="tags" type="text" size="30" value="${question.tags}" readonly="readonly" />
					</p>
					
					<p>
						<label for="qbody">Body</label>
						<g:textArea name="qbody" cols="50" rows="10" value="${question.body}" readonly="readonly" />
					</p>
				
					<legend>Please type your comment :</legend>
					
					<p class='${hasErrors(bean:comment,field:'body','errors')}'>					
						<label for="body">Body</label>
						<g:textArea name="body" cols="50" rows="10" value="${comment?.body}" />				
					</p>
					
					<p class="submit">
						<g:actionSubmit value="Submit" action="save"/>
					</p>
					
				</fieldset>								
			</g:form>
		
		</body>
</html>