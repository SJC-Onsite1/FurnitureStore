<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<sec:authorize access="isAuthenticated() and hasRole('ROLE_CLIENT')">
	<div class="col-md-9">
		<c:set var="i" value="1"></c:set>
		<div class="text-right">
			<a href="?lang=en">en</a> | <a href="?lang=ru">ru</a>
		</div>
		<div class="panel panel-default">
			<div class="panel-heading">
				<h5 class="text-center">
					<strong><span class="glyphicon glyphicon-list-alt"></span>
						<spring:message code="payment.header" /></strong>
				</h5>
			</div>

			<div class="panel-body">
				<c:if test="${empty payments}">
					<h3>
						<spring:message code="payment.notfound" />
					</h3>
				</c:if>


				<c:if test="${not empty payments}">
					<table class="table table-hover table-bordered">
						<thead>
							<tr class="text-center">
								<td>#</td>
								<td><spring:message code="payment.date" /></td>
								<td><spring:message code="payment.sum" /></td>
								<td><spring:message code="payment.action" /></td>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="payment" items="${payments}">
								<c:if test="${payment.paymentStatus eq false}">
									<tr class="text-center">
										<td>${i}</td>
										<td><fmt:formatDate type="date" value="${payment.date}" /></td>
										<td>${payment.sum}</td>
										<td><c:url var="toOrder"
												value="/order/${payment.order.id}" /> <a
											class="btn btn-primary" href="${toOrder}"><spring:message
													code="payment.toOrder" /></a></td>
										<c:set var="i" value="${i + 1}"></c:set>
									</tr>
								</c:if>
							</c:forEach>
						</tbody>
					</table>
				</c:if>
			</div>
			<div class="panel-footer clerfix">
				<div class="text-left">
					<c:url var="toProfile" value="/profile" />
					<a href="${toProfile}">To profile</a>
				</div>
			</div>
		</div>
	</div>
</sec:authorize>