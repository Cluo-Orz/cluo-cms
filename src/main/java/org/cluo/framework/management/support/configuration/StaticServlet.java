package org.cluo.framework.management.support.configuration;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

@WebServlet(urlPatterns = "/cluo-cms/*")
public class StaticServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(1341234);
        String requestURI = request.getRequestURI();
        String relativePath = "index.html";
        if(!requestURI.endsWith("cluo-cms")){
            relativePath= requestURI.substring("/cluo-cms/".length());
        }
        String resourcePath = "/web/" + relativePath;

        InputStream resourceStream = getClass().getResourceAsStream(resourcePath);
        if (resourceStream == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        byte[] buffer = new byte[1024];
        int bytesRead;
        OutputStream outputStream = response.getOutputStream();
        while ((bytesRead = resourceStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }

        resourceStream.close();
        outputStream.close();
    }
}