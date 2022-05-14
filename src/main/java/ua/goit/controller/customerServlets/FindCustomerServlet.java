package ua.goit.controller.customerServlets;

import ua.goit.model.Customer;
import ua.goit.service.CustomerService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/find-customer")
public class FindCustomerServlet extends HttpServlet {
    private CustomerService customerService;

    @Override
    public void init() throws ServletException {
        customerService = CustomerService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String customerId = req.getParameter("customerId");
        Customer customer;
        try {
            customer = customerService.findById(Integer.parseInt(customerId));
        } catch (Exception e) {
            req.setAttribute("exception", "Customer with this id is absent. Please try again.");
            req.getRequestDispatcher("/WEB-INF/html/error.jsp").forward(req, resp);
            return;
        }
        req.setAttribute("customer", customer);
        req.getRequestDispatcher("/WEB-INF/html/customersJSP/findCustomer.jsp").forward(req, resp);
    }
}
