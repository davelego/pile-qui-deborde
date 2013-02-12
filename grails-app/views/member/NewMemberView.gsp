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
					<legend>New member, please fill the following form :</legend>
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
					<P>
						<label for="pseudo">Pseudo</label>
						<g:textField type="text" name="pseudo" size="30" />
					</P>
					<p>
						<label for="password">Password</label>
						<g:passwordField type="password" name="password" size="30" />
					</p>
					<p>
						<label for="repassword">Reconfirm</label>
						<g:passwordField type="repassword" name="repassword" size="30" />
					</p>
					<p>
						<label for="birthdate">Birth date</label>
						<g:datePicker name="birthdate" precision="day"/>
					</p>
					<p>
						<label for="bio">Bio</label>
						<g:textArea name="bio" cols="30" rows="10" />
					</p>	
					<p>
						<label for="website">Web site</label>				
						<g:textField type="text" name="website" size="30" />										
					</p>
					<p>
						<label for="avatar">Avatar</label>
						<g:textField type="text" name="avatar" size="30" />
					</p>	
					
					<p class="submit">
						<g:actionSubmit value="Submit" action="saveNewMember"/>
					</p>	
				</fieldset>								
			</g:form>
		</div>
	</body>