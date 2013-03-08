<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Welcome to Pile Qui Deborde</title>
		<style type="text/css" media="screen">
			#status {
				background-color: #eee;
				border: .2em solid #fff;
				margin: 2em 2em 1em;
				padding: 1em;
				width: 12em;
				float: left;
				-moz-box-shadow: 0px 0px 1.25em #ccc;
				-webkit-box-shadow: 0px 0px 1.25em #ccc;
				box-shadow: 0px 0px 1.25em #ccc;
				-moz-border-radius: 0.6em;
				-webkit-border-radius: 0.6em;
				border-radius: 0.6em;
			}

			.ie6 #status {
				display: inline; /* float double margin fix http://www.positioniseverything.net/explorer/doubled-margin.html */
			}

			#status ul {
				font-size: 0.9em;
				list-style-type: none;
				margin-bottom: 0.6em;
				padding: 0;
			}
            
			#status li {
				line-height: 1.3;
			}

			#status h1 {
				text-transform: uppercase;
				font-size: 1.1em;
				margin: 0 0 0.3em;
			}

			#page-body {
				margin: 2em 1em 1.25em 18em;
			}

			h2 {
				margin-top: 1em;
				margin-bottom: 0.3em;
				font-size: 1em;
			}

			p {
				line-height: 1.5;
				margin: 0.25em 0;
			}

			#controller-list ul {
				list-style-position: inside;
			}

			#controller-list li {
				line-height: 1.3;
				list-style-position: inside;
				margin: 0.25em 0;
			}
			

			@media screen and (max-width: 480px) {
				#status {
					display: none;
				}

				#page-body {
					margin: 0 1em 1em;
				}

				#page-body h1 {
					margin-top: 0;
				}
				
				
			}
		</style>
		 
		
	</head>
	<body>
		<!--<a href="#page-body" class="skip"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a> -->
		
			<!-- div for the possible actions -->
			<div style="padding: 0.6em;">
				<span>
					<g:link controller="member" action="register">Register</g:link>
					<g:link controller="member" action="myAccount">My account</g:link>
					<g:link controller="member" action="list">List all the members</g:link>
				</span>
				<span style="margin-left: 4em;">
					<g:link controller="question">Post a new question</g:link>
					<g:link controller="question" action="list">List all the questions</g:link>
				</span>
				<span style="margin-left: 4em;">
					<g:link controller="tag">Add a new tag</g:link>
					<g:link controller="tag" action="list">List all the tags</g:link>
				</span>
				<br/>
			
				<h1>Recently asked questions</h1><br/>
	
				<!-- display the questions -->
				<g:each var="q" in="${questions}">
					<div class="questionList">
						Title  : <g:link controller="question" action="detail" id="${q.id}">${q.title}</g:link><br/>
						<div class="questionAuthor"> by : ${q.author.pseudo} </div>	
						<div class="questionTag"> Tags   :  <g:each var="t" in="${q.tags}"> <span class="tag"> ${t.word} </span></g:each></div>
						<div style="font-size: 0.7em;">posted the ${q.date}</div><br/>
					</div> 
				</g:each>
			
			</div>
		
	</body>
</html>
