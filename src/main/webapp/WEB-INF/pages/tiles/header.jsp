<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.news_title1" var="news_title1" />
<fmt:message bundle="${loc}" key="local.locbutton.name.ru"
	var="ru_button" />
<fmt:message bundle="${loc}" key="local.locbutton.name.en"
	var="en_button" />
<fmt:message bundle="${loc}" key="local.enter_login" var="enter_login" />
<fmt:message bundle="${loc}" key="local.enter_password"
	var="enter_password" />
<fmt:message bundle="${loc}" key="local.registration" var="registration" />
<fmt:message bundle="${loc}" key="local.sign_in" var="sign_in" />
<fmt:message bundle="${loc}" key="local.sign_out" var="sign_out" />
<fmt:message bundle="${loc}" key="local.welcome" var="welcome" />
<fmt:message bundle="${loc}" key="local.signing_in_error"
	var="signing_in_error" />
<fmt:message bundle="${loc}" key="local.editing" var="editing" />
<fmt:message bundle="${loc}" key="local.placing" var="placing" />


<div class="wrapper">


	<div class="newstitle">
		<c:if test="${sessionScope.editArticle eq 'active'}">
${editing}
</c:if>

		<c:if test="${sessionScope.addArticle eq 'active'}">
${placing}
</c:if>

		<c:if test="${not (sessionScope.addArticle eq 'active')}">
			<c:if test="${not (sessionScope.editArticle eq 'active')}">
${news_title1}
		</c:if>
		</c:if>

	</div>

	<div class="local-link">

		<div align="right">

			<a href="controller?command=change_locale_to_en">${en_button}</a> <br>
			<a href="controller?command=change_locale_to_ru"> ${ru_button}</a> <br>
		</div>

		<c:if test="${not (sessionScope.user eq 'active')}">

			<div align="right">
				<form action="controller" method="post">
					<input type="hidden" name="command" value="sign_in" />
					${enter_login} <input type="text" name="login" value="" /><br />
					${enter_password} <input type="password" name="password" value="" /><br />

					<c:if test="${not (param.signingInError eq null)}">
						<font color="red"> <c:out value="${signing_in_error}" />
						</font>
					</c:if>


					<a href="controller?command=go_to_registration_page">

						${registration}</a> <input type="submit" value="${sign_in}" /><br />
				</form>
			</div>

		</c:if>

		<c:if test="${sessionScope.user eq 'active'}">
			<br>
			<div class="welcome-user">
				<font color="blue"> ${welcome} <c:out
						value="${sessionScope.userName}" />
				</font>

			</div>

			<div align="right">
				<form action="controller" method="post">
					<input type="hidden" name="command" value="sign_out" /> <input
						type="submit" value="${sign_out}" /><br />
				</form>
			</div>
		</c:if>
	</div>

</div>
