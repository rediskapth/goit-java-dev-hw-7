package ua.goit.controller.projectsServlets;

import ua.goit.config.DatabaseManager;
import ua.goit.config.HibernateProvider;
import ua.goit.model.convert.ProjectsConverter;
import ua.goit.model.dto.ProjectsDto;
import ua.goit.repository.ProjectsRepository;
import ua.goit.service.ProjectsService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/project-deleted")
public class DeleteProjectServlet extends HttpServlet {
    private ProjectsService projectsService;

    @Override
    public void init() throws ServletException {
        DatabaseManager dbConnector = new HibernateProvider();
        projectsService = new ProjectsService(new ProjectsConverter(), new ProjectsRepository(dbConnector));
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String projectId = req.getParameter("projectId");
        ProjectsDto projectsDto = new ProjectsDto();
        projectsDto.setProjectId(Integer.parseInt(projectId));
        try {
            projectsService.findById(Integer.parseInt(projectId));
        } catch (Exception e) {
            req.setAttribute("exception", "Project with this id is absent. Please try again.");
            req.getRequestDispatcher("/WEB-INF/html/error.jsp").forward(req, resp);
            return;
        }
        projectsService.remove(projectsDto);
        req.getRequestDispatcher("/WEB-INF/html/projectsJSP/projectDeleted.jsp").forward(req, resp);
    }
}
