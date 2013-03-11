<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'questionstyle.css')}" type="text/css">
		<g:javascript library='jquery' />
		<title><g:message code="default.label.newcomment"/></title>
	</head>
	
	<body>
	
		<a href="${createLink(uri: '/', absolute: true)}"><- <g:message code="default.label.mainpage"/></a><br/><br/>
		
		<g:renderErrors bean="${comment}" />
			<g:form name="formnewquestion" params="${[idpost: post?.id]}">	
				<fieldset>
				
					<g:if test="${type == "question"}">
						<legend><g:message code="default.label.commentquestion"/></legend>
						
						<p>
							<label for="title"><g:message code="default.label.title"/></label>
							<g:textField name="title" type="text" size="30" value="${post.title}" readonly="readonly"/>
						</p>
						
						<p>
							<label for="by"><g:message code="default.label.by"/></label>
							<g:textField name="by" type="text" size="30" value="${post.author.pseudo}" readonly="readonly"/>
						</p>
						
						<p>
							<label for="tags"><g:message code="default.label.tags"/></label>
							<g:textField name="tags" type="text" size="30" readonly="readonly"/>
								<jq:jquery>
									$('#tags').val('')
								</jq:jquery>
							<g:each var="t" in="${post.tags}">
								<jq:jquery>
									$('#tags').val($('#tags').val() + '${t.word} ');                                
								</jq:jquery>
							</g:each>
						</p>
						
						<p>
							<label for="qbody"><g:message code="default.label.body"/></label>
							<g:textArea name="qbody" cols="50" rows="10" value="${post.body}" readonly="readonly" />
						</p>
					
					</g:if>
					<g:else>
						<legend><g:message code="default.label.commentanswer"/></legend>
						
						<p>
							<label for="abody"><g:message code="default.label.body"/></label>
							<g:textArea name="abody" cols="50" rows="10" value="${post.body}" readonly="readonly" />
						</p>
						
						<p>
							<label for="by"><g:message code="default.label.by"/></label>
							<g:textField name="by" type="text" size="30" value="${post.author.pseudo}" readonly="readonly"/>
						</p>
						
					</g:else>
					
					<legend><g:message code="default.label.typecomment"/></legend>
					
					<p class='${hasErrors(bean:comment,field:'body','errors')}'>					
						<label for="body"><g:message code="default.label.body"/></label>
						<g:textArea name="body" cols="50" rows="10" value="${comment?.body}" />				
					</p>
	
					<p class="submit">
						<g:actionSubmit value="Submit" action="save"/>
					</p>
					
				</fieldset>								
			</g:form>
		
		</body>
</html>