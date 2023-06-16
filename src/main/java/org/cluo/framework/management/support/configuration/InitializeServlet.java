package org.cluo.framework.management.support.configuration;

import org.cluo.framework.json.JSONUtil;
import org.cluo.framework.management.service.CmsSystemService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cms-api/cms/initializing/public-info")
public class InitializeServlet extends HttpServlet {

    @Autowired
    private CmsSystemService cmsSystemService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setCharacterEncoding("utf-8");
        response.setContentType("application/json;charset=utf-8");

        try {
            response.getOutputStream().write(JSONUtil.fromObjectAsBytes(cmsSystemService.publicInfo()));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}