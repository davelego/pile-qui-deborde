<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'questionstyle.css')}" type="text/css">
		<title>Post a new question</title>
	</head>
	
	<body>
		<div id="container">
		
			<a href="/pile-qui-deborde/"><- Return to main page</a>
		
			<g:renderErrors bean="${question}" />
			<g:form name="formnewquestion">	
				<fieldset>
					<legend>Please fill the following fields :</legend>
					
					<p  class='${hasErrors(bean:question,field:'title','errors')}'>					
						<label for="title">Title</label>
						<g:textField name="title" type="text" size="30" value="${question?.title}" />					
					</p>
					
					<p class='${hasErrors(bean:question,field:'tags','errors')}'>					
						<label for="tags">Tags</label>
						<g:textField name="tags" type="text" size="30" value="${question?.tags}" />					
					</p>
					
					<p class='${hasErrors(bean:question,field:'body','errors')}'>
						<label for="body">Question</label>
						<g:textArea name="body" cols="50" rows="10" value="${question?.body}" />
					</p>
					
					<p class="submit">
						<g:actionSubmit value="Submit" action="saveNewQuestion"/>
					</p>
					
				</fieldset>								
			</g:form>
		</div>
	</body>