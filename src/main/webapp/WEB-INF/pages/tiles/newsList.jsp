<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.latest_news" var="latest_news" />
<fmt:message bundle="${loc}" key="local.view_button" var="view_button" />
<fmt:message bundle="${loc}" key="local.edit_button" var="edit_button" />
<fmt:message bundle="${loc}" key="local.no_news" var="no_news" />
<fmt:message bundle="${loc}" key="local.delete_marked_button"
	var="delete_marked_button" />



<link rel="stylesheet" type="text/css" href="styles/newsStyle.css">

<div class="body-title">
	<a href="controller?command=go_to_base_page">${latest_news}</a>
</div>
<br>

<form action="controller" method="post">
	<c:forEach var="news" items="${requestScope.news}">

		<div class="single-news-wrapper">
			<div class="single-news-header-wrapper">


				<div class="news-title">

					<c:if test="${sessionScope.userRole eq 'ADMIN'}">

						<input type="checkbox" name="id" value="${news.id}" />
					</c:if>
					<c:out value="${news.title}" />
				</div>
				<div class="news-date">
					<c:out value="${news.date}" />
				</div>

				<div class="news-content">
					<c:out value="${news.brief}" />
				</div>
				<div class="news-link-to-wrapper">
					<div class="link-position">

						<a href="controller?command=go_to_view_article&id=${news.id}">${view_button}</a>

						&nbsp

						<c:if test="${sessionScope.userRole eq 'ADMIN'}">
							<a href="controller?command=go_to_edit_article&id=${news.id}">${edit_button}</a>
						</c:if>

					</div>
				</div>

			</div>
		</div>
		<br>

	</c:forEach>
	<c:if test="${sessionScope.userRole eq 'ADMIN'}">
		<c:if test="${not (requestScope.news eq null)}">
			<input type="hidden" name="command" value="delete_marked" />
			<input type="submit" value="${delete_marked_button}" />
		</c:if>
	</c:if>

	<div class="no-news">
		<c:if test="${requestScope.news eq null}">
${no_news}
	</c:if>
	</div>
	
	<div class="page-number" >
	<c:if test="${not (requestScope.news eq null)}">
		<br><br>Page ${sessionScope.page}
	</c:if>
	</div>
	
	
</form>
