<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'memberstyle.css')}" type="text/css">
		<title>Add a new member</title>
	</head>
	
	<body>
		<div id="container">			
			<g:form name="formnewmember">	
				<fieldset>
					<legend>Please, fill the following form :</legend>
					<p class="first">					
						<label for="firstname">First name</label>
						<g:textField name="firstname" type="text" size="30"/>					
					</p>
					<p class="first">					
						<label for="lastname">Last name</label>				
						<g:textField name="lastname" type="text" size="30"/>					
					</p>
					<p>					
						<label for="email">Email</label>				
						<g:textField type="text" name="email" size="30" />				
					</p>
					<p>
						<label for="bio">Bio</label>
						<g:textArea name="bio" cols="30" rows="10" />
					</p>	
					<p>
						<label for="web">Web site</label>				
						<g:textField type="text" name="website" size="30" />										
					</p>
					<p>
						<label for="">Avatar</label>
						<g:textField type="text" name="website" size="30" />
					</p>	
					
					<p class="submit">
						<g:submitButton name="Valider"/>
					</p>	
				</fieldset>								
			</g:form>	
		</div>
	</body>