<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Inquirer</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>

<div class="wrapper">
    <div class="header">
        <h1>
            ${status}
        </h1>
    </div>
    <div class="content">
        <div class="login_block">
            <form action="login" method="post">
                <label for="login">Имя пользователя</label>
                <input type="text" id="login" name="login" />
                <input class="link" type="submit">
            </form>
        </div>
    </div>
</div>
</body>
</html>