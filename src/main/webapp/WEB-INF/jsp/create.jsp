<!DOCTYPE html>
<html>
<head>
    <title>Create Bowling Game</title>
</head>
<body>
    <h1>Create Bowling Game</h1>
    <form action="/api/bowling/create" method="post">
        <label for="name">Name:</label>
        <input type="text" id="name" name="name"><br><br>
        <label for="rolls">Rolls (comma separated):</label>
        <input type="text" id="rolls" name="rolls"><br><br>
        <input type="submit" value="Submit">
    </form>
</body>
</html>
