<%--
    
    Login Component component.
    
    IMS Login Component
    
    --%><%
    %><%@include file="/apps/ims/base/global.jsp"%><%
    %><%@page session="false" %><%
    %><%
    // TODO add you code here
    %><div id="login_thumbnail"><label>Agent Login Panel</label></div>
<div id="login_body">
    <div id="login_body_top">
    </div>
    <div id="login_body_center">
        <cq:include path="loginformstart" resourceType="/apps/ims/base/components/forms/formstart"/>
        
        <table>
            <tr>
                <td>Agent Id</td>
            </tr>
            <tr>
                <td><input type="text" name="userid" value="" id="userid" class="text_box"/></td>
            </tr>
            <tr>
                <td>Password</td>
            </tr>
            <tr>
                <td><input type="password" name="password" value="" class="text_box"/></td>
            </tr>
            <tr>
                
                <td><input type="submit" value="Login" class="login_btn"/></td>
            </tr>
            <tr>
                <td><a href="#">Forgot Password?</a></td>
            </tr>
        </table>
        <cq:include path="loginformend" resourceType="/apps/ims/base/components/forms/end"/>
        
    </div>
    <div id="login_body_bottom">
    </div>
</div>