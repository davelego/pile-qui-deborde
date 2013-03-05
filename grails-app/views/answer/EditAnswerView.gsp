<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'questionstyle.css')}" type="text/css">
		<title>Edit the following question</title>
	</head>
	
	<body>
		<div id="container">
		
			<a href="${createLink(uri: '/', absolute: true)}"><- Return to main page</a><br/><br/>
		
			<g:renderErrors bean="${question}" />
			<g:form name="formeditanswer" params="${[idanswer: answer.id]}">	
				<fieldset>
					<legend>Edit your answer :</legend>
					
					<p class='${hasErrors(bean:question,field:'body','errors')}'>
						<label for="body">Question</label>
						<g:textArea name="body" cols="50" rows="10" value="${answer.body}" />
					</p>
					
					<p class="submit">
						<g:actionSubmit value="Submit" action="editAnswer"/>
					</p>
					
				</fieldset>								
			</g:form>
		</div>
	</body>