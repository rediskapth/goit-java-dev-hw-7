package ua.goit.controller.projectServlets;

import ua.goit.model.Project;
import ua.goit.service.ProjectService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/project-deleted")
public class DeleteProjectServlet extends HttpServlet {
    private ProjectService projectService;

    @Override
    public void init() throws ServletException {
        projectService = ProjectService.getInstance();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String projectId = req.getParameter("projectId");
        Project project = new Project();
        project.setId(Integer.parseInt(projectId));
        try {
            projectService.findById(Integer.parseInt(projectId));
        } catch (Exception e) {
            req.setAttribute("exception", "Project with this id is absent. Please try again.");
            req.getRequestDispatcher("/WEB-INF/html/error.jsp").forward(req, resp);
            return;
        }
        projectService.remove(project);
        req.getRequestDispatcher("/WEB-INF/html/projectsJSP/projectDeleted.jsp").forward(req, resp);
    }
}
