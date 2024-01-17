<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.back_to_the" var="back_to_the" />
<fmt:message bundle="${loc}" key="local.latest_news" var="latest_news" />
<fmt:message bundle="${loc}" key="local.edit_button1" var="edit_button1" />
<fmt:message bundle="${loc}" key="local.delete_button" var="delete_button" />
<fmt:message bundle="${loc}" key="local.news_title" var="news_title" />
<fmt:message bundle="${loc}" key="local.news_date" var="news_date" />
<fmt:message bundle="${loc}" key="local.news_brief" var="news_brief" />
<fmt:message bundle="${loc}" key="local.news_content" var="news_content" />


${back_to_the}
<a href="controller?command=go_to_base_page">${latest_news} </a>
<br>
<br>


<div class="add-table-margin">
	<table class="news_text_format">
		<tr>
			<td class="space_around_title_text">${news_title}</td>

			<td class="space_around_view_text"><div class="word-breaker">
					<c:out value="${requestScope.news.title }" />
				</div></td>
		</tr>
		<tr>
			<td class="space_around_title_text">${news_date}</td>

			<td class="space_around_view_text"><div class="word-breaker">
					<c:out value="${requestScope.news.date }" />
				</div></td>
		</tr>
		<tr>
			<td class="space_around_title_text">${news_brief}</td>
			<td class="space_around_view_text"><div class="word-breaker">
					<c:out value="${requestScope.news.brief}" />
				</div></td>
		</tr>
		<tr>
			<td class="space_around_title_text">${news_content}</td>
			<td class="space_around_view_text"><div class="word-breaker">
					<c:out value="${requestScope.news.content }" />
				</div></td>
		</tr>
	</table>
</div>


<c:if test="${sessionScope.userRole eq 'ADMIN'}">

	<div style="display: flex;">

		<form action="controller" method="post">
			<input type="hidden" name="command" value="go_to_edit_article" /> <input
				type="hidden" name="id" value="${news.id}" /> <input type="submit"
				value="${edit_button1}" />
		</form>
		
		&emsp;
		
		<form action="controller" method="post">
			<input type="hidden" name="command" value="delete_article" /> <input
				type="hidden" name="id" value="${news.id}" /> <input type="submit"
				value="${delete_button}" />
		</form>
	</div>
</c:if>