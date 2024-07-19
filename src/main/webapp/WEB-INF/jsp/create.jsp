<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create Bowling Game</title>
    <style>
        .frame-row {
            display: flex;
            align-items: center;
            margin-bottom: 10px;
        }
        .frame-row label, .frame-row input {
            margin-right: 10px;
        }
    </style>
    <script>
        function handleRollInput(frame, roll, value) {
            //console.log(`Frame: ${frame}, Roll: ${roll}, Value: ${value}`);
            const roll2Id = 'frame' + frame + 'roll2';
            const roll3Id = 'frame10roll3';

            if (frame <= 9) {
                const roll2Elem = document.getElementById(roll2Id);
                console.log(`Roll 2 ID: ${roll2Id}, Element: ${roll2Elem}`);
                if (roll === 1 && value == 10) {
                    if (roll2Elem) {
                        roll2Elem.value = null;
                        roll2Elem.disabled = true;
                    }
                } else {
                    if (roll2Elem) {
                        roll2Elem.disabled = false;
                    }
                }
            } else if (frame === 10) {
                const roll3Elem = document.getElementById(roll3Id);
                //console.log(`Roll 3 ID: ${roll3Id}, Element: ${roll3Elem}`);
                if (roll === 1 && value == 10) {
                    if (roll3Elem) {
                        roll3Elem.disabled = false;
                    }
                } else if (roll === 2 && value + parseInt(document.getElementById('frame10roll1').value) == 10) {
                    if (roll3Elem) {
                        roll3Elem.disabled = false;
                    }
                } else {
                    if (roll3Elem) {
                        roll3Elem.disabled = true;
                    }
                }
            }
        }
    </script>
</head>
<body>
    <h1>Create Bowling Game</h1>
    <form action="/api/bowling/create" method="post">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name" required><br><br>

        <h2>Frames</h2>
        <c:forEach var="i" begin="1" end="9">
            <div class="frame-row">
                <label>Frame ${i} - Roll 1:</label>
                <input type="number" id="frame${i}roll1" name="frame${i}roll1" min="0" max="10" required
                       onchange="handleRollInput(${i}, 1, this.value)">
                <label>Frame ${i} - Roll 2:</label>
                <input type="number" id="frame${i}roll2" name="frame${i}roll2" min="0" max="10" required>
            </div>
        </c:forEach>

        <!-- Last frame logic -->
        <h2>Frame 10</h2>
        <div class="frame-row">
            <label>Frame 10 - Roll 1:</label>
            <input type="number" id="frame10roll1" name="frame10roll1" min="0" max="10" required
                   onchange="handleRollInput(10, 1, this.value)">
            <label>Frame 10 - Roll 2:</label>
            <input type="number" id="frame10roll2" name="frame10roll2" min="0" max="10" required
                   onchange="handleRollInput(10, 2, this.value)">
            <label>Frame 10 - Roll 3 (if applicable):</label>
            <input type="number" id="frame10roll3" name="frame10roll3" min="0" max="10" disabled>
        </div>

        <input type="submit" value="Submit">
    </form>
    <br>
    <a href="/home">Back to Home</a>
</body>
</html>
