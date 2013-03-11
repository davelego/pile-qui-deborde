<!DOCTYPE html>
<html>
<head>
	<meta name="layout" content="main" />
	<g:javascript library="jquery" plugin="jquery"/>
	<!--<g:setProvider library="jquery"/>-->
	<title>${q.title}</title>
</head>

<body>

	<a href="${createLink(uri: '/', absolute: true)}"><- <g:message code="default.label.mainpage"/></a><br/><br/>

	<div class="content">

		<!-- Question -->
		<div class="question">
			<h2><g:message code="default.label.question"/> </h2><br/>
			
			<div style="float: left; width: 10%; margin-top:5%">
				<g:remoteLink controller="post" action="voteUp" update="totalVote" params="${[idpost: q.id]}">
					<img src="${resource(dir: 'images', file: 'arrow_up.png')}"/><br/>
				</g:remoteLink>
				
				<div id="totalVote" style="margin-left: 10%">${q.totalVote}</div>
				
				<g:remoteLink controller="post" action="voteDown" update="totalVote" params="${[idpost: q.id]}">
					<img src="${resource(dir: 'images', file: 'arrow_down.png')}"/><br/>
				</g:remoteLink>
			</div>
			
			<div style="float: right; width: 90%">
				<g:message code="default.label.title"/> :
				<g:link action="detail" id="${q.id}">
					${q.title}
				</g:link>
				<br /> <br /> Tags :
				<g:each var="t" in="${q.tags}">
					<span class="tag">
						<g:link controller="question" action="questionByTags" id="${t.id}">${t.word}</g:link>
					</span>
				</g:each>
				<br />
		
				<div class="questionBody">
					${q.body}
				</div>
				<g:message code="default.label.by"/> :
				<g:link controller="member" action="myAccount" id="${q.author.id}">${q.author.pseudo}</g:link>
				<br/>
				
				<div style="font-size: 0.7em;"><g:message code="default.label.date"/> ${q.date}</div>
				<br/>
			</div>
		
			<div style="margin-left: 10%">
				<g:if
					test="${session.user.id == q.author.id  || session.user.role == "admin"}">
					<g:link controller="question" action="edit"
						params="${[idquestion: q.id]}">
						<g:textField value="Edit" type="text" name="editBtnQuestion" readonly="readonly"/>
					</g:link>
					<g:link controller="question" action="delete"
						params="${[idquestion: q.id]}">
						<g:textField value="Delete" type="text" name="deleteBtnQuestion" readonly="readonly"/>
					</g:link>
				</g:if>
				
				<g:link controller="answer" action="answer"
					params="${[idquestion: q.id]}">
					<g:textField value="Answer" type="text" name="answerBtnQuestion" readonly="readonly"/>
				</g:link>
				<g:link controller="comment" action="comment"
					params="${[idpost: q.id]}">
					<g:textField value="Comment" type="text" name="commentBtnQuestion" readonly="readonly"/>
				</g:link>
			</div>
		</div>
		
		<!-- Comments in relation to the previous question -->
		<g:if test="${q.comments }">
			<div class="questionComments">
				<g:if test="${q.comments}">
					<h2><g:message code="default.label.comment"/> </h2>
					<br />
					<g:each var="c" in="${q.comments}">
						<div class="commentDetail">
							${c.body}
							<br />
									<g:message code="default.label.by"/> : 
									<g:link controller="member" action="myAccount" id="${c.author.id}">${c.author.pseudo}</g:link>
							<br />
							<!-- Edit button for the author of the comment, or for the admin -->
							<g:if
								test="${session.user.id == c.author.id  || session.user.role == "admin"}">
								<g:link controller="comment" action="edit"
									params="${[idcomment: c.id]}">
									<g:textField value="Edit" type="text" name="editBtnComment" readonly="readonly"/>
								</g:link>
								<g:link controller="comment" action="delete"
									params="${[idcomment: c.id,idquestion:q.id]}">
									<g:textField value="Delete" type="text" name="deleteBtnQuestion" readonly="readonly"/>
								</g:link>
								<br />
							</g:if>
						</div>
					</g:each>
				</g:if>
			</div>
		</g:if>
		<br />

		
		<!-- Answers in relation with the previous question -->
		<div class="questionAnswers">
			<h2><g:message code="default.label.answer"/> </h2><br/>
				
			<g:each var="a" in="${answers}">
			
				<div style="float: left; width: 10%;">
					<g:remoteLink controller="post" action="voteUp" update="totalVoteAnswer${a.id}" params="${[idpost: a.id]}">
						<img src="${resource(dir: 'images', file: 'arrow_up.png')}"/><br/>
					</g:remoteLink>
					
					<div id="totalVoteAnswer${a.id}" style="margin-left: 10%">${a.totalVote}</div>
					
					<g:remoteLink controller="post" action="voteDown" update="totalVoteAnswer${a.id}" params="${[idpost: a.id]}">
						<img src="${resource(dir: 'images', file: 'arrow_down.png')}"/><br/>
					</g:remoteLink>
					
					<g:if test="${a.haveHelped}">
						<g:link controller="answer" action="uncheck" update="checkdiv${a.id}" params="${[idanswer: a.id]}">
							<img src="${resource(dir: 'images', file: 'check.png')}"/><br/>
						</g:link>
					</g:if>
					<g:else>
						<g:link controller="answer" action="check" update="checkdiv${a.id}" params="${[idanswer: a.id]}">
							<img src="${resource(dir: 'images', file: 'uncheck.png')}"/><br/>
						</g:link>
					</g:else>
					
				</div>
			
				<div style="float: right; width: 90%;">
					${a.body} <br/>
					<g:message code="default.label.by"/> : <g:link controller="member" action="myAccount" id="${a.author.id}">${a.author.pseudo}</g:link> <br/>
					<span style="font-size: 0.7em;">
						<g:message code="default.label.date"/> ${a.date}
					</span>
				</div>
				
				<!-- Edit button for the author or the admin -->
				<div style="margin-left: 10%">
					<g:if
						test="${session.user.id == a.author.id  || session.user.role == "admin"}">
						<g:link controller="answer" action="edit"
							params="${[idanswer: a.id]}">
							<g:textField value="Edit" type="text" name="editBtnAnswer" readonly="readonly"/>
						</g:link>
						<g:link controller="answer" action="delete"
							params="${[idanswer: a.id]}">
							<g:textField value="Delete" type="text" name="deleteBtnQuestion" readonly="readonly"/>
						</g:link>
					</g:if>
		
					<!-- Comment button for everyone -->
					<g:link controller="comment" action="comment"
						params="${[idpost: a.id]}">
						<g:textField value="Comment" type="text" name="commentBtnAnswer" readonly="readonly"/>
					</g:link><br/><br/>
				</div>

				<!-- Comments in relation with the previous answer -->
				<g:if test="${a.comments }">
					<div class="questionComments">
						<h4><g:message code="default.label.comment"/>  :</h4>
						<g:each var="com" in="${a.comments}">
							<div class="commentDetail">
								${com.body}
								<br />
											<g:message code="default.label.by"/> : <g:link controller="member" action="myAccount" id="${com.author.id}">${com.author.pseudo}</g:link>
								<br />
								<!-- Edit button for the author of the comment, or for the admin -->
								<g:if
									test="${session.user.id == com.author.id  || session.user.role == "admin"}">
									<g:link controller="comment" action="edit"
										params="${[idcomment: com.id, idquestion:a.question.id]}">
										<g:textField value="Edit" type="text" name="editBtnComment" readonly="readonly"/>
									</g:link>
									<g:link controller="comment" action="delete"
										params="${[idcomment: com.id	, idquestion:a.question.id]}">
										<g:textField value="Delete"  type="text" name="deleteBtnQuestion" readonly="readonly"/>
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