package ua.goit.controller.companyServlets;

import ua.goit.model.Project;
import ua.goit.service.ProjectService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/create-company-form")
public class CreateCompanyFormServlet extends HttpServlet {
    private ProjectService projectService;

    @Override
    public void init() throws ServletException {
        projectService = ProjectService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Project> projects = projectService.findAll();
        req.setAttribute("projects", projects);
        req.getRequestDispatcher("/WEB-INF/html/companiesJSP/createCompanyForm.jsp").forward(req, resp);
    }
}
