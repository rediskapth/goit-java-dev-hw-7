package ua.goit.controller.customerServlets;

import ua.goit.model.Customer;
import ua.goit.model.Project;
import ua.goit.service.CustomerService;
import ua.goit.service.ProjectService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@WebServlet("/customer-created")
public class CreateCustomerServlet extends HttpServlet {
    private CustomerService customerService;
    private ProjectService projectService;

    @Override
    public void init() throws ServletException {
        customerService = CustomerService.getInstance();
        projectService = ProjectService.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String customerName = req.getParameter("customerName");
        String customerLocation = req.getParameter("customerLocation");
        Customer customer = new Customer();
        customer.setName(customerName);
        customer.setLocation(customerLocation);

        if (req.getParameterValues("projectId") != null) {
            Set<Integer> projectIds = Arrays.stream(req.getParameterValues("projectId"))
                    .map(Integer::parseInt)
                    .collect(Collectors.toSet());
            Set<Project> projects = projectService.findByIds(projectIds);
            customer.setProjects(projects);
        }
        customerService.save(customer);
        req.getRequestDispatcher("/WEB-INF/html/customersJSP/customerCreated.jsp").forward(req, resp);
    }
}
