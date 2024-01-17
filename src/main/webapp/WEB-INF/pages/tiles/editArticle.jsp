<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.editting_article_header" var="editting_article_header" />
<fmt:message bundle="${loc}" key="local.save_changes" var="save_changes" />
<fmt:message bundle="${loc}" key="local.cancel" var="cancel" />

<br>
<br>


<form action="controller" method="post">
	<input type="hidden" name="command" value="edit_article" />
	<textarea name="title" rows="2" cols="80">${sessionScope.news.title}</textarea>
	<textarea name="brief" rows="4" cols="80">${sessionScope.news.brief}</textarea>
	<textarea name="content" rows="10" cols="80">${sessionScope.news.content}</textarea>
	<br><br> <input type="submit" style=" width: 160px" value="${save_changes}" />


</form>

<form action="controller" method="post">
	<input type="hidden" name="command" value="go_to_base_page" /> <br>
	<input type="submit" style=" width: 160px" value="${cancel}" />
</form>





