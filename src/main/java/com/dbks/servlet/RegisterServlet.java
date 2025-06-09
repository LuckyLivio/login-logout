package com.dbks.servlet;

import com.dbks.util.DbConn;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");
        
        if (username == null || password == null || email == null ||
            username.trim().isEmpty() || password.trim().isEmpty() || email.trim().isEmpty()) {
            request.setAttribute("error", "所有字段都必须填写");
            request.getRequestDispatcher("Register.jsp").forward(request, response);
            return;
        }
        
        boolean success = DbConn.registerUser(username, password, email);
        
        if (success) {
            request.setAttribute("success", "注册成功！请登录");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "注册失败，用户名可能已存在");
            request.getRequestDispatcher("Register.jsp").forward(request, response);
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        request.getRequestDispatcher("Register.jsp").forward(request, response);
    }
}
