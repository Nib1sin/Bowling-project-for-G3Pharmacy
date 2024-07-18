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
            <li>${game.name}: ${game.rolls} - Score: ${game.score}</li>
        </c:forEach>
    </ul>
    <br>
    <a href="/home">Back to Home</a>

</body>
</html>
