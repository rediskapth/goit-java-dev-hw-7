package ua.goit.controller.customersServlets;

import ua.goit.config.DatabaseManager;
import ua.goit.config.HibernateProvider;
import ua.goit.model.convert.CustomersConverter;
import ua.goit.model.dto.CustomersDto;
import ua.goit.repository.CustomersRepository;
import ua.goit.service.CustomersService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/customer-deleted")
public class DeleteCustomerServlet extends HttpServlet {
    private CustomersService customersService;

    @Override
    public void init() throws ServletException {
        DatabaseManager dbConnector = new HibernateProvider();
        customersService = new CustomersService(new CustomersConverter(), new CustomersRepository(dbConnector));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String customerId = req.getParameter("customerId");
        CustomersDto customersDto = new CustomersDto();
        customersDto.setCustomerId(Integer.parseInt(customerId));
        try {
            customersService.findById(Integer.parseInt(customerId));
        } catch (Exception e) {
            req.setAttribute("exception", "Customer with this id is absent. Please try again.");
            req.getRequestDispatcher("/WEB-INF/html/error.jsp").forward(req, resp);
            return;
        }
        customersService.remove(customersDto);
        req.getRequestDispatcher("/WEB-INF/html/customersJSP/customerDeleted.jsp").forward(req, resp);
    }
}
