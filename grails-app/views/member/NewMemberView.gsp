<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'memberstyle.css')}" type="text/css">
		<title>Add a new member</title>
	</head>
	
	<body>
	
		<div id="container">
		
			<a href="${createLink(uri: '/', absolute: true)}"><- Return to main page</a><br/><br/>
		
			<g:renderErrors bean="${member}" />
			<g:form name="formnewmember" >	
				<fieldset>
					<legend>New member, please fill the following form :</legend>
					
					<p class='${hasErrors(bean:member,field:'firstName','errors')}' >					
						<label for="firstname">First name</label>
						<g:textField name="firstname" type="text" size="30" value="${member?.firstName}" />					
					</p>
					
					<p class='${hasErrors(bean:member,field:'lastName','errors')}' >					
						<label for="lastname">Last name</label>				
						<g:textField name="lastname" type="text" size="30" value="${member?.lastName}" />					
					</p>
					
					<p class='${hasErrors(bean:member,field:'email','errors')}'>					
						<label for="email">Email</label>				
						<g:textField type="text" name="email" size="30" value="${member?.email}" />				
					</p>
					
					<p class='${hasErrors(bean:member,field:'pseudo','errors')}'>
						<label for="pseudo">Pseudo</label>
						<g:textField type="text" name="pseudo" size="30" value="${member?.pseudo}" />
					</p>
					
					<p class='${hasErrors(bean:member,field:'password','errors')}'>
						<label for="password">Password</label>
						<g:passwordField type="password" name="password" size="30" />
					</p>
					
					<p class='${hasErrors(bean:member,field:'password','errors')}'>
						<label for="repassword">Reconfirm</label>
						<g:passwordField type="repassword" name="repassword" size="30" />
					</p>
					
					<p class='${hasErrors(bean:member,field:'birthdate','errors')}'>
						<label for="birthdate">Birth date</label>
						<g:datePicker name="birthdate" precision="day" value="${member?.dateNaissance}" />
					</p>
					
					<p class='${hasErrors(bean:member,field:'bio','errors')}'>
						<label for="bio">Bio</label>
						<g:textArea name="bio" cols="30" rows="10" value="${member?.bio}" />
					</p>
						
					<p class='${hasErrors(bean:member,field:'website','errors')}'>
						<label for="website">Web site</label>				
						<g:textField type="text" name="website" size="30" value="${member?.website}" />										
					</p>
					
					<p class='${hasErrors(bean:member,field:'avatar','errors')}'>
						<label for="avatar">Avatar</label>
						<g:textField type="text" name="avatar" size="30" value="${member?.photoPath}" />
					</p>	
					
					<p class="submit">
						<g:actionSubmit value="Submit" action="saveNewMember"/>
					</p>	
					
				</fieldset>								
			</g:form>
		</div>
	</body>