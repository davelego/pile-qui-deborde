<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'memberstyle.css')}" type="text/css">
		<title><g:message code="default.label.login"/></title>
	</head>
	
	<body>
		<div id="container">
		
			<a href="${createLink(uri: '/', absolute: true)}"><- <g:message code="default.label.mainpage"/></a><br/><br/>
				
			<g:form name="formnewmember">	
				<fieldset>
					<legend><g:message code="default.label.pleaselogin"/></legend>
					<P>
						<label for="pseudo"><g:message code="default.label.pseudo"/></label>
						<g:textField type="text" name="pseudo" size="30" />
					</P>
					<p>
						<label for="password"><g:message code="default.label.password"/></label>
						<g:passwordField type="password" name="password" size="30" />
					</p>					
					<p class="submit">
						<g:actionSubmit value="Submit" action="login"/>
					</p>	
				</fieldset>								
			</g:form>
		</div>
	</body>