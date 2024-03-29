<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
    <head>
        <c:import url="${contextPath}/WEB-INF/html/header.jsp"/>
    </head>

    <body>
        <c:import url="${contextPath}/WEB-INF/html/navibar.jsp"/>

        <div class="container">
            <form action="/skill-created" method="post">
                <div class="form-group">
                    <label for="skillLanguage">Skill language:</label><br>
                    <input type="text" class="form-control" id="skillLanguage" placeholder="Enter skill language" name="skillLanguage"><br>
                    <label for="skillSkill">Skill skill:</label><br>
                    <input type="text" class="form-control" id="skillSkill" placeholder="Enter skill skill (junior, middle, etc.)" name="skillSkill"><br>

                    <label for="developerId">Select developer:</label><br>
                    <c:forEach items="${developers}" var="developer">
                        <input type="checkbox" name="developerId" value="${developer.id}">
                        <c:out value="${developer.name}"/>
                        <c:out value="${developer.age}"/><br>
                    </c:forEach>
                </div>
                    <input type="submit" value="Submit">
            </form>
        </div>
    </body>
</html>