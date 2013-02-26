<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>List of all the questions</title>
	</head>
	
	<body>
	
		<a href="${createLink(uri: '/', absolute: true)}"><- Return to main page</a><br/><br/>
	
		<g:each var="q" in="${questions}">
			<div class="questionList">
				Title  : <g:link action="detail" id="${q.id}">${q.title}</g:link>         <br/>
				<div class="questionAuthor"> by : ${q.author.pseudo} </div>	 <br/>			
				<div class="questionTag"> Tags   :  <g:each var="t" in="${q.tags}"> <span class="tag"> ${t.word} </span></g:each>   </div>        <br/>
			</div> 
		</g:each>
	
	</body>
	
</html>