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
import java.time.LocalDate;

@WebServlet("/project-created")
public class CreateProjectServlet extends HttpServlet {
    private ProjectsService projectsService;

    @Override
    public void init() throws ServletException {
        DatabaseManager dbConnector = new HibernateProvider();
        projectsService = new ProjectsService(new ProjectsConverter(), new ProjectsRepository(dbConnector));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String projectName = req.getParameter("projectName");
        String projectDescription = req.getParameter("projectDescription");
        String projectCreationDate = req.getParameter("projectCreationDate");
        ProjectsDto projectsDto = new ProjectsDto();
        projectsDto.setName(projectName);
        projectsDto.setDescription(projectDescription);
        projectsDto.setCreationDate(LocalDate.parse(projectCreationDate));
        projectsService.save(projectsDto);
        req.getRequestDispatcher("/WEB-INF/html/projectsJSP/projectCreated.jsp").forward(req, resp);
    }
}
