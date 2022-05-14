package ua.goit.controller.projectServlets;

import ua.goit.model.Company;
import ua.goit.model.Customer;
import ua.goit.model.Developer;
import ua.goit.service.CompanyService;
import ua.goit.service.CustomerService;
import ua.goit.service.DeveloperService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/create-project-form")
public class CreateProjectFormServlet extends HttpServlet {
    private CompanyService companyService;
    private CustomerService customerService;
    private DeveloperService developerService;

    @Override
    public void init() throws ServletException {
        companyService = CompanyService.getInstance();
        customerService = CustomerService.getInstance();
        developerService = DeveloperService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Company> companies = companyService.findAll();
        req.setAttribute("companies", companies);
        List<Customer> customers = customerService.findAll();
        req.setAttribute("customers", customers);
        List<Developer> developers = developerService.findAll();
        req.setAttribute("developers", developers);
        req.getRequestDispatcher("/WEB-INF/html/projectsJSP/createProjectForm.jsp").forward(req, resp);
    }
}
