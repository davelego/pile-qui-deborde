<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>My account</title>
	</head>
	
	<body>
	
		<a href="${createLink(uri: '/', absolute: true)}"><- Return to main page</a><br/><br/>
		
		
			<g:form name="formupdatemember" params="${[memberToEdit:member.id]}">	
				<fieldset>
					<legend>Profile details</legend>
					
					<p class='${hasErrors(bean:member,field:'firstName','errors')}' >					
						<label for="firstname">First name : </label>
						<g:if test="${edit}">
							<g:textField name="firstname" type="text" size="30" value="${member?.firstName}" />
						</g:if>	
						<g:else>	
							<label>${member?.firstName}</label>	
						</g:else>		
					</p>
					
					<p class='${hasErrors(bean:member,field:'lastName','errors')}' >					
						<label for="lastname">Last name : </label>	
						<g:if test="${edit}">			
							<g:textField name="lastname" type="text" size="30" value="${member?.lastName}" />	
						</g:if>
						<g:else>
							<label>${member?.lastName}</label>
						</g:else>					
					</p>
					
					<p class='${hasErrors(bean:member,field:'email','errors')}'>					
						<label for="email">Email : </label>	
						<g:if test="${edit}">			
							<g:textField type="text" name="email" size="30" value="${member?.email}" />	
						</g:if>
						<g:else>
							<label>${member?.email}</label>
						</g:else>				
					</p>
					
					<p class='${hasErrors(bean:member,field:'pseudo','errors')}'>
						<label for="pseudo">Pseudo</label>
						<g:if test="${edit}">	
							<g:textField type="text" name="pseudo" size="30" value="${member?.pseudo}" />
						</g:if>
						<g:else>
							<label>${member?.pseudo}</label>
						</g:else>	
					</p>
					
					<g:if test="${edit}">	
						<p class='${hasErrors(bean:member,field:'password','errors')}'>
							<label for="password">Password</label>
							<g:passwordField type="password" name="password" size="30" />
						</p>
					
						
						<p class='${hasErrors(bean:member,field:'password','errors')}'>
							<label for="repassword">Reconfirm</label>
							<g:passwordField type="repassword" name="repassword" size="30" />
						</p>
					</g:if>
					
					<p class='${hasErrors(bean:member,field:'birthdate','errors')}'>
						<label for="birthdate">Birth date</label>
						<g:if test="${edit}">	
							<g:datePicker name="birthdate" precision="day" value="${member?.dateNaissance}" />
						</g:if>
						<g:else>
							<label>${member?.dateNaissance}</label>
						</g:else>	
					</p>
					
					<p class='${hasErrors(bean:member,field:'bio','errors')}'>
						<label for="bio">Bio</label>
						<g:if test="${edit}">	
							<g:textArea name="bio" cols="30" rows="10" value="${member?.bio}" />
						</g:if>
						<g:else>
							<label>${member?.bio}</label>
						</g:else>	
					</p>
						
					<p class='${hasErrors(bean:member,field:'website','errors')}'>
						<label for="website">Web site</label>		
						<g:if test="${edit}">			
							<g:textField type="text" name="website" size="30" value="${member?.website}" />
						</g:if>
						<g:else>		
							<label>${member?.website}</label>
						</g:else>									
					</p>
					
					<p class='${hasErrors(bean:member,field:'avatar','errors')}'>
						<label for="avatar">Avatar</label>
						<g:if test="${edit}">	
							<g:textField type="text" name="avatar" size="30" value="${member?.photoPath}" />
						</g:if>
						<g:else>
							<label>${member?.photoPath}</label>
						</g:else>	
					</p>	
					<g:if test="${edit}">					
						<p class="submit">
							<g:actionSubmit value="Save changes" action="updateProfile"/>
						</p>	
					</g:if>
					<g:else>
						<p class="submit">
							<g:actionSubmit value="Edit profile" action="edit"/>
						</p>
					</g:else>
				</fieldset>								
			</g:form>
			<div id="memberComments">
				<h4>Answers</h4>
				<hr/>
				<g:each var="a" in="${member.givenAnswers}">
						<g:link action="detail" controller="Question" params="${a.question}" id="${a.question.id}">${a.question.title}</g:link> <br/>
				</g:each> <br/>
			</div>
			<div id="memberQuestions">
				<h5>Questions</h5>
				<hr/>
				<g:each var="q" in="${member.questions}">
						<g:link action="detail" controller="Question" params="${q}" id="${q.id}">${q.title}</g:link> <br/>
				</g:each> <br/>
			</div>
	</body>
	
</html>