package ornek;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/UserRegister")
public class UserRegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        
        String userAgent = request.getHeader("User-Agent");

        
        String message = "First Name: " + firstName + "\n" +
                         "Last Name: " + lastName + "\n" +
                         "Email: " + email + "\n" +
                         "Password: " + maskPassword(password) + "\n" +
                         "User Agent: " + userAgent;

        
        response.setContentType("text/plain");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(message);
    }

  
    private String maskPassword(String password) {
        int length = password.length();
        StringBuilder maskedPassword = new StringBuilder();
        for (int i = 0; i < length; i++) {
            maskedPassword.append("*");
        }
        return maskedPassword.toString();
    }
}
