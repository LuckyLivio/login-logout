package com.dbks.servlet;

import com.dbks.util.DbConn;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/updatePassword")
public class SecServlet extends HttpServlet {
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        request.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        
        if (username == null) {
            request.setAttribute("error", "请先登录");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
            return;
        }
        
        String newPassword = request.getParameter("newPassword");
        
        if (newPassword == null || newPassword.trim().isEmpty()) {
            request.setAttribute("error", "新密码不能为空");
            String userInfo = DbConn.getUserInfo(username);
            request.setAttribute("userInfo", userInfo);
            request.getRequestDispatcher("Success.jsp").forward(request, response);
            return;
        }
        
        boolean success = DbConn.updatePassword(username, newPassword);
        
        if (success) {
            session.invalidate();
            request.setAttribute("success", "密码修改成功！请重新登录");
            request.getRequestDispatcher("Login.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "密码修改失败");
            String userInfo = DbConn.getUserInfo(username);
            request.setAttribute("userInfo", userInfo);
            request.getRequestDispatcher("Success.jsp").forward(request, response);
        }
    }
}
