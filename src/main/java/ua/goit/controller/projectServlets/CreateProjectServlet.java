package ua.goit.controller.projectServlets;

import ua.goit.model.Company;
import ua.goit.model.Customer;
import ua.goit.model.Developer;
import ua.goit.model.Project;
import ua.goit.service.CompanyService;
import ua.goit.service.CustomerService;
import ua.goit.service.DeveloperService;
import ua.goit.service.ProjectService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

@WebServlet("/project-created")
public class CreateProjectServlet extends HttpServlet {
    private ProjectService projectService;
    private CompanyService companyService;
    private CustomerService customerService;
    private DeveloperService developerService;

    @Override
    public void init() throws ServletException {
        projectService = ProjectService.getInstance();
        companyService = CompanyService.getInstance();
        customerService = CustomerService.getInstance();
        developerService = DeveloperService.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String projectName = req.getParameter("projectName");
        String projectDescription = req.getParameter("projectDescription");
        String projectCreationDate = req.getParameter("projectCreationDate");
        Project project = new Project();
        project.setName(projectName);
        project.setDescription(projectDescription);
        project.setCreationDate(LocalDate.parse(projectCreationDate));

        if (req.getParameterValues("companyId") != null) {
            Set<Integer> companyIds = Arrays.stream(req.getParameterValues("companyId"))
                    .map(Integer::parseInt)
                    .collect(Collectors.toSet());
            Set<Company> companies = companyService.findByIds(companyIds);
            project.setCompanies(companies);
        }
        if (req.getParameterValues("customerId") != null) {
            Set<Integer> customerIds = Arrays.stream(req.getParameterValues("customerId"))
                    .map(Integer::parseInt)
                    .collect(Collectors.toSet());
            Set<Customer> customers = customerService.findByIds(customerIds);
            project.setCustomers(customers);
        }
        if (req.getParameterValues("developerId") != null) {
            Set<Integer> developerIds = Arrays.stream(req.getParameterValues("developerId"))
                    .map(Integer::parseInt)
                    .collect(Collectors.toSet());
            Set<Developer> developers = developerService.findByIds(developerIds);
            project.setDevelopers(developers);
        }
        projectService.save(project);
        req.getRequestDispatcher("/WEB-INF/html/projectsJSP/projectCreated.jsp").forward(req, resp);
    }
}
