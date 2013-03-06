<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="main" />
	<g:javascript library="jquery" plugin="jquery"/>
	<!--<g:setProvider library="jquery"/>-->
	<title>List of all the questions</title>
</head>

<body>

	<a href="${createLink(uri: '/', absolute: true)}"><- Return to main
		page</a>
	<br />
	<br />

	<div class="content">

		<!-- Question -->
		<div class="question">
			<h2>Question</h2><br/>
			
			<div style="float: left; width: 10%; margin-top:5%">
				<g:remoteLink controller="question" action="voteUp" update="totalVote" params="${[idquestion: q.id]}">
					<img src="${resource(dir: 'images', file: 'arrow_up.png')}"/><br/>
				</g:remoteLink>
				
				<!--<g:textField name="totalVote" type="text" size="1" value="0" readonly="readonly"/><br/>-->
				<div id="totalVote" style="margin-left: 10%">${q.totalVote}</div>
				
				<g:remoteLink controller="question" action="voteDown" update="totalVote" params="${[idquestion: q.id]}">
					<img src="${resource(dir: 'images', file: 'arrow_down.png')}"/><br/>
				</g:remoteLink>
			</div>
			
			<div style="float: right; width: 90%">
				Title :
				<g:link action="detail" id="${q.id}">
					${q.title}
				</g:link>
				<br /> <br /> Tags :
				<g:each var="t" in="${q.tags}">
					<span class="tag">
						${t.word}
					</span>
				</g:each>
				<br />
		
				<div class="questionBody">
					${q.body}
				</div>
				by :
				${q.author.pseudo}
				<br/><br/>
			</div>
			
			<g:if
				test="${session.user.id == q.author.id  || session.user.role == "admin"}">
				<g:link controller="question" action="edit"
					params="${[idquestion: q.id]}">
					<input value="Edit" />
				</g:link>
			</g:if>
			<g:link controller="answer" action="answer"
				params="${[idquestion: q.id]}">
				<input value="Answer" />
			</g:link>
			<g:link controller="comment" action="comment"
				params="${[idpost: q.id]}">
				<input value="Comment" />
			</g:link>
		</div>
		
		<!-- Comments in relation to the previous question -->
		<div class="questionComments">
			<g:if test="${q.comments}">
				<h2>Comments</h2>
				<br />
				<g:each var="c" in="${q.comments}">
					<div class="commentDetail">
						${c.body}
						<br />
								by : ${c.author.pseudo}
						<br />
						<!-- Edit button for the author of the comment, or for the admin -->
						<g:if
							test="${session.user.id == c.author.id  || session.user.role == "admin"}">
							<g:link controller="comment" action="edit"
								params="${[idcomment: c.id]}">
								<input value="Edit" />
							</g:link>
							<br />
						</g:if>
					</div>
				</g:each>
			</g:if>
		</div>
		<br />

		
		<!-- Answers in relation with the previous question -->
		<div class="questionAnswers">
			<h2>Answers</h2>
			<br />
	
			<g:each var="a" in="${q.answers}">
				${a.body}
				<br />
							by : ${a.author.pseudo}
				<br />
	
				<!-- Edit button for the author or the admin -->
				<g:if
					test="${session.user.id == a.author.id  || session.user.role == "admin"}">
					<g:link controller="answer" action="edit"
						params="${[idanswer: a.id]}">
						<input value="Edit" />
					</g:link>
				</g:if>
	
				<!-- Comment button for everyone -->
				<g:link controller="comment" action="comment"
					params="${[idpost: a.id]}">
					<input value="Comment" />
				</g:link>
				<br />
				<br />
			

				<!-- Comments in relation with the previous answer -->
				<g:if test="${a.comments }">
					<div class="questionComments">
						<h4>Comments :</h4>
						<g:each var="com" in="${a.comments}">
							<div class="commentDetail">
								${com.body}
								<br />
											by : ${com.author.pseudo}
								<br />
								<!-- Edit button for the author of the comment, or for the admin -->
								<g:if
									test="${session.user.id == com.author.id  || session.user.role == "admin"}">
									<g:link controller="comment" action="edit"
										params="${[idcomment: com.id]}">
										<input value="Edit" />
									</g:link>
									<br />
								</g:if>
							</div>
						</g:each>
					</div>
				</g:if>
				<br />
			</g:each>
		</div>
	</div>
</body>
</html>