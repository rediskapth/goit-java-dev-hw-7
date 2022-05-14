package ua.goit.controller.projectServlets;

import ua.goit.model.Project;
import ua.goit.service.ProjectService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;

@WebServlet("/project-updated")
public class UpdateProjectServlet extends HttpServlet {
    private ProjectService projectService;

    @Override
    public void init() throws ServletException {
        projectService = projectService.getInstance();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String projectId = req.getParameter("projectId");
        String projectName = req.getParameter("projectName");
        String projectDescription = req.getParameter("projectDescription");
        String projectCreationDate = req.getParameter("projectCreationDate");
        Project project = new Project();
        project.setId(Integer.parseInt(projectId));
        try {
            projectService.findById(Integer.parseInt(projectId));
        } catch (Exception e) {
            req.setAttribute("exception", "Project with this id is absent. Please try again.");
            req.getRequestDispatcher("/WEB-INF/html/error.jsp").forward(req, resp);
            return;
        }
        project.setName(projectName);
        project.setDescription(projectDescription);
        project.setCreationDate(LocalDate.parse(projectCreationDate));
        projectService.update(project);
        req.getRequestDispatcher("/WEB-INF/html/projectsJSP/projectUpdated.jsp").forward(req, resp);
    }
}
