<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>登录成功</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
    <div class="container">
        <h2>欢迎，<%= session.getAttribute("username") %>！</h2>
        
        <% if (request.getAttribute("error") != null) { %>
            <div class="error"><%= request.getAttribute("error") %></div>
        <% } %>
        
        <div class="user-info">
            <h3>用户信息：</h3>
            <p><%= request.getAttribute("userInfo") %></p>
        </div>
        
        <div class="password-change">
            <h3>修改密码</h3>
            <form action="updatePassword" method="post">
                <div class="form-group">
                    <label for="newPassword">新密码:</label>
                    <input type="password" id="newPassword" name="newPassword" required>
                </div>
                
                <div class="form-group">
                    <input type="submit" value="修改密码" class="btn">
                </div>
            </form>
        </div>
        
        <div class="links">
            <a href="login">退出登录</a>
        </div>
    </div>
</body>
</html>
