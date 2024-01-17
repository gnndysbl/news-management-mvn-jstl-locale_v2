<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.locbutton.name.ru" var="ru_button" />
<fmt:message bundle="${loc}" key="local.locbutton.name.en" var="en_button" />
<fmt:message bundle="${loc}" key="local.registration" var="registration" />
<fmt:message bundle="${loc}" key="local.back_to_the" var="back_to_the" />
<fmt:message bundle="${loc}" key="local.main_page" var="main_page" />
<fmt:message bundle="${loc}" key="local.enter_login_reg" var="enter_login_reg" />
<fmt:message bundle="${loc}" key="local.enter_password" var="enter_password" />
<fmt:message bundle="${loc}" key="local.enter_name" var="enter_name" />
<fmt:message bundle="${loc}" key="local.enter_lastname" var="enter_lastname" />
<fmt:message bundle="${loc}" key="local.enter_email" var="enter_email" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title></title>

<link rel="stylesheet" type="text/css" href="styles/newsStyle.css">
</head>
<body>
	<div class="page">
		
		<div class="registration">${registration}</div>

		<div class="registration-form">
		
		<div align="right">
			<a href="controller?command=change_locale_to_en">${en_button}</a> <br>
			<a href="controller?command=change_locale_to_ru"> ${ru_button}</a> <br>
		</div>
		
			${back_to_the} <a href="index.jsp">${main_page}</a> <br> <br> <br>

			<form action="controller" method="post">
				<input type="hidden" name="command" value="add_user" /> ${enter_login_reg}<br /> <input type="text" name="login"
					required="required" pattern="\w{3,16}" required /> <br /> ${enter_password}<br /> <input type="password" name="password"
					minlength="3" required /> <br /> ${enter_name}<br /> <input
					type="text" name="name" required="required" /> <br /> ${enter_lastname}<br /> <input type="text" name="lastName"
					required="required" /> <br /> ${enter_email}<br /> <input
					type="email" name="email" required="required" /> <br /> <input
					type="submit" value="${registration}" />
			</form>


			<c:if test="${not (param.registrationError eq null)}">
				<font color="red"> <c:out value="${param.registrationError}" />
				</font>
			</c:if>
		</div>
	</div>

</body>
</html>