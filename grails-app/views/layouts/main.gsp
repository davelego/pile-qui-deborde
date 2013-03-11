<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<title><g:layoutTitle default="Grails"/></title>
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">
		<link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">
		<link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">
		<div id="loginHeader">
		    <g:loginControl />
		  </div>
		<g:layoutHead/>
		<r:layoutResources />
	</head>
	<body>
		<div id="grailsLogo" role="banner">
			<a href="${createLink(uri: '/', absolute: true)}">
				<img src="${resource(dir: 'images', file: 'pile_logo.png')}" alt="Grails"/>
			</a>
			<span style="font-size: 35px;"><span style="font-weight: normal;">Pile</span><span style="font-weight: bold;">quidéborde</span></span> 
			
			<div class="menu" >
				<div class="menu_item"> <g:link controller="question">Ask</g:link> </div>
				<div class="menu_item"> <g:link controller="badge" action="list">Badges</g:link> </div>
				<div class="menu_item"> <g:link controller="member" action="list">Members</g:link> </div>
				<div class="menu_item"> <g:link controller="tag" action="list">Tags</g:link> </div>
				<div class="menu_item"> <g:link controller="question" action="list">Questions</g:link> </div>
				
					<g:if test="${!session.user}">
						<div class="menu_item"> <g:link controller="member" action="register">Register</g:link> </div>
					</g:if>
					<g:else>
						<div class="menu_item"> <g:link controller="member" action="myAccount">My Account</g:link></div>
					</g:else>
				</div>
				
				
				
			<hr/>
		</div>
		<g:layoutBody/>
		<div class="footer" role="contentinfo"></div>
		<div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
		<g:javascript library="application"/>
		<r:layoutResources />
	</body>
</html>
