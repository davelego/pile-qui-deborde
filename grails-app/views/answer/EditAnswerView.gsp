<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'questionstyle.css')}" type="text/css">
		<title><g:message code="default.label.editanswer"/></title>
	</head>
	
	<body>
		<div id="container">
		
			<a href="${createLink(uri: '/', absolute: true)}"><- <g:message code="default.label.mainpage"/></a><br/><br/>
		
			<g:renderErrors bean="${answer}" />
			<g:form name="formnewquestion" params="${[idanswer: answer.id]}">	
				<fieldset>
					<legend><g:message code="default.label.editanswer"/> :</legend>
					
					<p class='${hasErrors(bean:question,field:'body','errors')}'>
						<label for="body"><g:message code="default.label.answer"/></label>
						<g:textArea name="body" cols="50" rows="10" value="${answer.body}" />
					</p>
					
					<p class="submit">
						<g:actionSubmit value="Submit" action="editAnswer"/>
					</p>
					
				</fieldset>								
			</g:form>
		</div>
	</body>