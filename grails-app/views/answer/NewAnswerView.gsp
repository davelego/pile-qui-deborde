<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'questionstyle.css')}" type="text/css">
		<g:javascript library='jquery' />
		<title><g:message code="default.label.answerquestion"/></title>
	</head>
	
	<body>
	
		<a href="${createLink(uri: '/', absolute: true)}"><- <g:message code="default.label.mainpage"/></a><br/><br/>
		
		<g:renderErrors bean="${answer}" />
			<g:form name="formnewquestion" params="${[idquestion: question.id]}">	
				<fieldset>
					<legend><g:message code="default.label.answerquestion"/> :</legend>
					
					<p>
						<label for="title"><g:message code="default.label.title"/></label>
						<g:textField name="title" type="text" size="30" value="${question.title}" readonly="readonly"/>
					</p>
					
					<p>
						<label for="tags"><g:message code="default.label.tags"/></label>
						<g:textField name="tags" type="text" size="30" readonly="readonly"/>
							<jq:jquery>
								$('#tags').val('')
							</jq:jquery>
						<g:each var="t" in="${question.tags}">
							<jq:jquery>
								$('#tags').val($('#tags').val() + '${t.word} ');                                
							</jq:jquery>
						</g:each>
					</p>
					
					<p>
						<label for="qbody"><g:message code="default.label.body"/></label>
						<g:textArea name="qbody" cols="50" rows="10" value="${question.body}" readonly="readonly" />
					</p>
				
					<legend><g:message code="default.label.typeanswer"/>r :</legend>
					
					<p  class='${hasErrors(bean:answer,field:'body','errors')}'>					
						<label for="body"><g:message code="default.label.body"/></label>
						<g:textArea name="body" cols="50" rows="10" value="${answer?.body}" />				
					</p>
					
					<p class="submit">
						<g:actionSubmit value="Submit" action="save"/>
					</p>
					
				</fieldset>								
			</g:form>
	
	</body>
</html>