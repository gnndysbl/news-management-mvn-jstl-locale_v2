<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<fmt:setLocale value="${sessionScope.local}" />
<fmt:setBundle basename="localization.local" var="loc" />

<fmt:message bundle="${loc}" key="local.add_article_header" var="add_article_header" />
<fmt:message bundle="${loc}" key="local.add_button" var="add_button" />
<fmt:message bundle="${loc}" key="local.write_title_here" var="write_title_here" />
<fmt:message bundle="${loc}" key="local.write_brief_here" var="write_brief_here" />
<fmt:message bundle="${loc}" key="local.write_content_here" var="write_content_here" />

<br>
<br>


<form action="controller" method="post">
	<input type="hidden" name="command" value="add_article" />
	<textarea name="title" rows="2" cols="80"
		placeholder="${write_title_here}"></textarea>
	<textarea name="brief" rows="4" cols="80"
		placeholder="${write_brief_here}"></textarea>
	<textarea name="content" rows="10" cols="80"
		placeholder="${write_content_here}"></textarea>
	<br><br> <input type="submit" value="${add_button }" />


</form>

