<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.back_to_the" var="back_to_the" />
<fmt:message bundle="${loc}" key="local.main_page" var="main_page" />
<fmt:message bundle="${loc}" key="local.add_news_button" var="add_news" />

<br>
<a href="index.jsp">${back_to_the}<br>${main_page}</a>
<br>
<br>


<c:if test="${sessionScope.userRole eq 'ADMIN'}">
	<a href="controller?command=go_to_add_article">${add_news} </a>
	<br>
</c:if>

<br><br><br><br><br>


<c:if test="${not (sessionScope.addArticle eq 'active' or sessionScope.viewArticle eq 'active' or sessionScope.editArticle eq 'active')}">

<c:if test="${not (requestScope.news eq null)}">
	<c:if
		test="${sessionScope.userRole eq 'ADMIN' or sessionScope.userRole eq 'USER'}">
		<a href="controller?command=next_page">Next page</a>
	</c:if>
</c:if>

<br><br>

<c:if test="${not (sessionScope.page eq '1')}">
	<c:if
		test="${sessionScope.userRole eq 'ADMIN' or sessionScope.userRole eq 'USER'}">
		<a href="controller?command=previous_page">Previous page</a>
	</c:if>
</c:if>

</c:if>






