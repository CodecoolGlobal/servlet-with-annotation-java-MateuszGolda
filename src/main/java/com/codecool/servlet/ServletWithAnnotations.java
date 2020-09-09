package com.codecool.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "simpleServlet", urlPatterns = {"/simpleServlet"}, loadOnStartup = 1)
public class ServletWithAnnotations extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {

        String title = "GET method with parameters to display";

        resp.getWriter().println(
                "<html>\n" +
                        "<head><title>" + title + "</title></head>\n" +
                        "<body>\n" +
                        "<h1 align = \"center\">" + title + "</h1>\n" +
                        "<ul>\n" +
                        "<li><b>First Name</b>: " + req.getParameter("first_name") + "\n" +
                        "<li><b>Last Name</b>: " + req.getParameter("last_name") + "\n" +
                        "</ul>\n" +
                        "<div>Visit another servlet: <a href=\"/another\">Visit the other servlet</a></div>" +
                        "</body></html>"
        );
    }
}
