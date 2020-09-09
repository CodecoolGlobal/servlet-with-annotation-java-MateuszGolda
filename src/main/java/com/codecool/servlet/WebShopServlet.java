package com.codecool.servlet;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * This should list at least 3-5 different available Items with different
 * properties fromStock.java (eg. [0, "Asus Laptop", 1600.0], [1, "Harry Potter
 * Ebook", 50.0], etc.). Generate these Items in the init() method , for
 * example. Render every Item's name and price on the screen, with an Add and
 * a Remove button OR link. When these buttons are pressed, the content of
 * Cart's list should be modified with the belonging item. You shouldn't give
 * any feedback on the screen from the modifications (yet)!
 */
@WebServlet(name = "webShop", urlPatterns = {"/webShop"}, loadOnStartup = 1)
public class WebShopServlet extends HttpServlet {
    private Stock stock;

    @Override
    public void init() {
        stock = new Stock();
        stock.addItem(new Item(1, "mouse", 2000));
        stock.addItem(new Item(2, "keyboard", 2999));
        stock.addItem(new Item(3, "monitor", 40000));
        stock.addItem(new Item(4, "headphones", 9000));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        StringBuilder builder = new StringBuilder();
        Set<Item> items = stock.getItems();

        String action = "/webShop?item_id=";
        String button = "<form method=\"%s\" action=\" %s \">\n" +
                "<button type=\"submit\">\n" +
                "%s\n" +
                "</button>\n" +
                "</form>";

        for (Item item : items) {
            builder.append("<tr>\n")
                    .append("<td>")
                    .append(item.getName())
                    .append("</td>\n")
                    .append("<td>")
                    .append(item.getPrice() / 100.0)
                    .append("</td>\n")
                    .append("<td>")
                    .append(String.format(button, "post", action + item.getId(), "Add to cart"))
                    .append("</td>\n")
                    .append("<td>")
                    .append(String.format(button, "delete", action + item.getId(), "Remove"))
                    .append("</td>\n")
                    .append("</tr>\n");
        }

        action = "/shoppingCart";
        resp.getWriter().println(
                "<html>\n" +
                        "<head><title>Web shop page</title></head>\n"
                        + "<body>\n"
                        + "<h1>Web shop</h1>"
                        + "<br/>"
                        + "<div>"
                        + "<table>"
                        + "<tbody>"
                        + builder.toString()
                        + "</tbody>"
                        + "</table>"
                        + "</div>"
                        + String.format(button, "get", action, "Check Shopping Cart")
                        + "</body></html>"
        );
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String itemId = req.getParameter("item_id");
        Cart.INSTANCE.add(stock.getItemById(Integer.parseInt(itemId)));
        doGet(req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String itemId = req.getParameter("item_id");
        Cart.INSTANCE.remove(stock.getItemById(Integer.parseInt(itemId)));
        doGet(req, resp);
    }
}
