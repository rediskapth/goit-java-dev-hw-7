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
            <form action="/developer-created" method="post">
                <div class="form-group">
                    <label for="developerName">Developer name:</label><br>
                    <input type="text" class="form-control" id="developerName" placeholder="Enter developer name" name="developerName"><br>
                    <label for="developerAge">Developer age:</label><br>
                    <input type="number" class="form-control" id="developerAge" placeholder="Enter developer age" name="developerAge"><br>
                    <label for="developerSalary">Developer salary:</label><br>
                    <input type="number" class="form-control" id="developerSalary" placeholder="Enter developer salary" name="developerSalary"><br>

                    <label for="skillId">Select skill:</label><br>
                    <c:forEach items="${skills}" var="skill">
                        <input type="checkbox" name="skillId" value="${skill.id}">
                        <c:out value="${skill.language}"/>
                        <c:out value="${skill.skill}"/><br>
                    </c:forEach>

                    <label for="projectId">Select project:</label><br>
                    <c:forEach items="${projects}" var="project">
                        <input type="checkbox" name="projectId" value="${project.id}">
                        <c:out value="${project.name}"/>
                        <c:out value="${project.description}"/>
                    <c:out value="${project.creationDate}"/><br>
                    </c:forEach>
                </div>
                    <input type="submit" value="Submit">
            </form>
        </div>
    </body>
</html>