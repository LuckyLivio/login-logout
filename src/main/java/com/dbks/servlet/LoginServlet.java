package com.dbks.servlet;

import com.dbks.util.DbConn;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        if (username == null || password == null || 
            username.trim().isEmpty() || password.trim().isEmpty()) {
            request.setAttribute("error", "用户名和密码不能为空");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
            return;
        }
        
        username = username.trim();
        password = password.trim();
        
        boolean isValid = DbConn.validateUser(username, password);
        
        if (isValid) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            
            String userInfo = DbConn.getUserInfo(username);
            request.setAttribute("userInfo", userInfo);
            
            request.getRequestDispatcher("Success.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "用户名或密码错误");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("Login.jsp").forward(request, response);
    }
}
