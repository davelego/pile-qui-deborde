<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>My account</title>
	</head>
	
	<body>
	
		<a href="${createLink(uri: '/', absolute: true)}"><- Return to main page</a><br/><br/>
		
			<div class="userInfo" style="width:100%">
				<div id="formUser" style="float:left;">
					<g:form name="formupdatemember" params="${[memberToEdit:member.id]}" method="post" enctype="multipart/form-data">	
						<fieldset>
							<legend>Profile details</legend>
							
							<p class='${hasErrors(bean:member,field:'firstName','errors')}' >					
								<label for="firstname"><g:message code="default.label.firstname"/> : </label>
								<g:if test="${edit}">
									<g:textField name="firstname" type="text" size="30" value="${member?.firstName}" />
								</g:if>	
								<g:else>	
									<label>${member?.firstName}</label>	
								</g:else>		
							</p>
							
							<p class='${hasErrors(bean:member,field:'lastName','errors')}' >					
								<label for="lastname"><g:message code="default.label.lastname"/> : </label>	
								<g:if test="${edit}">			
									<g:textField name="lastname" type="text" size="30" value="${member?.lastName}" />	
								</g:if>
								<g:else>
									<label>${member?.lastName}</label>
								</g:else>					
							</p>
							
							<p class='${hasErrors(bean:member,field:'email','errors')}'>					
								<label for="email"><g:message code="default.label.email"/> : </label>	
								<g:if test="${edit}">			
									<g:textField type="text" name="email" size="30" value="${member?.email}" />	
								</g:if>
								<g:else>
									<label>${member?.email}</label>
								</g:else>				
							</p>
							
							<p class='${hasErrors(bean:member,field:'pseudo','errors')}'>
								<label for="pseudo"><g:message code="default.label.pseudo"/> : </label>
								<g:if test="${edit}">	
									<g:textField type="text" name="pseudo" size="30" value="${member?.pseudo}" />
								</g:if>
								<g:else>
									<label>${member?.pseudo}</label>
								</g:else>	
							</p>
							
							<g:if test="${edit}">	
								<p class='${hasErrors(bean:member,field:'password','errors')}'>
									<label for="password"><g:message code="default.label.password"/> : </label>
									<g:passwordField type="password" name="password" size="30" />
								</p>
							
								
								<p class='${hasErrors(bean:member,field:'password','errors')}'>
									<label for="repassword"><g:message code="default.label.confirm"/> : </label>
									<g:passwordField type="repassword" name="repassword" size="30" />
								</p>
							</g:if>
							
							<p class='${hasErrors(bean:member,field:'birthdate','errors')}'>
								<label for="birthdate"><g:message code="default.label.birthdate"/> : </label>
								<g:if test="${edit}">	
									<g:datePicker name="birthdate" precision="day" value="${member?.dateNaissance}" />
								</g:if>
								<g:else>
									<label>${member?.dateNaissance}</label>
								</g:else>	
							</p>
							
							<p class='${hasErrors(bean:member,field:'bio','errors')}'>
								<label for="bio"><g:message code="default.label.bio"/> : </label>
								<g:if test="${edit}">	
									<g:textArea name="bio" cols="30" rows="10" value="${member?.bio}" />
								</g:if>
								<g:else>
									<label>${member?.bio}</label>
								</g:else>	
							</p>
								
							<p class='${hasErrors(bean:member,field:'website','errors')}'>
								<label for="website"><g:message code="default.label.website"/> : </label>		
								<g:if test="${edit}">			
									<g:textField type="text" name="website" size="30" value="${member?.website}" />
								</g:if>
								<g:else>		
									<label>${member?.website}</label>
								</g:else>									
							</p>
							<g:if test="${edit}">
								<p class='${hasErrors(bean:member,field:'avatar','errors')}'>
									<label for="avatar"><g:message code="default.label.avatar"/> : </label>
										
										<input size="30" type="file" id="avatar" name="avatar" accept="image/*"/>
									
								</p>
							</g:if>	
							
							<g:if test="${!edit}">
								<label><g:message code="default.label.reputation"/> : ${member?.reputation}</label><br/>
							</g:if>
							
							<g:if test="${!edit}">
								<label><g:message code="default.label.badges"/> : 
									<g:if test="${member?.badges}"> 
										<g:each var="b" in="${member?.badges}">${b.name} </g:each>
									</g:if>
									<g:else>
										<g:message code="default.label.none"/>
									</g:else>
								</label>
							</g:if>
							
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
				</div>
				<div id="avatar" style="float:right; margin-right: 30px;">
					<g:if test="${member.avatar}">
					  <img class="avatar"  src="${createLink(controller:'member', action:'avatar_image', id:member.id)}" />
					</g:if>
				</div>
			</div>
			<div style="width:100%;;float:left">
				<div id="memberComments">
					<h4><g:message code="default.label.answer"/></h4>
					<hr/>
					<g:each var="a" in="${member.givenAnswers}">
							<g:link action="detail" controller="Question" params="${a.question}" id="${a.question.id}">${a.question.title}</g:link> <br/>
					</g:each> <br/>
				</div>
				<div id="memberQuestions">
					<h5><g:message code="default.label.question"/></h5>
					<hr/>
					<g:each var="q" in="${member.questions}">
							<g:link action="detail" controller="Question" params="${q}" id="${q.id}">${q.title}</g:link> <br/>
					</g:each> <br/>
				</div>
			</div>
	</body>
	
</html>