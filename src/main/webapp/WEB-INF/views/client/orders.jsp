<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>

<sec:authorize access="isAuthenticated() and hasRole('ROLE_CLIENT')">
	<div class="col-md-9">
		<div class="panel panel-default">
			<div class="panel-heading">
				<h5 class="text-center">
					<strong><span class="glyphicon glyphicon-list-alt"><strong>Orders</strong>
				</h5>
			</div>
			<div class="panel-body">
				<div class="col-md-10 col-md-offset-1">
					<c:if test="${empty orderList}">
						<h2>No orders!</h2>
					</c:if>
					<c:if test="${not empty orderList}">
						<c:set var="timeZone" value="GMT+3" />
						<table class="table table-bordered">
							<thead>
								<tr class="text-center">
									<td><strong>ID</strong></td>
									<td><strong>Order date</strong></td>
									<td><strong>Status</strong></td>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${orderList}" var="order">
									<c:if test="${empty order.assemblyDate}">
										<tr class="active text-center">
											<td>#${order.id}</td>
											<td><fmt:formatDate type="both" timeZone="${timeZone}"
													dateStyle="medium" timeStyle="long"
													value="${order.orderDate}" /></td>
											<td>Not confirmed</td>
										</tr>
									</c:if>
									<c:if test="${not empty order.assemblyDate}">
										<tr class="success text-center">
											<c:url var="showOrder" value="/order/${order.id}"></c:url>
											<td><a class="brn brn-link" href="${showOrder}">#${order.id}</a></td>
											<td><fmt:formatDate type="both" timeZone="${timeZone}"
													dateStyle="medium" timeStyle="long"
													value="${order.orderDate}" /></td>
											<td>Confirmed</td>
										</tr>
									</c:if>
								</c:forEach>
							</tbody>
						</table>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</sec:authorize>