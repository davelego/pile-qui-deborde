<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'memberstyle.css')}" type="text/css">
		<title>Login</title>
	</head>
	
	<body>
		<div id="container">			
			<g:form name="formLogin">	
				<fieldset>
					<legend>Please login :</legend>
										<P>
						<label for="pseudo">Pseudo</label>
						<g:textField type="text" name="pseudo" size="30" />
					</P>
					<p>
						<label for="password">Password</label>
						<g:passwordField type="password" name="password" size="30" />
					</p>					
					<p class="submit">
						<g:actionSubmit value="Submit" action="login"/>
					</p>	
				</fieldset>								
			</g:form>
		</div>
	</body>