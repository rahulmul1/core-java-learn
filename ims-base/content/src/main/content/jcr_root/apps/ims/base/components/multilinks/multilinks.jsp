<%--

  links component.

  IMS LINKS COMPONENT

--%>
<%@include file="/apps/ims/base/global.jsp"%>
<%@page session="false" %>
<ims:controller var= "multiLinksController" cls= "com.ims.base.components.MultiLinksController" />
<ul>
    <c:if test="${not empty multiLinksController.titleLinkList}">
        <c:forEach var="titleLinkMap" items="${multiLinksController.titleLinkList}">
            <li>&nbsp;&nbsp;| &nbsp;&nbsp;</li>
            <li><a href="${titleLinkMap.url}.html">${titleLinkMap.title}</a></li>
        </c:forEach>
    </c:if>
</ul>