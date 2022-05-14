package ua.goit.controller.companyServlets;

import ua.goit.model.Company;
import ua.goit.model.Project;
import ua.goit.service.CompanyService;
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

@WebServlet("/company-created")
public class CreateCompanyServlet extends HttpServlet {
    private CompanyService companyService;
    private ProjectService projectService;

    @Override
    public void init() throws ServletException {
        companyService = CompanyService.getInstance();
        projectService = ProjectService.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String companyName = req.getParameter("companyName");
        String companyLocation = req.getParameter("companyLocation");
        Company company = new Company();
        company.setName(companyName);
        company.setLocation(companyLocation);

        if (req.getParameterValues("projectId") != null) {
            Set<Integer> projectIds = Arrays.stream(req.getParameterValues("projectId"))
                    .map(Integer::parseInt)
                    .collect(Collectors.toSet());
            Set<Project> projects = projectService.findByIds(projectIds);
            company.setProjects(projects);
        }
        companyService.save(company);
        req.getRequestDispatcher("/WEB-INF/html/companiesJSP/companyCreated.jsp").forward(req, resp);
    }
}
