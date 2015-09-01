<%@ page import="com.shashi.db.services.CustomerService" %>
<%@ page import="com.shashi.db.services.impl.CustomerServiceImpl" %>
<%@include file="/apps/dbaccess/global.jsp" %>
Form Component

<c:set var="customerService" value="<%=sling.getService(com.shashi.db.services.CustomerService.class)%>"/>

<%
    CustomerService customerService = new CustomerServiceImpl();
    customerService.getData(CustomerService.CustomerType.ACTIVE_CUSTOMER);
%>