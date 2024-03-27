package ornek;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.Iterator;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/UploadServlet")
public class UploadServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/plain;charset=UTF-8");

        Collection<Part> parts = request.getParts();
        Iterator<Part> iterator = parts.iterator();

        while (iterator.hasNext()) {
            Part part = iterator.next();
            String fileName = getFileName(part);

            if (fileName != null && !fileName.isEmpty()) {
                try {
                    
                    response.getWriter().println("Yüklenen dosya: " + fileName);
                } catch (Exception e) {
                   
                    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                    response.getWriter().println("Dosya işlenirken bir hata oluştu.");
                    e.printStackTrace();
                    return;
                }
            }
        }
    }

    private String getFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        String[] tokens = contentDisposition.split(";");

        for (String token : tokens) {
            if (token.trim().startsWith("filename")) {
                return token.substring(token.indexOf('=') + 1).trim().replace("\"", "");
            }
        }

        return null;
    }
}
