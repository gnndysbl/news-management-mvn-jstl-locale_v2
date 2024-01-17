<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title></title>

<link rel="stylesheet" type="text/css" href="styles/newsStyle.css">

</head>
<body>

	<div class="page">

		<div class="header">
			<c:import url="/WEB-INF/pages/tiles/header.jsp" />
		</div>

		<div class="base-layout-wrapper">


			<div class="menu">
				<c:import url="/WEB-INF/pages/tiles/menu.jsp" />
			</div>

			<div class="content">

				<c:if test="${not (sessionScope.editArticle eq 'active') }">
					<c:if test="${not (sessionScope.addArticle eq 'active') }">
						<c:if test="${not (sessionScope.viewArticle eq 'active') }">

							<c:if test="${not (sessionScope.user eq 'active')}">
								<c:import url="/WEB-INF/pages/tiles/guestContent.jsp" />
							</c:if>

							<c:if test="${sessionScope.user eq 'active'}">
								<c:import url="/WEB-INF/pages/tiles/newsList.jsp" />
							</c:if>
						</c:if>
					</c:if>
				</c:if>

				<c:if test="${sessionScope.viewArticle eq 'active' }">
					<c:import url="/WEB-INF/pages/tiles/viewArticle.jsp" />
				</c:if>


				<c:if test="${sessionScope.userRole eq 'ADMIN' }">
					<c:if test="${sessionScope.addArticle eq 'active' }">
						<c:import url="/WEB-INF/pages/tiles/addArticle.jsp" />
					</c:if>
				</c:if>

				<c:if test="${sessionScope.userRole eq 'ADMIN' }">
					<c:if test="${sessionScope.editArticle eq 'active' }">
						<c:import url="/WEB-INF/pages/tiles/editArticle.jsp" />
					</c:if>
				</c:if>




			</div>
		</div>

		<div class="footer">footer</div>
	</div>
</body>
</html>