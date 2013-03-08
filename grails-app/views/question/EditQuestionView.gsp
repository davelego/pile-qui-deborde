<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'questionstyle.css')}" type="text/css">
		<g:javascript library='jquery' />
		<title>Edit the following question</title>
	</head>
	
	<body>
		<div id="container">
		
			<a href="${createLink(uri: '/', absolute: true)}"><- Return to main page</a><br/><br/>
		
			<g:renderErrors bean="${question}" />
			<g:form name="formnewquestion" params="${[idquestion: question.id]}">	
				<fieldset>
					<legend>Edit your question :</legend>
					
					<p>					
						<label for="title">Title</label>
						<g:textField name="title" type="text" size="30" readonly="readonly" value="${question?.title}" />					
					</p>
					
					<p class='${hasErrors(bean:question,field:'tags','errors')}'>					
						<label for="tags">Tags</label>
						<g:textField name="tags" type="text" size="30" />
							<jq:jquery>
								$('#tags').val('')
							</jq:jquery>
						<g:each var="t" in="${question.tags}">
							<jq:jquery>
								$('#tags').val($('#tags').val() + '${t.word} ');                                
							</jq:jquery>
						</g:each>
						
					</p>
					
					<p class='${hasErrors(bean:question,field:'body','errors')}'>
						<label for="body">Question</label>
						<g:textArea name="body" cols="50" rows="10" value="${question.body}" />
					</p>
					
					<p class="submit">
						<g:actionSubmit value="Submit" action="editQuestion"/>
					</p>
					
				</fieldset>								
			</g:form>
		</div>
	</body>