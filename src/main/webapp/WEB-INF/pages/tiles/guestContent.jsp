<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.latest_news" var="latest_news" />
<fmt:message bundle="${loc}" key="local.no_news" var="no_news" />


<a href="index.jsp">${latest_news}</a>

<br>
<br>

<c:forEach var="news" items="${requestScope.news}">
	<div class="single-news-wrapper">
		<div class="single-news-header-wrapper">
			<div class="news-title">
				<c:out value="${news.title}" />
			</div>
			<div class="news-date">
				<c:out value="${news.date}" />
			</div>

			<div class="news-content">
				<c:out value="${news.brief}" />
			</div>
			<br>
		</div>
	</div>

</c:forEach>



<div class="no-news">
	<c:if test="${requestScope.news eq null}">
        ${no_news}
	</c:if>
</div>


