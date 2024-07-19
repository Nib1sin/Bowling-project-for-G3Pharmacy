<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
    <title>View Bowling Games</title>
</head>
<body>
    <h1>All Bowling Games</h1>
    <ul>
        <c:forEach var="game" items="${games}">
            <li>
                ${game.name}:
                <c:forEach var="frame" items="${game.rolls}" varStatus="frameStatus">
                    [
                    <c:forEach var="roll" items="${frame}" varStatus="rollStatus">
                        ${roll}<c:if test="${!rollStatus.last}">, </c:if>
                    </c:forEach>
                    ]<c:if test="${!frameStatus.last}">, </c:if>
                </c:forEach>
                <br>
                -> Score: ${game.score}
            </li>
        </c:forEach>
    </ul>
</body>
</html>
