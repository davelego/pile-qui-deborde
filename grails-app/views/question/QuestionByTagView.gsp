<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title><g:message code="default.label.listquestions"/></title>
	</head>
	
	<body>
	
		<a href="${createLink(uri: '/', absolute: true)}"><- <g:message code="default.label.mainpage"/></a><br/><br/>

		<g:each var="q" in="${questions}">
			<div class="questionList">
				<g:message code="default.label.title"/> : <g:link action="detail" id="${q.id}">${q.title}</g:link>         <br/>
				<div class="questionAuthor"> <g:message code="default.label.by"/> : ${q.author.pseudo} </div>	 <br/>			
				<div class="questionTag"> <g:message code="default.label.tags"/>   :  <g:each var="t" in="${q.tags}"> <span class="tag"> <g:link controller="question" action="questionByTags" id="${t.id}">${t.word}</g:link> </span></g:each></div>
				<div style="font-size: 0.7em;"><g:message code="default.label.date"/> ${q.date}</div><br/>
			</div> 
		</g:each>
	
	</body>
	
</html>