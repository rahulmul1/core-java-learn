<%--

  IMS Base Page component.

  IMS Base Page is the master template page

--%><%
%><%@include file="/apps/ims/base/global.jsp"%><%
%><%@page session="false" %><%
%><%
    // TODO add you code here
%>

<html>
    <head>
        <title>IMS</title>
		<cq:include script="/libs/wcm/core/components/init/init.jsp"/> 
        <cq:includeClientLib categories="ims.style"/>
    </head>
    <body>
        <cq:include script="body.jsp"/>
    </body>
    
</html>