<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>

<div class="navbar navbar-default" role="navigation">
	<div class="container">
		<div class="navbar-header">
			<a class="navbar-brand" href="<c:url value="/" />">Furniture
				Store</a>
		</div>

		<sec:authorize access="isAuthenticated() and hasRole('ROLE_CLIENT')">
			<ul class="nav navbar-nav navbar-right">
				<li><a href='<c:url value="/client/profile/" />'>Hi,
						${client.profile.name}</a></li>
				<li><a href="<c:url value="/logout"/>">Logout</a></li>
			</ul>
		</sec:authorize>

		<sec:authorize access="isAuthenticated() and hasRole('ROLE_ADMIN')">

			<ul class="nav navbar-nav">
				<li><a href='<c:url value="/admin/panel" />'>Show list of users</a></li>
				<li><a href='<c:url value="/admin/register/provider"/>'>New Provider</a></li>
				<li><a href='<c:url value="/admin/register/company"/>'>New	Employer</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="<c:url value="/logout"/>">Logout</a></li>
			</ul>
		</sec:authorize>

	</div>

</div>