<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'questionstyle.css')}" type="text/css">
		<title><g:message code="default.label.addtag"/></title>
	</head>
	
	<body>
		<div id="container">
		
			<a href="${createLink(uri: '/', absolute: true)}"><- <g:message code="default.label.mainpage"/></a><br/><br/>
		
			<g:renderErrors bean="${tag}" />
			<g:form name="formnewquestion">	
				<fieldset>
					<legend><g:message code="default.label.fillfields"/> :</legend>
					
					<p  class='${hasErrors(bean:tag,field:'word','errors')}'>					
						<label for="title"><g:message code="default.label.tags"/></label>
						<g:textField name="word" type="text" size="30" value="${tag?.word}" />					
					</p>
										
					<p class='${hasErrors(bean:tag,field:'description','errors')}'>
						<label for="body"><g:message code="default.label.description"/></label>
						<g:textArea name="description" cols="50" rows="10" value="${tag?.description}" />
					</p>
					
					<p class="submit">
						<g:actionSubmit value="Submit" action="save"/>
					</p>
					
				</fieldset>								
			</g:form>
		</div>
	</body>