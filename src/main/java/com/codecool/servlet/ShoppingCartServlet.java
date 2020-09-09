package com.codecool.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * This should list the content of Cart's list. It should render a HTML list or
 * a table with the items in it. It should render a Sum of Price field as well.
 */
@WebServlet(name = "shoppingCart", urlPatterns = {"/shoppingCart"}, loadOnStartup = 1)
public class ShoppingCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        StringBuilder builder = new StringBuilder();
        List<Item> items = Cart.INSTANCE.getItems();

        for (Item item : items) {
            builder.append("<tr>\n")
                    .append("<td>")
                    .append(item.getName())
                    .append("</td>\n")
                    .append("<td>")
                    .append(item.getPrice() / 100.0)
                    .append("</td>\n");
        }

        resp.getWriter().println(
                "<html>\n" +
                        "<head><title>Shopping cart</title></head>\n"
                        + "<body>\n"
                        + "<h1>Shopping cart</h1>"
                        + "<br/>"
                        + "<div>"
                        + "<table>"
                        + "<tbody>"
                        + builder.toString()
                        + "</tbody>"
                        + "</table>"
                        + "</div>"
                        + "<br>"
                        + "Sum of Price: "
                        + Cart.INSTANCE.getSum() / 100.0 + " USD"
                        + "</body></html>"
        );
    }
}
